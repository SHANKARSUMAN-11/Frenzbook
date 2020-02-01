package com.example.frenzbook;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.frenzbook.DTO.TimelineDTO;

import java.util.List;


public class TimelineAdapter extends RecyclerView.Adapter<TimelineAdapter.ViewHolder> {

    private ContentInterface contentInterface;
    private List<TimelineDTO> contents;


    public TimelineAdapter(ContentInterface contentInterface,List<TimelineDTO> contents)
    {
        this.contentInterface = contentInterface;
        this.contents = contents;
    }

    @NonNull
    @Override
    public TimelineAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TimelineAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_my_feed,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull final TimelineAdapter.ViewHolder holder, final int position) {


        holder.name.setText(contents.get(position).getUserName());
        holder.message.setText(contents.get(position).getUserName() + " " +contents.get(position).getMessage());
        if(!(contents.get(position).getPostDTO().getContent().getText() == null))
            holder.postContent.setText(String.valueOf(contents.get(position).getPostDTO().getContent().getText()));
        else{
            holder.postContent.setVisibility(View.GONE);
        }
        Glide.with(holder.profilePicture.getContext())
                .applyDefaultRequestOptions(new RequestOptions().placeholder(R.drawable.ic_launcher_foreground))
                .load(contents.get(position).getImageUrl()).into(holder.profilePicture);
        if(!(contents.get(position).getPostDTO().getContent().getImage() == null)) {
            Glide.with(holder.postImage.getContext())
                    .applyDefaultRequestOptions(new RequestOptions().placeholder(R.drawable.ic_launcher_foreground))
                    .load(contents.get(position).getPostDTO().getContent().getImage()).into(holder.postImage);
        }
        else {
            holder.postImage.setVisibility(View.GONE);
        }

        holder.postLikes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contentInterface.onClick(contents.get(holder.getAdapterPosition()),"LikeCount");
            }
        });
        holder.dislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contentInterface.onClick(contents.get(holder.getAdapterPosition()),"Dislike");
            }
        });
        holder.angry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contentInterface.onClick(contents.get(holder.getAdapterPosition()),"Angry");
            }
        });
        holder.wow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contentInterface.onClick(contents.get(holder.getAdapterPosition()),"wow");
            }
        });
        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            contentInterface.onClick(contents.get(holder.getAdapterPosition()),"Like");
            }
        });

    }

    @Override
    public int getItemCount() {
        return contents.size();
    }

    public interface ContentInterface{
        void onClick(TimelineDTO response, String type);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView postContent;
        TextView message;
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
            message = itemView.findViewById(R.id.message);
            postLikes = itemView.findViewById(R.id.LikeCount);
            like = itemView.findViewById(R.id.Like1);
            dislike = itemView.findViewById(R.id.Dislike1);
            wow = itemView.findViewById(R.id.Wow1);
            angry = itemView.findViewById(R.id.Angry1);
        }
    }
}
