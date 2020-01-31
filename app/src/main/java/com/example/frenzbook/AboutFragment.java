package com.example.frenzbook;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.frenzbook.DTO.FriendsDTO;
import com.example.frenzbook.DTO.UserData;
import com.example.frenzbook.DTO.UserResponse;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AboutFragment extends Fragment {

    private UserResponse userResponse;
    private TextView name;
    private TextView emailAddress;
    private TextView gender;
    private TextView mobile;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_about,container,false);

        Api api = App.getRetrofit().create(Api.class);
        name= view.findViewById(R.id.name);
        emailAddress = view.findViewById(R.id.email);
        gender = view.findViewById(R.id.gender);
        mobile = view.findViewById(R.id.mobile);

        Call<UserResponse> call = api.getUserInfo("1");
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {

                if(response.body()!=null)
                {
                    userResponse = response.body();
                    UserData userData = userResponse.getData();
                    name.setText(userData.getUserName());
                    emailAddress.setText(userData.getEmail());
                    gender.setText(userData.getGender());
                    mobile.setText(String.valueOf( userData.getMobileNumber()));
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

                Log.i("AALIA",t.getMessage());

            }
        });
        return view;
    }


}
