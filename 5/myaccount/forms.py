from django import forms


from django.contrib.auth.forms import UserCreationForm
from restaccount.models import DriverUser

class RegisterForm(UserCreationForm):
	class Meta:
		model = DriverUser
		fields = ('username', 'password1', 'password2')