
from django.contrib import admin
from django.urls import path

from django.conf.urls import include, url

urlpatterns = [
    path('admin/', admin.site.urls),

    url(r'^post/', include(('mypost.urls', 'mypost'), namespace='mypost')),
    url(r'^profile/', include(('myprofile.urls', 'myprofile'), namespace='myprofile')),
	url(r'^account/', include(('myaccount.urls', 'myaccount'), namespace='myaccount')),

	url(r'^api/v1/account/', include(('restaccount.urls', 'restprofile'), namespace='restaccount')),
]