from django import forms
from mypost.models import Post


class AddForm(forms.ModelForm):
	class Meta:
		model = Post
		exclude = ('author',)

class ListForm(forms.ModelForm):
	class Meta:
		model = Post
		exclude = [""]