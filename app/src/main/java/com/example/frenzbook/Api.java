package com.example.frenzbook;

import com.example.frenzbook.DTO.FriendsResponse;
import com.example.frenzbook.DTO.UserResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Api {

    String BASE_URL = "http://10.177.68.178:8082";

    @GET("user/getUserDetails/{id}")
    Call<UserResponse> getUserInfo(@Path("id") String id);

    @GET("user/getFriends/{userId}")
    public Call<FriendsResponse> getFriends(@Path("userId") String id);

    
}
