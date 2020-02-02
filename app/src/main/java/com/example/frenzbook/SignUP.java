package com.example.frenzbook;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.example.frenzbook.DTO.SignUpRequestDTO;
import com.example.frenzbook.DTO.SignUpResponseDTO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class SignUP extends AppCompatActivity {
    private EditText emailEditext;
    private EditText passwordEditText;
    private EditText cpassword;
    private EditText nameEditText;
    private LinearLayout progressBarLinearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }
    public void Login(View view) {
        startActivity(new Intent(SignUP.this, LoginActivity.class));
    }
    public void SignUp(View view) {
        emailEditext = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        nameEditText = findViewById(R.id.name);
        cpassword = findViewById(R.id.cpassword);
        progressBarLinearLayout = findViewById(R.id.progressbar_layout);
        progressBarLinearLayout.setVisibility(View.VISIBLE);
        Api api = App.getRetrofit(Api.BASE_URL_LOGIN).create(Api.class);
        SignUpRequestDTO signUpRequestDTO = new SignUpRequestDTO();
        signUpRequestDTO.setEmail(emailEditext.getText().toString());
        signUpRequestDTO.setName(nameEditText.getText().toString());
        signUpRequestDTO.setPassword(passwordEditText.getText().toString());
        Call<SignUpResponseDTO> call = api.sendSignUpCredentials(signUpRequestDTO);
        call.enqueue(new Callback<SignUpResponseDTO>() {
            @Override
            public void onResponse(Call<SignUpResponseDTO> call, Response<SignUpResponseDTO> response) {
                if (response.body() != null) {
                    SignUpResponseDTO signUpResponseDTO = response.body();
                    if (signUpResponseDTO.isSuccess()) {
                        progressBarLinearLayout.setVisibility(View.GONE);
                        Toast.makeText(SignUP.this, "Sign Up successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SignUP.this, LoginActivity.class));
                        finish();
                    }
                }
            }
            @Override
            public void onFailure(Call<SignUpResponseDTO> call, Throwable t) {
                progressBarLinearLayout.setVisibility(View.GONE);
                Toast.makeText(SignUP.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}