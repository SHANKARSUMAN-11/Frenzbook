package com.example.frenzbook;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.frenzbook.DTO.BaseResponse;
import com.example.frenzbook.DTO.FriendsDTO;
import com.example.frenzbook.DTO.FriendsResponse;
import com.example.frenzbook.DTO.TimelineDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MyFriendsFragment extends Fragment implements FriendsAdapter.FriendsInterface {

    RecyclerView recyclerView;
    FriendsAdapter friendsAdapter;
    List<FriendsDTO> friendsDTOList;
    RecyclerView.LayoutManager layoutManager;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view =  inflater.inflate(R.layout.fragment_my_friends,container,false);
        recyclerView = view.findViewById(R.id.recycler);

        Api api = App.getRetrofit().create(Api.class);
        Call<BaseResponse<List<FriendsDTO>>> call = api.getFriends("1");
        call.enqueue(new Callback<BaseResponse<List<FriendsDTO>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<FriendsDTO>>> call, Response<BaseResponse<List<FriendsDTO>>> response) {
                if (response.body() != null)
                {
                    friendsDTOList = response.body().getData();
                    friendsAdapter = new FriendsAdapter(MyFriendsFragment.this,friendsDTOList);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                    recyclerView.setAdapter(friendsAdapter);
                    recyclerView.setLayoutManager(layoutManager);

                }
            }

            @Override
            public void onFailure(Call<BaseResponse<List<FriendsDTO>>> call, Throwable t) {

            }
        });


        return view;
    }


    @Override
    public void onClick(String userId) {

        TimelineFragment timelineFragment = new TimelineFragment(userId);
        FragmentManager fragmentManager2 = getActivity().getSupportFragmentManager();
        fragmentManager2.beginTransaction().replace(R.id.fragment,timelineFragment).commit();
    }
}
