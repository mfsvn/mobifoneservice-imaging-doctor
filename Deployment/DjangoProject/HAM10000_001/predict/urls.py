from django.urls import path
from . import views


urlpatterns = [
    path('', views.predict_with_img_path, name='predict'),
    path('hello/', views.hello, name='hello'),
]