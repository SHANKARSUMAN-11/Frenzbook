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

public class MainActivity extends AppCompatActivity {
    Button button,button1;

    EditText mEmail, mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.register);
        button1=findViewById(R.id.login);
        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Login2Activity.class));
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                credentials cred=new credentials(mEmail.getText().toString(), mPassword.getText().toString());
                AnalyticsController.getRetrofit("http://172.16.20.32:8080").create(APInterface.class).postData(cred).enqueue(
                        new Callback<AccessTokenRegisterResponse>() {
                            @Override
                            public void onResponse(Call<AccessTokenRegisterResponse> call, Response<AccessTokenRegisterResponse> response) {
                                Toast.makeText(MainActivity.this,"Congrats!",Toast.LENGTH_SHORT).show();

                                startActivity(new Intent(MainActivity.this,Register2Activity.class));
                            }

                            @Override
                            public void onFailure(Call<AccessTokenRegisterResponse> call, Throwable t) {
                                Toast.makeText(MainActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();

                            }
                        }
                );
            }
        });


    }
}
