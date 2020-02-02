package com.example.frenzbook;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.frenzbook.DTO.BaseResponse;
import com.example.frenzbook.DTO.FriendsDTO;
import com.example.frenzbook.DTO.UserData;
import com.example.frenzbook.DTO.UserResponse;

import org.w3c.dom.Text;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AboutFragment extends Fragment {

    private UserData userData;
    private TextView name;
    private TextView emailAddress;
    private TextView gender;
    private ImageView photo;
    private SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_about,container,false);

        Api api = App.getRetrofit(Api.BASE_URL_PROXY).create(Api.class);
        name= view.findViewById(R.id.name);
        emailAddress = view.findViewById(R.id.email);
        gender = view.findViewById(R.id.gender);
        photo=view.findViewById(R.id.photo);
        sharedPreferences= this.getActivity().getSharedPreferences("user_details", Context.MODE_PRIVATE);
        String userId = sharedPreferences.getString("user_id",null);
        Call<BaseResponse<UserData>> call = api.getUserInfo(userId);

      call.enqueue(new Callback<BaseResponse<UserData>>() {
          @Override
          public void onResponse(Call<BaseResponse<UserData>> call, Response<BaseResponse<UserData>> response)
          {

if (response.body() != null)
              userData = response.body().getData();
              Glide.with(photo.getContext()).load(userData.getImageUrl()).into(photo);
              name.setText(userData.getUserName());
              emailAddress.setText(userData.getEmail());
              gender.setText(userData.getGender());
          }

          @Override
          public void onFailure(Call<BaseResponse<UserData>> call, Throwable t) {
              Log.e("Aalia", t.getMessage() );
          }
      });


        return view;
    }


}
