from restaccount import views
from django.conf.urls import url
from django.urls import path
from rest_framework.authtoken import views as authviews

urlpatterns = [
	url(r'^register/$', views.Register.as_view(), name='register'),
]