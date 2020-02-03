package com.example.frenzbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.frenzbook.DTO.AddComment;
import com.example.frenzbook.DTO.BaseResponse;
import com.example.frenzbook.DTO.ChildCommentItem;
import com.example.frenzbook.DTO.Comment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentActivity extends AppCompatActivity implements CommentAdapter.CommentInterface {

    RecyclerView recyclerView;
    CommentAdapter commentAdapter;
    private RecyclerView.LayoutManager linear;
    List<Comment> comments;
    Button addComment;
    EditText addCommentContent;
    String postIdComment;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_recyler);
        sharedPreferences = getSharedPreferences("user_details",MODE_PRIVATE);
        final String userId = sharedPreferences.getString("user_id",null);
        postIdComment =getIntent().getStringExtra("postIdComment");
        Api api = App.getRetrofit(Api.BASE_URL_PROXY).create(Api.class);
        Call<BaseResponse<List<Comment>>> call = api.getCommentByPostId(postIdComment);
        call.enqueue(new Callback<BaseResponse<List<Comment>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<Comment>>> call, Response<BaseResponse<List<Comment>>> response) {
                recyclerView = findViewById(R.id.rv_comments);
                linear = new LinearLayoutManager(CommentActivity.this);
                comments=response.body().getData();
                commentAdapter = new CommentAdapter(comments);
                recyclerView.setLayoutManager(linear);
                recyclerView.setAdapter(commentAdapter);

            }

            @Override
            public void onFailure(Call<BaseResponse<List<Comment>>> call, Throwable t) {
                Log.i("Aalia", t.getMessage());

            }
        });
        addCommentContent=findViewById(R.id.editText);
        addComment = findViewById(R.id.addComment);

        addComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddCommentClick(postIdComment,userId);
            }
        });


    }


    public void onAddCommentClick(String postId, String userId) {

        AddComment addComment=new AddComment();
        addComment.setPostId(postId);
        addComment.setUserId(userId);
        addComment.setText(String.valueOf(addCommentContent.getText()));
        addComment.setParentCommentId("null");
        Api api = App.getRetrofit(Api.BASE_URL_PROXY).create(Api.class);
        Call<BaseResponse<String>> call = api.addComment(addComment);
        call.enqueue(new Callback<BaseResponse<String>>() {
            @Override
            public void onResponse(Call<BaseResponse<String>> call, Response<BaseResponse<String>> response) {
                Toast.makeText(CommentActivity.this, "You commented on the post!", Toast.LENGTH_SHORT).show();
                finish();
                startActivity(getIntent());
            }

            @Override
            public void onFailure(Call<BaseResponse<String>> call, Throwable t) {
                Log.i("Aalia", t.getMessage());
            }
        });
    }

    @Override
    public void onParentCommentClick(String parentCommentId, String postId, List<ChildCommentItem> childCommentItems) {

        Intent intent=new Intent(CommentActivity.this,ChildCommentActivity.class);
        intent.putExtra("parentCommentId",parentCommentId);
        intent.putExtra("postId",postId);
        intent.putExtra("childComment", String.valueOf(childCommentItems));
        startActivity(intent);
    }
}
