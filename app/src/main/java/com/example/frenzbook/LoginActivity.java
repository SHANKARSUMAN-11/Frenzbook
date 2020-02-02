package com.example.frenzbook;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.example.frenzbook.DTO.LoginRequestDTO;
import com.example.frenzbook.DTO.LoginResponseDTO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class LoginActivity extends AppCompatActivity {
    private TextView mUserEmail;
    private TextView mUserPassword;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    public void login(View view) {
        mUserEmail = findViewById(R.id.email);
        mUserPassword = findViewById(R.id.password);
        if (!(mUserEmail.getText().toString().equals("") || mUserPassword.getText().toString().equals(""))) {
            Api api = App.getRetrofit(Api.BASE_URL_LOGIN).create(Api.class);
            final LoginRequestDTO loginRequestDTO = new LoginRequestDTO();
            loginRequestDTO.setEmail(mUserEmail.getText().toString());
            loginRequestDTO.setPassword(mUserPassword.getText().toString());
            sharedPreferences = getSharedPreferences("user_details", MODE_PRIVATE);
            Call<LoginResponseDTO> call = api.sendLoginCredentials(loginRequestDTO);
            call.enqueue(new Callback<LoginResponseDTO>()
            {
                @Override
                public void onResponse(Call<LoginResponseDTO> call, Response<LoginResponseDTO> response) {
                    if (response.body() != null) {
                        LoginResponseDTO accessTokenRegisterResponse = response.body();
                        String auth_token = accessTokenRegisterResponse.getTokenType() + " " + accessTokenRegisterResponse.getAccessToken();
                        if (!auth_token.equals(""))
                        {
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("auth_token", auth_token);
                            editor.apply();
                            Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this, SecondSignUp.class));
                            finish();
                        }
                    }
                }
                @Override
                public void onFailure(Call<LoginResponseDTO> call, Throwable t) {
                    Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(LoginActivity.this, "Login or Password can't be blank!", Toast.LENGTH_SHORT).show();
        }
    }
    public void SignUp(View view) {
        startActivity(new Intent(LoginActivity.this, SignUP.class));
    }
}