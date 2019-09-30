package com.example.samuray.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.samuray.myapplication.model.CategoryModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class CategoryById extends Fragment {


    Integer CategoryUrl;
    private static final String TAG = "CategoryList";

    private ArrayList<Integer> pkCategory = new ArrayList<>();
    private ArrayList<Integer> idCategory = new ArrayList<>();
    private ArrayList<String> nameCategory = new ArrayList<>();

    private RecyclerView recyclerView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.post_list, container, false);

        recyclerView = rootView.findViewById(R.id.recycler_category_list);

        if ( InternetUtil.isInternetOnline(getActivity()) ){

            Bundle bundle = this.getArguments();
            if (bundle != null) {
                CategoryUrl = bundle.getInt("post_id");
            }
        }


        getActivity().setTitle("All posts in Category");

        return rootView;

    }


    private void showAllPosts(Integer catId) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PostApi.POST_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PostApi postApi= retrofit.create(PostApi.class);
        Call<List<CategoryModel>> call = postApi.getCategoryById(catId);

        call.enqueue(new Callback<List<CategoryModel>>() {
            @Override
            public void onResponse(Call<List<CategoryModel>> call, Response<List<CategoryModel>> response) {


                if(response.isSuccessful()){

                    if (response.body() != null) {
                        List<CategoryModel> catList = response.body();

                        for(CategoryModel h:catList){

                            Integer cat_id = h.getId();
                            pkCategory.add(cat_id);

                            String cat_name = h.getName();
                            nameCategory.add(cat_name);

                            Integer cat_id_category = h.getIdCategory();
                            idCategory.add(cat_id_category);

                        }

                        initRecyclerView();

                    }

                }else {
                    Log.d("fail", "fail");
                }
            }

            @Override
            public void onFailure(Call<List<CategoryModel>> call, Throwable t) {
                Log.d("fail", t.getMessage() == null ? "" : t.getMessage());
            }

        });

    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview.");
        RecyclerCategoryById adapter = new RecyclerCategoryById(getActivity(), pkCategory,  nameCategory);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }


    public void ClearList()
    {
        pkCategory.clear();
        idCategory.clear();
        nameCategory.clear();

        RecyclerCategoryById adapter = new RecyclerCategoryById(getActivity(), pkCategory,  nameCategory);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

    }


    @Override
    public void onResume() {
        super.onResume();

        if ( InternetUtil.isInternetOnline(getActivity()) ){
            ClearList();
            showAllPosts(CategoryUrl);
        }

    }

}