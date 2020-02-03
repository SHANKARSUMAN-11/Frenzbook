package com.example.frenzbook;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.frenzbook.DTO.Ad;
import com.example.frenzbook.DTO.AddPostDTO;
import com.example.frenzbook.DTO.BaseResponse;
import com.example.frenzbook.DTO.Content;
import com.example.frenzbook.DTO.FeedResponse;
import com.example.frenzbook.DTO.PostDTO;
import com.example.frenzbook.DTO.ReactionDTO;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;


public class FeedFragment extends Fragment implements FeedAdapter.FeedInterface {


    private SharedPreferences sharedPreferences;
    private RecyclerView.LayoutManager linear;
    RecyclerView feedRecycler;
    FeedAdapter feedAdapter;
    String userId;
    private static final int REQUEST_CODE = 1;
    private EditText postEditText;
    private ImageView uploadImage;
    private Button postButton;
    private StorageReference mStorageRef;
    private Uri imageUri;
    private AddPostDTO addPostDTO;
    private Content content;
    private List<Ad> ads = new ArrayList<>();
    private List<FeedResponse> feedResponse;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_feed, container, false);
        sharedPreferences = getActivity().getSharedPreferences("user_details", Context.MODE_PRIVATE);
        userId = sharedPreferences.getString("user_id", null);
        feedResponse = new ArrayList<>();
        uploadImage = view.findViewById(R.id.upload_image_icon);
        postButton = view.findViewById(R.id.post_button);
        mStorageRef = FirebaseStorage.getInstance().getReference();
        addPostDTO = new AddPostDTO();
        content = new Content();
        // sharedPreferences = getSharedPreferences("user_details",MODE_PRIVATE);
        String userId = sharedPreferences.getString("user_id", null);
        String authToken = sharedPreferences.getString("auth_token", null);
        addPostDTO.setUserId(userId);
        addPostDTO.setCategory("personal");


        feedRecycler = view.findViewById(R.id.feed_recycler);
        feedAdapter = new FeedAdapter(FeedFragment.this, feedResponse, ads);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        feedRecycler.setLayoutManager(layoutManager);
        feedRecycler.setAdapter(feedAdapter);


        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postEditText = view.findViewById(R.id.addPostEditText);
                content.setText(postEditText.getText().toString());
                addPostDTO.setContent(content);
                Api api = App.getRetrofit(Api.BASE_URL_PROXY).create(Api.class);
                Call<BaseResponse<String>> call = api.addPost(addPostDTO);
                call.enqueue(new Callback<BaseResponse<String>>() {
                    @Override
                    public void onResponse(Call<BaseResponse<String>> call, Response<BaseResponse<String>> response) {
                        if (response.body() != null) {
                            if (response.body().isSuccess()) {
                                Toast.makeText(getContext(), "Post Added Successfully", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<BaseResponse<String>> call, Throwable t) {
                        Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        Api api = App.getRetrofit(Api.ADS_URL).create(Api.class);
        Call<List<Ad>> addCall = api.getAds(authToken, 1l);

        addCall.enqueue(new Callback<List<Ad>>() {
            @Override
            public void onResponse(Call<List<Ad>> call, Response<List<Ad>> response) {
                if (response.body() != null)
                {
                    ads.clear();
                    ads.addAll(response.body());
                    feedAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<List<Ad>> call, Throwable t) {
                Log.i("Aalia", t.getMessage());

            }
        });

        Api api1 = App.getRetrofit(Api.FEED_URL).create(Api.class);
        Call<BaseResponse<List<FeedResponse>>> call = api1.createFeed(userId);
        call.enqueue(new Callback<BaseResponse<List<FeedResponse>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<FeedResponse>>> call, Response<BaseResponse<List<FeedResponse>>> response) {

                if (response.body() != null) {
                    feedResponse.clear();
                    feedResponse.addAll(response.body().getData());
                    feedAdapter.notifyDataSetChanged();


                }
            }

            @Override
            public void onFailure(Call<BaseResponse<List<FeedResponse>>> call, Throwable t) {

                Log.i("Aalia", t.getMessage());
            }
        });

        return view;
    }

    @Override
    public void onClick(FeedResponse response, String type) {
        ReactionDTO reactionDTO = new ReactionDTO();
        reactionDTO.setUserId(response.getPostDTO().getUserId());
        reactionDTO.setPostId(response.getPostDTO().getPostId());
        reactionDTO.setActivity(type);

        if (type == "Comment") {
            final String postId = response.getPostDTO().getPostId();
            Intent intent = new Intent(getActivity(), CommentActivity.class);
            intent.putExtra("postIdComment", postId);
            startActivity(intent);
        } else if (type == "LikeCount") {
            final String postId = response.getPostDTO().getPostId();
            Intent intent = new Intent(getContext(), ReactionActivity.class);
            intent.putExtra("postId", postId);
            startActivity(intent);
        } else {
            Api api = App.getRetrofit(Api.BASE_URL_PROXY).create(Api.class);
            Call<BaseResponse<String>> call = api.addReaction(reactionDTO);
            call.enqueue(new Callback<BaseResponse<String>>() {
                @Override
                public void onResponse(Call<BaseResponse<String>> call, Response<BaseResponse<String>> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(getContext(), "You liked the post!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<BaseResponse<String>> call, Throwable t) {
                    Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    private String getExtension(Uri uri) {
        ContentResolver contentResolver = getActivity().getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            // uploadImage.setImageURI(imageUri);
            final StorageReference ref = mStorageRef.child(System.currentTimeMillis() + getExtension(imageUri));
            ref.putFile(imageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    content.setImage(uri.toString());
                                }
                            });
                            Toast.makeText(getContext(), "Image uploaded successfully", Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                            Toast.makeText(getContext(), "Upload is " + progress + "% done", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
}
