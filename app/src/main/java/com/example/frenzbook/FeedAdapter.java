package com.example.frenzbook;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.frenzbook.DTO.Ad;
import com.example.frenzbook.DTO.FeedResponse;
import com.example.frenzbook.DTO.TimelineDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder> {

    private FeedInterface feedInterface;
    private List<FeedResponse> feedResponses;
    private List<Ad> Ads;

    public FeedAdapter(FeedInterface feedInterface, List<FeedResponse> feedResponses, List<Ad> ads) {
        this.feedInterface = feedInterface;
        this.feedResponses = feedResponses;
        this.Ads = ads;
    }

    @NonNull
    @Override
    public FeedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FeedAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_my_feed,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull final FeedAdapter.ViewHolder holder, final int position) {

        if((position%5==0) && position!=0)
        {
            Glide.with(holder.AdView.getContext())
                    .applyDefaultRequestOptions(new RequestOptions().placeholder(R.drawable.ic_launcher_foreground))
                    .load(Ads.get(position).getImageUrl()).into(holder.postImage);
        }

        holder.name.setText(feedResponses.get(position).getUserName());
        if(feedResponses.get(position).getFriendName() == null) {
            holder.message.setText(feedResponses.get(position).getUserName() + " " + feedResponses.get(position).getActivity());
        }
        else{
            holder.message.setText(feedResponses.get(position).getUserName() + " " + feedResponses.get(position).getActivity()+" "+feedResponses.get(position).getFriendName());
        }
        Glide.with(holder.profilePicture.getContext())
                .applyDefaultRequestOptions(new RequestOptions().placeholder(R.drawable.ic_launcher_foreground))
                .load(feedResponses.get(position).getImageUrl()).into(holder.profilePicture);

        if(!(feedResponses.get(position).getPostDTO().getContent().getImage() == null)) {
            Glide.with(holder.postImage.getContext())
                    .applyDefaultRequestOptions(new RequestOptions().placeholder(R.drawable.ic_launcher_foreground))
                    .load(feedResponses.get(position).getPostDTO().getContent().getImage()).into(holder.postImage);
        }
        else {
            holder.postImage.setVisibility(View.GONE);
        }

        holder.AdView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feedInterface.onClick(feedResponses.get(holder.getAdapterPosition()),"AdView");
            }
        });
        holder.postLikes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feedInterface.onClick(feedResponses.get(holder.getAdapterPosition()),"LikeCount");
            }
        });
        holder.dislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feedInterface.onClick(feedResponses.get(holder.getAdapterPosition()),"Dislike");
            }
        });
        holder.angry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feedInterface.onClick(feedResponses.get(holder.getAdapterPosition()),"Angry");
            }
        });
        holder.wow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feedInterface.onClick(feedResponses.get(holder.getAdapterPosition()),"wow");
            }
        });
        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                feedInterface.onClick(feedResponses.get(holder.getAdapterPosition()),"Like");
            }
        });
        holder.comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                feedInterface.onClick(feedResponses.get(holder.getAdapterPosition()),"Comment");
            }
        });







    }

    @Override
    public int getItemCount() {
        return feedResponses.size();
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
        ImageView comment;
        private ImageView AdView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            postContent = itemView.findViewById(R.id.contentText);
            message = itemView.findViewById(R.id.message);
            postImage = itemView.findViewById(R.id.contentPic);
            profilePicture = itemView.findViewById(R.id.profile);
            postLikes = itemView.findViewById(R.id.LikeCount);
            like = itemView.findViewById(R.id.Like1);
            dislike = itemView.findViewById(R.id.Dislike1);
            wow = itemView.findViewById(R.id.Wow1);
            angry = itemView.findViewById(R.id.Angry1);
            comment=itemView.findViewById(R.id.comment);
            AdView=itemView.findViewById(R.id.ads);

        }
    }

    public interface FeedInterface
    {
        void onClick(FeedResponse response, String type);
    }
}
