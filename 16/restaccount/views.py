from django.shortcuts import render
from rest_framework.views import APIView
from django.http import HttpResponse
from django.http import JsonResponse

from .serializers import RegisterSerializer

from rest_framework.generics import (CreateAPIView, RetrieveUpdateAPIView, UpdateAPIView, DestroyAPIView, ListAPIView, RetrieveAPIView)
from rest_framework.response import Response

from restaccount.models import DriverUser




from rest_framework.authentication import TokenAuthentication
from rest_framework.authentication import SessionAuthentication


from rest_framework.permissions import IsAuthenticated
from rest_framework.permissions import (
	AllowAny,
	)

class Register(CreateAPIView):
	permission_classes = (AllowAny,)

	serializer_class = RegisterSerializer
	queryset = DriverUser.objects.all()