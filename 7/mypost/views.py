from django.shortcuts import render
from .forms import AddForm
from .forms import ListForm
from django.shortcuts import render

from mypost.models import Post
from mypost.models import Category

from django.shortcuts import render, get_object_or_404
from django.contrib.auth.decorators import login_required

from django.core.paginator import Paginator

def add(request):
	form = AddForm(request.POST)
	post_category = Category.objects.all()
	if request.method == 'POST':
		print(form.errors)
		if form.is_valid():
			post_saved = form.save(commit=False)
			post_saved.author = request.user.id
			form.save()
	return render(request, 'add.html', {'form': form, 'post_category': post_category})


def all_posts(request):

	form = ListForm(request.POST)
	posts = Post.objects.all().order_by('-id')
	categoty_search = Category.objects.all()

	if request.method == 'POST':
		category_id = request.POST.get('category_id_f')
		posts = Post.objects.filter(category=category_id).order_by('-id')
	
	paginator = Paginator(posts, 10)
	page = request.GET.get('page')
    
	posts = paginator.get_page(page)

	context_posts = {
		'posts': posts,
		'categoty_search': categoty_search,
	}

	return render(request, 'all_posts.html', context_posts)


def show_post(request, pk):
	post = get_object_or_404(Post,pk=pk)
	brand = Category.objects.get(id=post.category)
	return render(request, 'post.html', {'post':post, 'brand':brand})


def list_category(request, pk:None):

	form = ListForm(request.POST)
	posts = Post.objects.filter(category=pk).order_by('-id')
	categoty_search = Category.objects.all()

	if request.method == 'POST':
		category_id = request.POST.get('brand_id_f')
		posts = Post.objects.filter(category=category_id_f).order_by('-id')
	
	paginator = Paginator(posts, 10)
	page = request.GET.get('page')
    
	posts = paginator.get_page(page)

	context_posts = {
		'posts': posts,
		'categoty_search': categoty_search,
	}

	return render(request, 'all_posts.html', context_posts)