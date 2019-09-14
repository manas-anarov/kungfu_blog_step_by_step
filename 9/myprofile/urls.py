from django.conf.urls import url

from . import views

urlpatterns = [
	url(r'^list', views.all_posts, name='list'),
	url(r'^update/(?P<pk>\d+)/$', views.update_post, name='update'),
]