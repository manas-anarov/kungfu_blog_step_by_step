from rest_framework import serializers
from rest_framework.serializers import (
	ModelSerializer,
)
from mypost.models import Post
from mypost.models import Category


class listSerializer(ModelSerializer):
	class Meta:
		model = Post
		fields = [
			'id',
			'category',
			'title',
			'text',
		]


class addSerializer(ModelSerializer):
	class Meta:
		model = Post
		fields = [
			'category',
			'title',
			'text',
		]


class showSerializer(ModelSerializer):
	class Meta:
		model = Post
		fields = [
			'id',
			'category',
			'title',
			'text',
		]


class listCategorySerializer(ModelSerializer):
	class Meta:
		model = Category
		fields = [
			'id',
			'id_category',
			'name',
		]