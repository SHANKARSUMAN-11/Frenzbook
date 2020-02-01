package com.example.frenzbook;

import com.example.frenzbook.DTO.BaseResponse;
import com.example.frenzbook.DTO.FriendsDTO;
import com.example.frenzbook.DTO.ReactionDTO;
import com.example.frenzbook.DTO.ReactionShowResponse;
import com.example.frenzbook.DTO.TimelineDTO;
import com.example.frenzbook.DTO.UserData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api {

    String BASE_URL = "http://172.16.20.172:8080/";

    @GET("user/getUserDetails/{id}")
    Call<BaseResponse<UserData>> getUserInfo(@Path("id") String id);

    @GET("user/getFriends/{userId}")
    public Call<BaseResponse<List<FriendsDTO>>> getFriends(@Path("userId") String id);

    @GET("post/user/timeline/{userId}")
    public Call<BaseResponse<List<TimelineDTO>>> getUsersAllPosts(@Path("userId") String id);

    @POST("/reaction/addActivity")
    public Call<BaseResponse<String>> addReaction(@Body ReactionDTO reactionDTO);

    @POST("/reaction/showReactionsByUserId")
    public Call<BaseResponse<ReactionShowResponse>> showReactionsByUserId(@Path("postId") String postId);
}
