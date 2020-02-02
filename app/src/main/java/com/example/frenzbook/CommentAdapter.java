package com.example.frenzbook;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.frenzbook.DTO.BaseResponse;
import com.example.frenzbook.DTO.ChildCommentItem;
import com.example.frenzbook.DTO.Comment;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

    List<Comment> comments;
    CommentInterface commentInterface;

    public CommentAdapter(List<Comment> comments)
    {
        this.comments = comments;
    }

    @NonNull
    @Override
    public CommentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_comment,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull final CommentAdapter.ViewHolder holder, final int position) {

        holder.name.setText(comments.get(position).getUserName());
        Glide.with(holder.profileImage.getContext()).load(comments.get(position).getImageUrl()).into(holder.profileImage);
        holder.comment.setText(comments.get(position).getText());
        final List<ChildCommentItem> childCommentItem = comments.get(position).getChildComment();
        if(childCommentItem!=null)
        {
            holder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    commentInterface.onParentCommentClick(comments.get(position).getParentCommentId(),comments.get(position).getPostId());
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView comment;
        ImageView profileImage;
        LinearLayout layout;

        public ViewHolder(View inflate) {
            super(inflate);
            name = inflate.findViewById(R.id.username);
            profileImage = inflate.findViewById(R.id.userpic);
            comment = inflate.findViewById(R.id.parentComment);
            layout = inflate.findViewById(R.id.comment_layout);
        }
    }

    public interface CommentInterface
    {
        public void onParentCommentClick(String parentCommentId,String postId);
    }
}
