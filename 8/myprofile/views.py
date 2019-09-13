from django.shortcuts import render, redirect
from .forms import ListForm
from django.shortcuts import render

from mypost.models import Post
from mypost.models import Category
from django.core.paginator import Paginator

from django.shortcuts import render, get_object_or_404
from django.contrib.auth.decorators import login_required
# from .forms import UpdateForm


def all_posts(request):

	user = request.user.id
	form = ListForm(request.POST)
	posts = Post.objects.filter(author=user).order_by('-id')
	category_search = Category.objects.all()

	if request.method == 'POST':
		category_id = request.POST.get('category_id_f')
		posts = Post.objects.filter(category=category_id, author = user).order_by('-id')
	
	paginator = Paginator(posts, 10)
	page = request.GET.get('page')
    
	posts = paginator.get_page(page)

	context_posts = {
		'posts': posts,
		'category_search': category_search,
	}

	return render(request, 'myprofile/all_posts.html', context_posts)