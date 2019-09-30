package com.example.samuray.myapplication;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.samuray.myapplication.model.PostModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class PostAdd extends Fragment  implements View.OnClickListener {


    EditText E_add_title;
    EditText E_add_text;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {



        View rootView = inflater.inflate(R.layout.post_add, container, false);


        Button B_add_save_button = (Button) rootView.findViewById(R.id.add_save_button);
        Button B_add_category_button = (Button) rootView.findViewById(R.id.add_category_button);


        E_add_title = (EditText) rootView.findViewById(R.id.add_title);
        E_add_text = (EditText) rootView.findViewById(R.id.add_text);




        B_add_save_button.setOnClickListener(this);
        B_add_category_button.setOnClickListener(this);


        getActivity().setTitle("Add Post");


        return rootView;


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_save_button:
                SaveButtonClick();
                break;
            case R.id.add_category_button:
                CategoryButtonClick();
                break;
        }
    }



    public void replaceFragment(Fragment someFragment) {

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }


    public void SaveButtonClick()
    {

        if (!IsEmptyEditTextLogin()){

            if ( InternetUtil.isInternetOnline(getActivity()) ){
                Save();
            }

        }



    }

    public void CategoryButtonClick()
    {

        if ( InternetUtil.isInternetOnline(getActivity()) ){

            Fragment fragment = null;
            fragment = new CategoryForAdd();
            replaceFragment(fragment);

        }


    }



    public void Save(){


        SharedPreferences preferences = getActivity().getSharedPreferences("CategorySaved", Context.MODE_PRIVATE);
        Integer shered_category_id = preferences.getInt("CategoryId",  0);



        Integer add_id      =  1;
        int add_category      =  shered_category_id;
        String add_title      =  E_add_title.getText().toString();
        String add_text      =  E_add_text.getText().toString();



        PostModel postModelModel = new PostModel(
                add_id,
                add_title,
                add_text,
                add_category
        );


        if ( InternetUtil.isInternetOnline(getActivity()) ){
            AddPostServer(postModelModel);
        }

    }



    public void AddPostServer(PostModel postModelModel) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PostApi.POST_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        String queryToken_ap = SharedDataGetSet.getMySavedToken(getActivity());


        PostApi postApi= retrofit.create(PostApi.class);
        Call<PostModel> call = postApi.addPost(queryToken_ap, postModelModel);

        call.enqueue(new Callback<PostModel>() {
            @Override
            public void onResponse(Call<PostModel> call, Response<PostModel> response) {
                Log.d("good", "good");
            }
            @Override
            public void onFailure(Call<PostModel> call, Throwable t) {
                Log.d("fail", "fail");
            }
        });

    }





    private Boolean IsEmptyEditTextLogin(){

        if(E_add_title.getText().toString().isEmpty() || E_add_text.getText().toString().isEmpty()){

            Toast toast = Toast.makeText(getActivity(),"Empty EditText", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();

            return true;
        }else{
            return false;
        }

    }







}