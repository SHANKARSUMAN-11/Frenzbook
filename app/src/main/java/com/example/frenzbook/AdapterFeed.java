package com.example.frenzbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AdapterFeed extends RecyclerView.Adapter<AdapterFeed.MyViewHolder> {

    Context context;
    ArrayList<ModelFeed>  modelFeedArrayList=new ArrayList<>();

    public AdapterFeed(Context context,ArrayList<ModelFeed> modelFeeds){
        this.context=context;
        this.modelFeedArrayList=modelFeeds;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_feed_2,parent,false);
        MyViewHolder viewHolder=new MyViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final ModelFeed modelFeed=modelFeedArrayList.get(position);

        holder.tv_name.setText(modelFeed.getName());
        holder.tv_likes.setText(String.valueOf(modelFeed.getLikes()));
        holder.tv_comments.setText(String.valueOf(modelFeed.getComments()));
        holder.tv_status.setText(modelFeed.getStatus());
        Glide.with(holder.imgView_proPic).load(modelFeed.getPropic()).into(holder.imgView_proPic);
        if(modelFeed.getPostpic()==0){
           holder.imgView_postPic.setVisibility(View.GONE);
        }
        else {
            holder.imgView_postPic.setVisibility(View.VISIBLE);
            Glide.with(holder.imgView_postPic).load(modelFeed.getPostpic()).into(holder.imgView_postPic);

        }
    }


    @Override
    public int getItemCount() {
        return modelFeedArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name,tv_likes,tv_comments,tv_status;
        ImageView imgView_proPic,imgView_postPic;

        public MyViewHolder(View itemView){
            super(itemView);
            imgView_proPic=(ImageView)itemView.findViewById(R.id.imgView_proPic);
            imgView_postPic=(ImageView)itemView.findViewById(R.id.imgView_postPic);
            tv_name=(TextView) itemView.findViewById(R.id.tv_name);
            tv_likes=(TextView) itemView.findViewById(R.id.tv_likes);
            tv_comments=(TextView) itemView.findViewById(R.id.tv_comments);
            tv_status=(TextView) itemView.findViewById(R.id.tv_status);
        }
    }
}
