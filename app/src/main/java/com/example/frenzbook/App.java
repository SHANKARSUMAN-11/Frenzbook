package com.example.frenzbook;
import android.app.Application;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class App extends Application {
    static Retrofit retrofit;
    @Override
    public void onCreate() {
        super.onCreate();
    }
    public static Retrofit getRetrofit(String baseUrl) {
        OkHttpClient client = new OkHttpClient.Builder().build();
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        return retrofit;
    }
}