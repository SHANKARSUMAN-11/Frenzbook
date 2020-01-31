package com.example.frenzbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login2Activity extends AppCompatActivity {

    Button button;

    EditText mEmail, mPassword,mName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
         button=findViewById(R.id.login2);
        mEmail = findViewById(R.id.email);
        mName=findViewById(R.id.name);
        mPassword = findViewById(R.id.password);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secondcredentials sec=new secondcredentials(mName.getText().toString(),mEmail.getText().toString(), mPassword.getText().toString());
                AnalyticsController.getRetrofit("http://172.16.20.32:8080").create(APInterface.class).postConf(sec).enqueue(
                        new Callback<AccessTokenRegisterResponse>() {
                            @Override
                            public void onResponse(Call<AccessTokenRegisterResponse> call, Response<AccessTokenRegisterResponse> response) {
                                Toast.makeText(Login2Activity.this,"Congrats!",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Login2Activity.this,MainActivity.class));
                            }
                            @Override
                            public void onFailure(Call<AccessTokenRegisterResponse> call, Throwable t) {
                                Toast.makeText(Login2Activity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        }
                );
            }
        });


    }
}
