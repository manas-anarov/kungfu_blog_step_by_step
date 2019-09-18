from rest_framework import serializers
from rest_framework.serializers import (
	ModelSerializer,
	ValidationError,
	EmailField,
)
from mypost.models import Post
from mypost.models import Category
from restaccount.models import DriverUser

class RegisterSerializer(ModelSerializer):
	email = EmailField(label='Email adress')
	class Meta:
		model = DriverUser
		fields = [
			'id',
			'username',
			'password',
			'email',
		]
	extra_kwargs = {"password":
					{"write_only":True},
					"id":
					{"read_only":True}
					}

	def validate(self, data):
		return data

	def validate_email(self, value):
		email = value
		user_qs = DriverUser.objects.filter(email=email)
		if user_qs.exists():
			raise ValidationError("Email alredy registred")
		return value


	def create(self, validated_data):
		username = validated_data['username']
		password = validated_data['password']
		email = validated_data['email']
		user_obj = DriverUser(
			username = username,
			email = email,
		)
		user_obj.set_password(password)
		user_obj.save()
		return user_obj