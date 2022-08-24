import tensorflow as tf
from keras import Model
from keras import backend as K
from keras.layers import Layer, InputSpec
import keras.layers as kl
from keras.layers import concatenate, Dense, Conv2D, MaxPooling2D, Flatten, Input, Activation, add, AveragePooling2D, \
    BatchNormalization, Dropout
from keras.preprocessing import image

from keras.utils import load_img, img_to_array
import numpy as np

model = None


class HAM10000:

    def __int__(self):
        print("chao")

    def load_image(self, img_path):
        img = load_img(img_path, target_size=(299, 299))
        img_tensor = img_to_array(img)  # (height, width, channels)
        img_tensor = np.expand_dims(img_tensor,
                                    axis=0)  # (1, height, width, channels), add a dimension because the model expects this shape: (batch_size, height, width, channels)
        img_tensor /= 255.  # imshow expects values in the range [0, 1]

        return img_tensor

    def predict(self, image_path):
        global model
        if model is None:
            print("loading model ...")
            irv2 = tf.keras.applications.InceptionResNetV2(
                include_top=True,
                weights="imagenet",
                input_tensor=None,
                input_shape=None,
                pooling=None,
                classifier_activation="softmax",

            )

            conv = irv2.layers[-28].output

            attention_layer, map2 = SoftAttention(aggregate=True, m=16, concat_with_x=False, ch=int(conv.shape[-1]),
                                                  name='soft_attention')(conv)
            attention_layer = (MaxPooling2D(pool_size=(2, 2), padding="same")(attention_layer))
            conv = (MaxPooling2D(pool_size=(2, 2), padding="same")(conv))

            conv = concatenate([conv, attention_layer])
            conv = Activation('relu')(conv)
            conv = Dropout(0.5)(conv)

            output = Flatten()(conv)
            output = Dense(7, activation='softmax')(output)
            model = Model(inputs=irv2.input, outputs=output)

            # model.summary()

            model.load_weights("IRV2+SA.hdf5")
        # load a single image
        print("image_path = ", image_path)
        new_image = self.load_image(image_path)

        # check prediction
        pred = model.predict(new_image)

        print("predicted: ", pred)

        return pred


class SoftAttention(Layer):
    def __init__(self, ch, m, concat_with_x=False, aggregate=False, **kwargs):
        self.channels = int(ch)
        self.multiheads = m
        self.aggregate_channels = aggregate
        self.concat_input_with_scaled = concat_with_x

        super(SoftAttention, self).__init__(**kwargs)

    def build(self, input_shape):

        self.i_shape = input_shape

        kernel_shape_conv3d = (self.channels, 3, 3) + (1, self.multiheads)  # DHWC

        self.out_attention_maps_shape = input_shape[0:1] + (self.multiheads,) + input_shape[1:-1]

        if self.aggregate_channels == False:

            self.out_features_shape = input_shape[:-1] + (input_shape[-1] + (input_shape[-1] * self.multiheads),)
        else:
            if self.concat_input_with_scaled:
                self.out_features_shape = input_shape[:-1] + (input_shape[-1] * 2,)
            else:
                self.out_features_shape = input_shape

        self.kernel_conv3d = self.add_weight(shape=kernel_shape_conv3d,
                                             initializer='he_uniform',
                                             name='kernel_conv3d')
        self.bias_conv3d = self.add_weight(shape=(self.multiheads,),
                                           initializer='zeros',
                                           name='bias_conv3d')

        super(SoftAttention, self).build(input_shape)

    def call(self, x):

        exp_x = K.expand_dims(x, axis=-1)

        c3d = K.conv3d(exp_x,
                       kernel=self.kernel_conv3d,
                       strides=(1, 1, self.i_shape[-1]), padding='same', data_format='channels_last')
        conv3d = K.bias_add(c3d,
                            self.bias_conv3d)
        conv3d = kl.Activation('relu')(conv3d)

        conv3d = K.permute_dimensions(conv3d, pattern=(0, 4, 1, 2, 3))

        conv3d = K.squeeze(conv3d, axis=-1)
        conv3d = K.reshape(conv3d, shape=(-1, self.multiheads, self.i_shape[1] * self.i_shape[2]))

        softmax_alpha = K.softmax(conv3d, axis=-1)
        softmax_alpha = kl.Reshape(target_shape=(self.multiheads, self.i_shape[1], self.i_shape[2]))(softmax_alpha)

        if self.aggregate_channels == False:
            exp_softmax_alpha = K.expand_dims(softmax_alpha, axis=-1)
            exp_softmax_alpha = K.permute_dimensions(exp_softmax_alpha, pattern=(0, 2, 3, 1, 4))

            x_exp = K.expand_dims(x, axis=-2)

            u = kl.Multiply()([exp_softmax_alpha, x_exp])

            u = kl.Reshape(target_shape=(self.i_shape[1], self.i_shape[2], u.shape[-1] * u.shape[-2]))(u)

        else:
            exp_softmax_alpha = K.permute_dimensions(softmax_alpha, pattern=(0, 2, 3, 1))

            exp_softmax_alpha = K.sum(exp_softmax_alpha, axis=-1)

            exp_softmax_alpha = K.expand_dims(exp_softmax_alpha, axis=-1)

            u = kl.Multiply()([exp_softmax_alpha, x])

        if self.concat_input_with_scaled:
            o = kl.Concatenate(axis=-1)([u, x])
        else:
            o = u

        return [o, softmax_alpha]

    def compute_output_shape(self, input_shape):
        return [self.out_features_shape, self.out_attention_maps_shape]

    def get_config(self):
        return super(SoftAttention, self).get_config()
