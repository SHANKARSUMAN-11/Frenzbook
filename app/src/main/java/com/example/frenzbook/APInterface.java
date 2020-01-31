package com.example.frenzbook;

import android.graphics.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface APInterface {
@POST("/auth/signup")
Call<AccessTokenRegisterResponse>  postData(@Body credentials cred);

@POST("/auth/signin")
Call<AccessTokenRegisterResponse> postConf(@Body secondcredentials sec);


}
