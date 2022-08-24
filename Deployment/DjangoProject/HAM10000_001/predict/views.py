from django.shortcuts import render
from django.http import HttpResponse
from model import HAM10000
from django.core import serializers
from django.views.decorators.csrf import csrf_exempt
import json
import numpy as np
from django.http import JsonResponse


# Create your views here.


def index(request):
    return render(request, "predict/index.html")


def hello(chao):
    print(chao.path)
    return HttpResponse("<html><head></head>    <body> <span style='color:red'>hello world </span> </body>     </html>")


class NpEncoder(json.JSONEncoder):
    def default(self, obj):
        if isinstance(obj, np.integer):
            return int(obj)
        if isinstance(obj, np.floating):
            # 👇️ alternatively use str()
            return float(obj)
        if isinstance(obj, np.ndarray):
            return obj.tolist()
        return json.JSONEncoder.default(self, obj)


@csrf_exempt
def predict_with_img_path(request):
    if request.method == "POST":
        data = request.POST
        image_path = data.get("image_path")
        print("image: ", image_path)

        model1 = HAM10000.HAM10000()
        res = model1.predict(image_path)

        real = dict()

        i = 1
        for v in res[0]:
            k = str(i)
            real[k] = v
            print(v)
            i += 1

        print("real: ", real)

        json_object = json.dumps(real, cls=NpEncoder)
        print("real: ", real)

        response = HttpResponse(json_object)
        response['Content-Type'] = 'application/json'

        return response
