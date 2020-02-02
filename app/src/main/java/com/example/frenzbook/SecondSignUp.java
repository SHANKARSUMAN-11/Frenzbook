package com.example.frenzbook;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.example.frenzbook.DTO.BaseResponse;
import com.example.frenzbook.DTO.GetUserDetailsDTO;
import com.example.frenzbook.DTO.SecondLoginRequestDTO;
import com.example.frenzbook.DTO.SecondSignUpDTO;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class SecondSignUp extends AppCompatActivity {
    private static final int REQUEST_CODE = 1;
    private StorageReference mStorageRef;
    private Uri imageUri;
    private ImageView imgageView;
    private ImageView dobImageView;
    private TextView dobTextView;
    private SharedPreferences sharedPreferences;
    private String authToken;
    private SecondSignUpDTO secondSignUpDTO;
    private RadioGroup radioGroupGender;
    private RadioGroup radioGroupDisplayType;
    private RadioGroup radioGroupProfileType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_sign_up);
        mStorageRef = FirebaseStorage.getInstance().getReference();
        imgageView = findViewById(R.id.image_dp);
        dobImageView = findViewById(R.id.dob_image);
        dobTextView = findViewById(R.id.dob_textview);
        sharedPreferences = getSharedPreferences("user_details", MODE_PRIVATE);
        authToken = sharedPreferences.getString("auth_token", null);
        secondSignUpDTO = new SecondSignUpDTO();
        radioGroupGender = findViewById(R.id.radio_group_gender);
        radioGroupProfileType = findViewById(R.id.radio_group_profile);
        radioGroupDisplayType= findViewById(R.id.radio_group_display_type);
        radioGroupGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton =  group.findViewById(checkedId);
                if (null != radioButton && checkedId !=-1) {
                    //Toast.makeText(SecondSignUp.this, radioButton.getText().toString(), Toast.LENGTH_SHORT).show();
                    secondSignUpDTO.setGender(radioButton.getText().toString());
                }
            }
        });
        radioGroupProfileType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton =  group.findViewById(checkedId);
                if (null != radioButton && checkedId !=-1) {
                    //Toast.makeText(SecondSignUp.this, radioButton.getText().toString(), Toast.LENGTH_SHORT).show();
                    secondSignUpDTO.setProfileType(radioButton.getText().toString());
                }
            }
        });
        radioGroupDisplayType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton =  group.findViewById(checkedId);
                if (null != radioButton && checkedId !=-1) {
                    //Toast.makeText(SecondSignUp.this, radioButton.getText().toString(), Toast.LENGTH_SHORT).show();
                    secondSignUpDTO.setDisplayType(radioButton.getText().toString());
                }
            }
        });
        List<String> interests = new ArrayList<>();
        interests.add("sports");
        secondSignUpDTO.setInterests(interests);
        dobImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(SecondSignUp.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                dobTextView.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                secondSignUpDTO.setDOB(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
    }
    public void uploadImage(View view) {
        Intent intent = new Intent();
        intent.setType("image/");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, REQUEST_CODE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            imgageView.setImageURI(imageUri);
            final StorageReference ref = mStorageRef.child(System.currentTimeMillis() + getExtension(imageUri));
            ref.putFile(imageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri)
                                {
                                    secondSignUpDTO.setImageUrl(uri.toString());
                                }
                            });
                            Toast.makeText(SecondSignUp.this, "Image uploaded successfully", Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                            Toast.makeText(SecondSignUp.this, "Upload is " + progress + "% done", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(SecondSignUp.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
    private String getExtension(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }
    public void SecondRegistration(View view) {
        final SecondLoginRequestDTO secondLoginRequestDTO = new SecondLoginRequestDTO();
        Api api = App.getRetrofit(Api.BASE_URL_LOGIN).create(Api.class);
        Call<GetUserDetailsDTO> call = api.getUserDetails(secondLoginRequestDTO, authToken);
        call.enqueue(new Callback<GetUserDetailsDTO>() {
            @Override
            public void onResponse(Call<GetUserDetailsDTO> call, Response<GetUserDetailsDTO> response) {
                if (response.body() != null) {
                    GetUserDetailsDTO getUserDetailsDTO = response.body();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    if (getUserDetailsDTO.getId()!=null)
                        editor.putString("user_id", getUserDetailsDTO.getId().toString());
                    editor.putString("auth_token",getUserDetailsDTO.getAccessToken());
                    editor.putString("name", getUserDetailsDTO.getName());
                    editor.putString("email", getUserDetailsDTO.getEmail());
                    editor.apply();
                    secondSignUpDTO.setUserId(getUserDetailsDTO.getId().toString());
                    secondSignUpDTO.setEmail(getUserDetailsDTO.getEmail());
                    secondSignUpDTO.setUserName(getUserDetailsDTO.getName());
                    Api api1 = App.getRetrofit(Api.BASE_URL_PROXY).create(Api.class);
                    Call<BaseResponse<SecondSignUpDTO>> responseCall = api1.editUserDetails(secondSignUpDTO,authToken);
                    responseCall.enqueue(new Callback<BaseResponse<SecondSignUpDTO>>() {
                        @Override
                        public void onResponse(Call<BaseResponse<SecondSignUpDTO>> call, Response<BaseResponse<SecondSignUpDTO>> response) {
                            if (response.body()!= null)
                            {
                                if (response.body().isSuccess())
                                {
                                    Toast.makeText(getApplicationContext(),"Registered Successfully",Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(SecondSignUp.this,LandingPageDrawer.class));
                                }
                            }
                        }
                        @Override
                        public void onFailure(Call<BaseResponse<SecondSignUpDTO>> call, Throwable t)
                        {
                            Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
            @Override
            public void onFailure(Call<GetUserDetailsDTO> call, Throwable t) {
                Toast.makeText(SecondSignUp.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}