package com.example.frenzbook;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.frenzbook.DTO.BaseResponse;
import com.example.frenzbook.DTO.ReactionShowResponse;

import java.util.List;

public class ReactionAdapter extends RecyclerView.Adapter<ReactionAdapter.ViewHolder> {

    private List<ReactionShowResponse> reactionList;
    @NonNull
    @Override
    public ReactionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_myfriends_item,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull ReactionAdapter.ViewHolder holder, int position) {
        holder.text.setText(reactionList.get(position).getUserName());
        Glide.with(holder.image.getContext()).load(reactionList.get(position).getImageUrl()).into(holder.image);

    }

    @Override
    public int getItemCount() {

        return reactionList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView text;
        ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            text = itemView.findViewById(R.id.friends_textview);
            image = itemView.findViewById(R.id.friends_imageview);
        }
    }


}
