package com.example.frenzbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class LandingPageDrawer extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page_drawer);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        setDrawer();
    }


    private void setDrawer() {
     navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
         @Override
         public boolean onNavigationItemSelected(@NonNull MenuItem item) {
             switch(item.getItemId()) {
                 case R.id.about:
                     AboutFragment fragment1 = new AboutFragment();
                     FragmentManager fragmentManager = getSupportFragmentManager();
                     fragmentManager.beginTransaction().replace(R.id.fragment, fragment1).commit();
                     break;
                 case R.id.my_friends:
                     MyFriendsFragment friendsFragment = new MyFriendsFragment();
                     FragmentManager fragmentManager1 = getSupportFragmentManager();
                     fragmentManager1.beginTransaction().replace(R.id.fragment,friendsFragment).commit();
                     break;
                 case R.id.timeline:
                     TimelineFragment timelineFragment = new TimelineFragment();
                     FragmentManager fragmentManager2 = getSupportFragmentManager();
                     fragmentManager2.beginTransaction().replace(R.id.fragment,timelineFragment).commit();
                     break;
                 case R.id.feed:


                 default:
                     throw new IllegalStateException("Unexpected value: " + item.getItemId());
             }
             drawerLayout.closeDrawers();
             return true;
         }
     });
    }


    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }


}
