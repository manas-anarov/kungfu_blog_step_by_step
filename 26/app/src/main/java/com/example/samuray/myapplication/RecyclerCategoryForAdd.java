package com.example.samuray.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class RecyclerCategoryForAdd extends RecyclerView.Adapter<RecyclerCategoryForAdd.ViewHolder>{

    private static final String TAG = "RecyclerCategoryAdapter";

    private ArrayList<Integer> pkCategory = new ArrayList<>();
    private ArrayList<Integer> idCategory = new ArrayList<>();
    private ArrayList<String> nameCategory = new ArrayList<>();


    private Context mContext;

    public RecyclerCategoryForAdd(Context context, ArrayList<Integer> rId, ArrayList<String> rName, ArrayList<Integer> rCategoryId) {
        pkCategory = rId;

        idCategory = rCategoryId;
        nameCategory = rName;

        mContext = context;


    }

    @Override
    public RecyclerCategoryForAdd.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_list_item, parent, false);
        RecyclerCategoryForAdd.ViewHolder holder = new RecyclerCategoryForAdd.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerCategoryForAdd.ViewHolder holder, final int position) {


        holder.TCatId.setText(String.valueOf(pkCategory.get(position)));
        holder.TCatName.setText(nameCategory.get(position));

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                SharedPreferences myCategoryPrefs = mContext.getSharedPreferences("CategorySaved", MODE_PRIVATE);
                SharedPreferences.Editor prefCategoryEdit = myCategoryPrefs.edit();
                prefCategoryEdit.putInt("CategoryId", pkCategory.get(position));
                prefCategoryEdit.commit();

                ((AppCompatActivity)mContext).getSupportFragmentManager().popBackStack();



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