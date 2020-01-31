package com.example.frenzbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class LandingPage extends AppCompatActivity {

    RecyclerView recyclerView;
    EditText post_b;
    ArrayList<ModelFeed> modelFeedArrayList=new ArrayList<>();
    AdapterFeed adapterFeed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
        recyclerView=(RecyclerView) findViewById(R.id.recyclerViewFeed);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapterFeed=new AdapterFeed(this,modelFeedArrayList);
        recyclerView.setAdapter(adapterFeed);
        populateRecyclerView();
        post_b=findViewById(R.id.post_click);
        post_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LandingPage.this,PostActivity.class));
            }
        });
    }
    public void populateRecyclerView(){
        ModelFeed modelFeed=new ModelFeed(1,5,2,R.drawable.profilepic2,R.drawable.statuspic,"Varish Kumar","The notion of love cannot be captured by desolution");
        modelFeedArrayList.add(modelFeed);
        ModelFeed modelFeed1=new ModelFeed(1,3,4,R.drawable.profilepic3,R.drawable.statuspic,"Gaurav Kumar","If you are good at something,Never do it for free");
        modelFeedArrayList.add(modelFeed1);
        ModelFeed modelFeed2=new ModelFeed(1,10,45,R.drawable.profilepic2,R.drawable.statuspic,"Esther Duflo","Stopping by the woods on a snowy evening");
        modelFeedArrayList.add(modelFeed2);

        adapterFeed.notifyDataSetChanged();
    }
}
