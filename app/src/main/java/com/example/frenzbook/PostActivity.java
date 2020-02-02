package com.example.frenzbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostActivity extends AppCompatActivity {
    private StorageReference mStorageRef;
    private ImageView img,vid,img1,videoshow;
    private Button post,up,videoupload;
    public Uri imageuri;
    private TextView post_input;
    private String imagelink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        mStorageRef = FirebaseStorage.getInstance().getReference("Images");
        img=(ImageView)findViewById(R.id.imageButton);
        img1=(ImageView)findViewById(R.id.imageView3);
        vid=(ImageView)findViewById(R.id.video_btn);
        up=findViewById(R.id.up);
        post=findViewById(R.id.buttonpost);
        post_input=findViewById(R.id.post_input);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileChooser();
            }
        });

        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageUploader();
            }
        });

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Content content=new Content(post_input.getText().toString(),imagelink,null);
                PostDTO postDTO=new PostDTO("personal","98",content);
                PostController.getRetrofit("http://172.16.20.82:8083").create(APInterface.class).pushPost(postDTO).enqueue(
                        new Callback<BaseResponse<String>>() {
                            @Override

                            public void onResponse(Call<BaseResponse<String>> call, Response<BaseResponse<String>> response) {
//
                                Toast.makeText(PostActivity.this,"Post uploaded successfully!",Toast.LENGTH_SHORT).show();
                                //startActivity(new Intent(PostActivity.this,Register2Activity.class));
                            }

                            @Override
                            public void onFailure(Call<BaseResponse<String>> call, Throwable t) {
                                Toast.makeText(PostActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        }
                        );
            }
        });
    }
    private void ImageUploader(){
        final StorageReference ref=mStorageRef.child(System.currentTimeMillis()+getExtension(imageuri));
        ref.putFile(imageuri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        //Uri downloadUrl = taskSnapshot.getDownloadUrl();
                        Toast.makeText(PostActivity.this, "Image uploaded successfully", Toast.LENGTH_LONG).show();
                        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                //secondSignUpDTO.setImageUrl(uri.toString());
                                imagelink=uri.toString();
                            }
                        });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(PostActivity.this,e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void FileChooser(){
        Intent intent=new Intent();
        intent.setType("image/");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);
    }

    private String getExtension(Uri uri){
        ContentResolver cr=getContentResolver();
        MimeTypeMap mimeTypeMap=MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(cr.getType(uri));
    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==1 && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            imageuri=data.getData();
            img1.setImageURI(imageuri);
         }
    }
}
