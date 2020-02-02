package com.example.frenzbook;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.frenzbook.DTO.BaseResponse;
import com.example.frenzbook.DTO.Content;
import com.example.frenzbook.DTO.FriendsDTO;
import com.example.frenzbook.DTO.FriendsResponse;
import com.example.frenzbook.DTO.TimelineDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MyFriendsFragment extends Fragment implements FriendsAdapter.FriendsInterface {

   private RecyclerView recyclerView;
   private FriendsAdapter friendsAdapter;
   private List<FriendsDTO> friendsDTOList;
   private CardView card;
   private SharedPreferences sharedPreferences;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view =  inflater.inflate(R.layout.fragment_my_friends,container,false);
        recyclerView = view.findViewById(R.id.recycler);
        card=view.findViewById(R.id.card);
        Api api = App.getRetrofit(Api.BASE_URL_PROXY).create(Api.class);
        sharedPreferences = getActivity().getSharedPreferences("user_details", Context.MODE_PRIVATE);
        String userId = sharedPreferences.getString("user_id",null);
        Call<BaseResponse<List<FriendsDTO>>> call = api.getFriends(userId);
        call.enqueue(new Callback<BaseResponse<List<FriendsDTO>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<FriendsDTO>>> call, Response<BaseResponse<List<FriendsDTO>>> response) {
                if (response.body() != null)
                {
                    card.setVisibility(View.GONE);
                    friendsDTOList = response.body().getData();
                    friendsAdapter = new FriendsAdapter(MyFriendsFragment.this,friendsDTOList);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                    recyclerView.setAdapter(friendsAdapter);
                    recyclerView.setLayoutManager(layoutManager);

                }
            }

            @Override
            public void onFailure(Call<BaseResponse<List<FriendsDTO>>> call, Throwable t) {

                Log.e("aalia", t.getMessage() );

            }
        });


        return view;
    }


    @Override
    public void onClick(String userId,String url,String userName) {

        TimelineFragment timelineFragment = new TimelineFragment(userId,url,userName);
        FragmentManager fragmentManager2 = getActivity().getSupportFragmentManager();
        fragmentManager2.beginTransaction().replace(R.id.fragment,timelineFragment).commit();
    }
}
