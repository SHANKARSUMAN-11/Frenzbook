package com.example.frenzbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.frenzbook.DTO.AddComment;
import com.example.frenzbook.DTO.BaseResponse;
import com.example.frenzbook.DTO.ChildCommentItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChildCommentActivity extends AppCompatActivity {

    RecyclerView childCommentRecycler;
    ChildCommentAdapter childCommentAdapter;
    private RecyclerView.LayoutManager linear;
    List<ChildCommentItem> childCommentItemList;
    String parentCommentId;
    String postId;
    Button addComment;
    EditText addCommentContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_recyler);
        parentCommentId = getIntent().getStringExtra("parentCommentId");
        postId = getIntent().getStringExtra("postId");
        Api api = App.getRetrofit(Api.BASE_URL_PROXY).create(Api.class);
        Call<BaseResponse<List<ChildCommentItem>>> call1 = api.getCommentByParentId(parentCommentId);
        call1.enqueue(new Callback<BaseResponse<List<ChildCommentItem>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<ChildCommentItem>>> call, Response<BaseResponse<List<ChildCommentItem>>> response) {
                childCommentRecycler= findViewById(R.id.rv_comments);
                linear = new LinearLayoutManager(ChildCommentActivity.this);
                childCommentItemList=response.body().getData();
                childCommentAdapter = new ChildCommentAdapter(childCommentItemList);
                childCommentRecycler.setLayoutManager(linear);
                childCommentRecycler.setAdapter(childCommentAdapter);

            }

            @Override
            public void onFailure(Call<BaseResponse<List<ChildCommentItem>>> call, Throwable t) {

            }
        });

        addCommentContent=findViewById(R.id.editText);
        addComment = findViewById(R.id.addComment);

        addComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddChildCommentClick(parentCommentId,"98",postId);
            }
        });

    }

    public void onAddChildCommentClick(String parentCommentId, String userId,String postId) {
        AddComment addComment=new AddComment();
        addComment.setPostId(postId);
        addComment.setUserId(userId);
        addComment.setParentCommentId(parentCommentId);
        addComment.setText(String.valueOf(addCommentContent.getText()));

        Api api = App.getRetrofit(Api.BASE_URL_PROXY).create(Api.class);
        Call<BaseResponse<String>> call = api.addComment(addComment);

        call.enqueue(new Callback<BaseResponse<String>>() {
            @Override
            public void onResponse(Call<BaseResponse<String>> call, Response<BaseResponse<String>> response) {
                Toast.makeText(ChildCommentActivity.this, "You commented on the post!", Toast.LENGTH_SHORT).show();
                finish();
                startActivity(getIntent());
            }

            @Override
            public void onFailure(Call<BaseResponse<String>> call, Throwable t) {
                Log.i("Aalia", t.getMessage());
            }
        });
    }
}
