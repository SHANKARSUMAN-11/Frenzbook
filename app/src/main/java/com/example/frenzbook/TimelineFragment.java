package com.example.frenzbook;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.frenzbook.DTO.BaseResponse;
import com.example.frenzbook.DTO.FriendRequestDTO;
import com.example.frenzbook.DTO.MutualFriendsDTO;
import com.example.frenzbook.DTO.ReactionDTO;
import com.example.frenzbook.DTO.TimelineDTO;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TimelineFragment extends Fragment implements TimelineAdapter.ContentInterface{

   private RecyclerView timeline;
   private List<TimelineDTO> contents;
   private TimelineAdapter timelineAdapter;
   private String userId;
   private String friendName;
   private String url;
   private String friendId;
   private TextView FriendName;
   private ImageView friendPic;
   private SharedPreferences sharedPreferences;
   private Call<BaseResponse<List<TimelineDTO>>> call;
   private Button addComment;
   private Button addFriend;
   private FriendRequestDTO friendRequestDTO;
   private MutualFriendsDTO mutualFriendsDTO;

    public TimelineFragment(String userId,String url,String userName){

       this.friendId = userId;
       this.friendName=userName;
       this.url=url;
    }


    public TimelineFragment()
    {


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_my_friends,container,false);
        timeline = view.findViewById(R.id.recycler);

        FriendName = view.findViewById(R.id.name);
        friendPic = view.findViewById(R.id.profile);

        sharedPreferences = getActivity().getSharedPreferences("user_details", Context.MODE_PRIVATE);
        userId = sharedPreferences.getString("user_id",null);


        addFriend = view.findViewById(R.id.addFriend);

        initAdapter();


        friendRequestDTO = new FriendRequestDTO();
        friendRequestDTO.setUserId(userId);
        friendRequestDTO.setFriendId(friendId);


        if (friendId != null) {
            Api api = App.getRetrofit(Api.USER_URL).create(Api.class);

            Call<BaseResponse<MutualFriendsDTO>> call1 = api.getMutualFriends(friendRequestDTO);
            call1.enqueue(new Callback<BaseResponse<MutualFriendsDTO>>() {
                @Override
                public void onResponse(Call<BaseResponse<MutualFriendsDTO>> call, Response<BaseResponse<MutualFriendsDTO>> response) {

                    if (response.body() != null) {
                        mutualFriendsDTO = response.body().getData();
                        if (!mutualFriendsDTO.getFriend()) {
                            addFriend.setVisibility(View.VISIBLE);
                        }
                    }
                }

                @Override
                public void onFailure(Call<BaseResponse<MutualFriendsDTO>> call, Throwable t) {
                    Log.i("Aalia", t.getMessage());
                }
            });

            addFriend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onAddFriendClick(friendRequestDTO);
                }
            });
        }


        Api api = App.getRetrofit(Api.POST_URL).create(Api.class);

        if(friendId==null) {
            call = api.getUsersAllPosts(userId);
        }
        else
        {
            call= api.getUsersAllPosts(friendId);
        }



        call.enqueue(new Callback<BaseResponse<List<TimelineDTO>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<TimelineDTO>>> call, Response<BaseResponse<List<TimelineDTO>>> response) {
                if(response.body()!=null)
                {
                    FriendName.setText(friendName);
                    Glide.with(friendPic.getContext())
                            .applyDefaultRequestOptions(new RequestOptions().placeholder(R.drawable.ic_launcher_foreground))
                            .load(url).into(friendPic);
                    contents.clear();
                    contents.addAll(response.body().getData());
                    timelineAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<BaseResponse<List<TimelineDTO>>> call, Throwable t)
            {
                Log.i("Aalia", t.getMessage());
            }
        });


        return view;
    }

    public void onAddFriendClick(FriendRequestDTO friendRequestDTO)
    {
        Api api = App.getRetrofit(Api.USER_URL).create(Api.class);
        Call<BaseResponse<String>> call = api.sendFriendRequest(friendRequestDTO);

        call.enqueue(new Callback<BaseResponse<String>>() {
            @Override
            public void onResponse(Call<BaseResponse<String>> call, Response<BaseResponse<String>> response) {
                if(response.body()!=null)
                {
                    if(response.isSuccessful())
                    {
                        Toast.makeText(getContext(), "You sent the friend Request!", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<String>> call, Throwable t) {
                Toast.makeText(getContext(), "Failed to send friend Request!", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void initAdapter() {
        contents = new ArrayList<>();
        timelineAdapter = new TimelineAdapter(TimelineFragment.this, contents);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        timeline.setLayoutManager(layoutManager);
        timeline.setAdapter(timelineAdapter);
    }

    @Override
    public void onClick(TimelineDTO timelineDTO, String type) {
        ReactionDTO reactionDTO = new ReactionDTO();
        reactionDTO.setUserId(timelineDTO.getPostDTO().getUserId());
        reactionDTO.setPostId(timelineDTO.getPostId());
        reactionDTO.setActivity(type);

        if(type=="Comment")
        {
            final String postId = timelineDTO.getPostDTO().getPostId();
            Intent intent = new Intent(getActivity(),CommentActivity.class);
            intent.putExtra("postIdComment",postId);
            startActivity(intent);
        }
        else if(type =="LikeCount")
        {
            final String postId = timelineDTO.getPostDTO().getPostId();
            Intent intent = new Intent(getContext(),ReactionActivity.class);
            intent.putExtra("postId",postId);
            startActivity(intent);
        }
        else {
            Api api = App.getRetrofit(Api.POST_URL).create(Api.class);
            Call<BaseResponse<String>> call = api.addReaction(reactionDTO);
            call.enqueue(new Callback<BaseResponse<String>>() {
                @Override
                public void onResponse(Call<BaseResponse<String>> call, Response<BaseResponse<String>> response) {
                    if(response.isSuccessful()) {
                        Toast.makeText(getContext(), "You liked the post!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<BaseResponse<String>> call, Throwable t) {
                    Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }

    }
}
