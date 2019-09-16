from restpost import views
from django.conf.urls import url

urlpatterns = [
	url(r'^list/$', views.PostListAPIView.as_view(), name='restpost-list'),
]