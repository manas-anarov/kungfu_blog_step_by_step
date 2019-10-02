package com.example.samuray.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.samuray.myapplication.model.PostModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShowPost extends Fragment {

    TextView v_sh_title;
    TextView v_sh_text;
    TextView v_sh_id;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.show_post, container, false);

        v_sh_title = (TextView) rootView.findViewById(R.id.vshow_title);
        v_sh_text = (TextView) rootView.findViewById(R.id.vshow_text);
        v_sh_id = (TextView) rootView.findViewById(R.id.vshow_id);


        Bundle bundle = this.getArguments();
        if (bundle != null) {
            Integer bundle_id = bundle.getInt("post_id");
            GetServerData(bundle_id);


        }


        getActivity().setTitle("Show Post");


        return rootView;

    }


    private void GetServerData(Integer getted_id) {


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
                        Integer v_sh_str_id = postValues.getId();

                        v_sh_title.setText(v_sh_str_title);
                        v_sh_text.setText(v_sh_str_text);
                        v_sh_id.setText(String.valueOf(v_sh_str_id));

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
}
