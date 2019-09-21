from django.conf.urls import url

from . import views

urlpatterns = [
	url(r'^add', views.add, name='add'),
	url(r'^list', views.all_posts, name='list'),
	url(r'^(?P<pk>\d+)/$', views.show_post, name='post-show'),
	url(r'^category/(?P<pk>\d+)/$', views.list_category, name='category'),
]