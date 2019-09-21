from rest_framework import serializers
from rest_framework.serializers import (
	ModelSerializer,
)
from mypost.models import Post
from mypost.models import Category

class listUserSerializer(ModelSerializer):
	class Meta:
		model = Post
		fields = [
			'id',
			'title',
			'text',
		]


class deleteSerializer(ModelSerializer):
	class Meta:
		model = Post
		fields = [
			'id',
		]


class updateSerializer(ModelSerializer):
	class Meta:
		model = Post
		fields = [
			'category',
			'title',
			'text',
		]