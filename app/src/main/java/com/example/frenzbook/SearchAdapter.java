package com.example.frenzbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.frenzbook.DTO.SecondSignUpDTO;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private Context context;
    private   ISearchCommunicator iSearchCommunicator;
  private   List<SecondSignUpDTO> secondSignUpDTOList;

    public SearchAdapter(Context context, ISearchCommunicator iSearchCommunicator, List<SecondSignUpDTO> secondSignUpDTOList) {
        this.context = context;
        this.iSearchCommunicator = iSearchCommunicator;
        this.secondSignUpDTOList = secondSignUpDTOList;
    }

    @NonNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        return new SearchAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_myfriends_item,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull final SearchAdapter.ViewHolder holder, int position)
    {
        holder.textView.setText(secondSignUpDTOList.get(position).getUserName());
        Glide.with(holder.imageView.getContext()).load(secondSignUpDTOList.get(position).getImageUrl()).into(holder.imageView);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iSearchCommunicator.onSearchItemClick(secondSignUpDTOList.get(holder.getAdapterPosition()).getUserId(),secondSignUpDTOList.get(holder.getAdapterPosition()).getImageUrl(),secondSignUpDTOList.get(holder.getAdapterPosition()).getUserName());
            }
        });
    }

    @Override
    public int getItemCount() {
        return secondSignUpDTOList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {

        TextView textView;
        ImageView imageView;
        CardView card_friends;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            this.textView = itemView.findViewById(R.id.friends_textview);
            this.imageView = itemView.findViewById(R.id.friends_imageview);
            this.card_friends= itemView.findViewById(R.id.card_friends);
        }
    }

    public interface ISearchCommunicator
    {
        void onSearchItemClick(String userId ,String imageUrl,String userName);
    }
}
