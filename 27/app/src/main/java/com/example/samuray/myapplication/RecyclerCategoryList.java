package com.example.samuray.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;




public class RecyclerCategoryList extends RecyclerView.Adapter<RecyclerCategoryList.ViewHolder>{

    private static final String TAG = "RecyclerCategoryList";

    private ArrayList<Integer> pkCategory = new ArrayList<>();
    private ArrayList<String> nameCategory = new ArrayList<>();


    private Context mContext;

    public RecyclerCategoryList(Context context, ArrayList<Integer> rId, ArrayList<String> rName) {
        pkCategory = rId;

        nameCategory = rName;

        mContext = context;
    }

    @Override
    public RecyclerCategoryList.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_list_item, parent, false);
        RecyclerCategoryList.ViewHolder holder = new RecyclerCategoryList.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerCategoryList.ViewHolder holder, final int position) {


        holder.TCatId.setText(String.valueOf(pkCategory.get(position)));
        holder.TCatName.setText(nameCategory.get(position));

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


//                Bundle bundle = new Bundle();
//                bundle.putInt("post_id",pkCategory.get(position)); // Put anything what you want
//                AppCompatActivity activity = (AppCompatActivity) view.getContext();
//                android.support.v4.app.Fragment myFragment = new CategoryById();
//                myFragment.setArguments(bundle);
//                activity.getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.fragment_container, myFragment)
//                        .addToBackStack(null)
//                        .commit();


            }
        });
    }

    @Override
    public int getItemCount() {
        return pkCategory.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView TCatId;
        TextView TCatName;

        RelativeLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            TCatId = itemView.findViewById(R.id.post_listitem_id);
            TCatName = itemView.findViewById(R.id.post_listitem_name);


            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }


}