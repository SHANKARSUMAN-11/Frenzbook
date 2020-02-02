package com.example.frenzbook;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.frenzbook.DTO.ChildCommentItem;

import java.util.List;

public class ChildCommentAdapter extends RecyclerView.Adapter<ChildCommentAdapter.ViewHolder> {

    List<ChildCommentItem> childCommentItemList;

    public ChildCommentAdapter(List<ChildCommentItem> childCommentItems){
        this.childCommentItemList = childCommentItems;
    }

    @NonNull
    @Override
    public ChildCommentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ChildCommentAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_comment,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull ChildCommentAdapter.ViewHolder holder, final int position) {
        holder.name.setText(childCommentItemList.get(position).getUserName());
        Glide.with(holder.profileImage.getContext()).load(childCommentItemList.get(position).getImageUrl()).into(holder.profileImage);
        holder.comment.setText(childCommentItemList.get(position).getText());

    }

    @Override
    public int getItemCount() {
        return childCommentItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView comment;
        ImageView profileImage;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.username);
            profileImage = itemView.findViewById(R.id.userpic);
            comment = itemView.findViewById(R.id.parentComment);
        }
    }


}
