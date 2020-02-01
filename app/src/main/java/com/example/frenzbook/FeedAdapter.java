package com.example.frenzbook;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder> {

    private FeedInterface feedInterface;


    @NonNull
    @Override
    public FeedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FeedAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_my_profile,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull FeedAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView postContent;
        ImageView postImage;
        TextView postLikes;
        ImageView profilePicture;
        ImageView like;
        ImageView dislike;
        ImageView wow;
        ImageView angry;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            postContent = itemView.findViewById(R.id.contentText);
            postImage = itemView.findViewById(R.id.contentPic);
            profilePicture = itemView.findViewById(R.id.profile);
            postLikes = itemView.findViewById(R.id.LikeCount);
            like = itemView.findViewById(R.id.Like1);
            dislike = itemView.findViewById(R.id.Dislike1);
            wow = itemView.findViewById(R.id.Wow1);
            angry = itemView.findViewById(R.id.Angry1);
        }
    }

    public interface FeedInterface
    {

    }
}
