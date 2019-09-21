from restprofile import views
from django.conf.urls import url

urlpatterns = [
	url(r'^list/$', views.ListAuthor.as_view(), name='list'),
	url(r'^delete/(?P<id>[\w-]+)/$', views.DeletePost.as_view(), name='delete'),
]