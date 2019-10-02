package com.example.samuray.myapplication;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.samuray.myapplication.model.PostModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Edit extends Fragment  implements View.OnClickListener{

    EditText e_sh_title;
    EditText e_sh_text;

    Button btn_update;
    Button btn_delete;
    Integer vint_id_posta;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.edit_post, container, false);

        e_sh_title = (EditText) rootView.findViewById(R.id.eshow_title);
        e_sh_text = (EditText) rootView.findViewById(R.id.eshow_text);

        btn_update = (Button) rootView.findViewById(R.id.eshow_update);
        btn_delete = (Button) rootView.findViewById(R.id.eshow_delete);

        btn_update.setOnClickListener(this);
        btn_delete.setOnClickListener(this);


        Bundle bundle = this.getArguments();
        if (bundle != null) {
            Integer bundle_id = bundle.getInt("post_id");
            GetServerData(bundle_id);
        }


        return rootView;

    }


    @Override
    public void onClick(View view) {
        Fragment fragment = null;
        switch (view.getId()) {
//            case R.id.eshow_update:
//                onMyUpdateButtonClick();
//                break;
            case R.id.eshow_delete:
                onMyDeleteButtonClick();
                break;

        }
    }


    private void GetServerData(Integer getted_id) {


        vint_id_posta = getted_id;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PostApi.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        String data = String.valueOf(getted_id);

        PostApi postApi = retrofit.create(PostApi.class);

        Call<PostModel> call = postApi.getPost(data);

        call.enqueue(new Callback<PostModel>() {
            @Override
            public void onResponse(Call<PostModel> call, Response<PostModel> response) {

                if (response.isSuccessful()) {

                    if (response.body() != null) {

                        PostModel postValues = response.body();

                        String v_sh_str_title = postValues.getTitle();
                        String v_sh_str_text = postValues.getText();

                        e_sh_title.setText(v_sh_str_title);
                        e_sh_text.setText(v_sh_str_text);


                    }

                } else {
                    Log.d("fail", "fail");
                }

            }

            @Override
            public void onFailure(Call<PostModel> call, Throwable t) {
                Log.d("fail", t.getMessage() == null ? "" : t.getMessage());
            }
        });

    }


//    public void onMyUpdateButtonClick() {
//
//
//        if (InternetUtil.isInternetOnline(getActivity())) {
//
//            if (!IsEmptyEditTextProfileVoditel()) {
//                updatePost();
//                getActivity().getSupportFragmentManager().popBackStack();
//            }
//
//        }
//
//    }


//    private void updatePost() {
//
//
//        Integer upv_id_posta = 1;
//
//        String upv_title = e_sh_title.getText().toString();
//        String upv_text = e_sh_text.getText().toString();
//        Integer upv_category = 1;
//
//        String queryToken_ap = SharedDataGetSet.getMySavedToken(getActivity());
//
//
//
//        PostModel postModelUpdate = new PostModel(
//                upv_id_posta,
//                upv_title,
//                upv_text,
//                upv_category
//        );
//
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(PostApi.API_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        PostApi postApi = retrofit.create(PostApi.class);
//
//        String vstr_id_posta = String.valueOf(vint_id_posta);
//
//        Call<PostModel> call = postApi.updatePost(queryToken_ap, postModelUpdate, vstr_id_posta);
//
//        call.enqueue(new Callback<PostModel>() {
//            @Override
//            public void onResponse(Call<PostModel> call, Response<PostModel> response) {
//                Log.d("good", "good");
//            }
//
//            @Override
//            public void onFailure(Call<PostModel> call, Throwable t) {
//                Log.d("fail", "fail");
//            }
//        });
//
//    }


    private void onMyDeleteButtonClick() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(getString(R.string.title_delete_message));
        builder.setMessage(getString(R.string.delete_message));

        builder.setPositiveButton(getString(R.string.yes_delete_message), new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                deletePost();
                dialog.dismiss();
                getActivity().getSupportFragmentManager().popBackStack();


            }
        });

        builder.setNegativeButton(getString(R.string.no_delete_message), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();

    }


    private void deletePost() {

        String queryToken_ap = SharedDataGetSet.getMySavedToken(getActivity());


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PostApi.base_local)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        PostApi postApi = retrofit.create(PostApi.class);

        String vstr_id_posta = String.valueOf(vint_id_posta);

        Call<List<PostModel>> call = postApi.deletePost(queryToken_ap, vstr_id_posta);


        call.enqueue(new Callback<List<PostModel>>() {
            @Override
            public void onResponse(Call<List<PostModel>> call, Response<List<PostModel>> response) {
                Log.d("good", "good");
            }

            @Override
            public void onFailure(Call<List<PostModel>> call, Throwable t) {
                Log.d("fail", t.getMessage() == null ? "" : t.getMessage());
            }
        });

    }


    private Boolean IsEmptyEditTextProfileVoditel() {

        if (e_sh_title.getText().toString().isEmpty() || e_sh_text.getText().toString().isEmpty()) {
            Toast toast = Toast.makeText(getActivity(), "Edit Text is Empty", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();

            return true;

        } else {
            return false;
        }

    }



}


