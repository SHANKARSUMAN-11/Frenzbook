package com.example.frenzbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.frenzbook.DTO.BaseResponse;
import com.example.frenzbook.DTO.ReactionShowResponse;
import com.example.frenzbook.DTO.ReactionView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReactionActivity extends AppCompatActivity  {

    RecyclerView recyclerView;
    ReactionAdapter reactionAdapter;
    private RecyclerView.LayoutManager linear;
    private List reactionShow;
    private CardView cardView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_my_friends);
        cardView=findViewById(R.id.card);

        Api api = App.getRetrofit(Api.BASE_URL_PROXY).create(Api.class);

        Call<BaseResponse<List<ReactionShowResponse>>> call =api.showReactionsByUserId(getIntent().getStringExtra("postId"));
        call.enqueue(new Callback<BaseResponse<List<ReactionShowResponse>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<ReactionShowResponse>>> call, Response<BaseResponse<List<ReactionShowResponse>>> response) {
                if(response.body()!=null)
                {
                    cardView.setVisibility(View.GONE);
                    reactionShow = response.body().getData();
                    recyclerView = findViewById(R.id.recycler);
                    linear = new LinearLayoutManager(ReactionActivity.this);
                    reactionAdapter = new ReactionAdapter(reactionShow);
                    recyclerView.setLayoutManager(linear);
                    recyclerView.setAdapter(reactionAdapter);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<List<ReactionShowResponse>>> call, Throwable t) {
                Log.i("Aalia", t.getMessage());
            }
        });



    }


}
