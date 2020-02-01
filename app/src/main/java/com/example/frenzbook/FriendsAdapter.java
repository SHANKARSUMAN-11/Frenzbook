package com.example.frenzbook;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.frenzbook.DTO.FriendsDTO;
import com.example.frenzbook.DTO.FriendsResponse;

import java.util.List;

import okhttp3.Response;

public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.ViewHolder> {

    FriendsInterface friendsInterface;
    List<FriendsDTO>friendsDTOList;

    public FriendsAdapter(FriendsInterface friendsInterface, List<FriendsDTO> friendsDTOList) {
        this.friendsInterface = friendsInterface;
        this.friendsDTOList = friendsDTOList;
    }

    @NonNull
    @Override
    public FriendsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_myfriends_item,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull final FriendsAdapter.ViewHolder holder, int position) {

        holder.textView.setText(friendsDTOList.get(position).getUserName());
        Glide.with(holder.imageView.getContext()).load(friendsDTOList.get(position).getImageUrl()).into(holder.imageView);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                friendsInterface.onClick(friendsDTOList.get(holder.getAdapterPosition()).getUserId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return friendsDTOList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textView = itemView.findViewById(R.id.friends_textview);
            this.imageView = itemView.findViewById(R.id.friends_imageview);
        }
    }

    public interface FriendsInterface
    {
        public void onClick(String userId);
    }
}
