package com.example.frenzbook;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("user_details", MODE_PRIVATE);
        final String userId = sharedPreferences.getString("user_id", null);

        new Handler().postDelayed
                (new Runnable() {
                    @Override
                    public void run() {
                        if (userId != null) {
                            Intent intent = new Intent(MainActivity.this, LandingPageDrawer.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Intent intent = new Intent(MainActivity.this, SignUP.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                }, 700);
    }
}