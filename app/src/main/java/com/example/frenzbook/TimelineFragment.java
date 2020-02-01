package com.example.frenzbook;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.frenzbook.DTO.BaseResponse;
import com.example.frenzbook.DTO.ReactionDTO;
import com.example.frenzbook.DTO.ReactionShowResponse;
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
   private String userName;

   public TimelineFragment(String userId){
       userName = userId;
    }


    public TimelineFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_my_friends,container,false);
        timeline = view.findViewById(R.id.recycler);
        initAdapter();
        Api api = App.getRetrofit().create(Api.class);
        Call<BaseResponse<List<TimelineDTO>>> call = api.getUsersAllPosts(userName);

        call.enqueue(new Callback<BaseResponse<List<TimelineDTO>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<TimelineDTO>>> call, Response<BaseResponse<List<TimelineDTO>>> response) {
                if(response.body()!=null)
                {
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

        Api api = App.getRetrofit().create(Api.class);
        Call<BaseResponse<String>> call = api.addReaction(reactionDTO);
        call.enqueue(new Callback<BaseResponse<String>>() {
            @Override
            public void onResponse(Call<BaseResponse<String>> call, Response<BaseResponse<String>> response)
            {
                Toast.makeText(getContext(),"You liked the post!",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<BaseResponse<String>> call, Throwable t)
            {
                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        final String postId = timelineDTO.getPostDTO().getPostId();
        Call<BaseResponse<ReactionShowResponse>> call1 = api.showReactionsByUserId(postId);

        call1.enqueue(new Callback<BaseResponse<ReactionShowResponse>>() {
            @Override
            public void onResponse(Call<BaseResponse<ReactionShowResponse>> call, Response<BaseResponse<ReactionShowResponse>> response) {
                Intent intent = new Intent(getActivity(),ReactionActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<BaseResponse<ReactionShowResponse>> call, Throwable t) {
                Log.e("aalia", t.getMessage() );
            }
        });

    }
}
