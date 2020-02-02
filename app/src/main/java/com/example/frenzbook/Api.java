package com.example.frenzbook;

import com.example.frenzbook.DTO.AddComment;
import com.example.frenzbook.DTO.BaseResponse;
import com.example.frenzbook.DTO.ChildCommentItem;
import com.example.frenzbook.DTO.Comment;
import com.example.frenzbook.DTO.FriendsDTO;
import com.example.frenzbook.DTO.GetUserDetailsDTO;
import com.example.frenzbook.DTO.LoginRequestDTO;
import com.example.frenzbook.DTO.LoginResponseDTO;
import com.example.frenzbook.DTO.ReactionDTO;
import com.example.frenzbook.DTO.ReactionShowResponse;
import com.example.frenzbook.DTO.SecondLoginRequestDTO;
import com.example.frenzbook.DTO.SecondSignUpDTO;
import com.example.frenzbook.DTO.SignUpRequestDTO;
import com.example.frenzbook.DTO.SignUpResponseDTO;
import com.example.frenzbook.DTO.TimelineDTO;
import com.example.frenzbook.DTO.UserData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api {

    String BASE_URL_PROXY = "http://172.16.20.172:8080";
    String BASE_URL_LOGIN = "http://172.16.20.32:8080";

    @GET("user/getUserDetails/{id}")
    Call<BaseResponse<UserData>> getUserInfo(@Path("id") String id);

    @GET("user/getFriends/{userId}")
    public Call<BaseResponse<List<FriendsDTO>>> getFriends(@Path("userId") String id);

    @GET("post/user/timeline/{userId}")
    public Call<BaseResponse<List<TimelineDTO>>> getUsersAllPosts(@Path("userId") String id);

    @POST("/reaction/addActivity")
    public Call<BaseResponse<String>> addReaction(@Body ReactionDTO reactionDTO);

    @POST("/reaction/showReactions/{postId}")
    public Call<BaseResponse<List<ReactionShowResponse>>> showReactionsByUserId(@Path("postId") String postId);

    @GET("comment/viewCommentByPost/{postId}")
    public Call<BaseResponse<List<Comment>>> getCommentByPostId(@Path("postId") String postId);

    @POST("comment/addComment")
    public Call<BaseResponse<String>> addComment(@Body AddComment addComment);

    @GET("comment/viewCommentByParentId/{parentCommentId}")
    public Call<BaseResponse<List<ChildCommentItem>>> getCommentByParentId(@Path("parentCommentId") String parentCommentId);

    @POST("/auth/signin")
    Call<LoginResponseDTO> sendLoginCredentials(@Body LoginRequestDTO loginRequestDTO);

    @POST("/auth/signup")
    Call<SignUpResponseDTO> sendSignUpCredentials(@Body SignUpRequestDTO signUpRequestDTO);

    @POST("/jwt/getUserDetails")
    Call<GetUserDetailsDTO>getUserDetails(@Body SecondLoginRequestDTO secondLoginRequestDTO, @Header("authorization") String authToken);

    @POST("/user/editDetails")
    Call<BaseResponse<SecondSignUpDTO>>editUserDetails(@Body SecondSignUpDTO secondSignUpDTO, @Header("Auth") String authToken);

    @GET("/search/getAll/{search}")
    Call<List<SecondSignUpDTO>> getByName(@Path("search") String search);

}
