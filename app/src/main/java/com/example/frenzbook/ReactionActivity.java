package com.example.frenzbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class ReactionActivity extends AppCompatActivity  {

    RecyclerView recyclerView;
    ReactionAdapter reactionAdapter;
    private RecyclerView.LayoutManager linear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_my_friends);

        recyclerView = findViewById(R.id.recycler);
        linear = new LinearLayoutManager(this);
        reactionAdapter = new ReactionAdapter();
        recyclerView.setLayoutManager(linear);
        recyclerView.setAdapter(reactionAdapter);

    }


}
