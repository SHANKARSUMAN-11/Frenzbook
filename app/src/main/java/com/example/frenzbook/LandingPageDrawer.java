package com.example.frenzbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.frenzbook.DTO.SearchDTO;
import com.example.frenzbook.DTO.SecondSignUpDTO;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LandingPageDrawer extends AppCompatActivity implements SearchAdapter.ISearchCommunicator ,NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private SharedPreferences sharedPreferences;
    private List<SecondSignUpDTO> secondSignUpDTOList;
    private RecyclerView landingRecycler;
    private SearchAdapter searchAdapter;
    private Api api;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page_drawer);

        searchAdapter = new SearchAdapter(this, this, secondSignUpDTOList);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        //navigationView.setNavigationItemSelectedListener(this);
        final NavigationView navigationView = findViewById(R.id.nav_view);
        Menu menu = navigationView.getMenu();
        navigationView.setNavigationItemSelectedListener(this);
        menu.performIdentifierAction(R.id.my_friends, 3);
        sharedPreferences = getSharedPreferences("user_details", MODE_PRIVATE);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        MenuItem menuItem = menu.getItem(0);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search here");
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                final SearchDTO searchDTO = new SearchDTO();
                searchDTO.setSearch(query);
                api = App.getRetrofit(Api.BASE_URL_PROXY).create(Api.class);
                Call<List<SecondSignUpDTO>> call = api.getByName(searchDTO.getSearch());
                call.enqueue(new Callback<List<SecondSignUpDTO>>() {
                    @Override
                    public void onResponse(Call<List<SecondSignUpDTO>> call, Response<List<SecondSignUpDTO>> response) {
                        if (!response.isSuccessful()) {
                            System.out.println("Response not successful. CODE : " + response.code());
                            return;
                        }

                        secondSignUpDTOList = response.body();
                        landingRecycler = findViewById(R.id.recycler);
                        landingRecycler.setAdapter(new SearchAdapter(LandingPageDrawer.this,LandingPageDrawer.this,secondSignUpDTOList));
                        landingRecycler.setLayoutManager(new LinearLayoutManager(LandingPageDrawer.this));
                        searchAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<List<SecondSignUpDTO>> call, Throwable t) {
                        Toast.makeText(LandingPageDrawer.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @Override
    public void onSearchItemClick(String userId, String imageUrl, String userName)
    {
        TimelineFragment timelineFragment = new TimelineFragment(userId,imageUrl,userName);
        FragmentManager fragmentManager4 = getSupportFragmentManager();
        fragmentManager4.beginTransaction().replace(R.id.fragment,timelineFragment).commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about:
                AboutFragment fragment1 = new AboutFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment, fragment1).commit();
                break;
            case R.id.my_friends:
                MyFriendsFragment friendsFragment = new MyFriendsFragment();
                FragmentManager fragmentManager1 = getSupportFragmentManager();
                fragmentManager1.beginTransaction().replace(R.id.fragment, friendsFragment).commit();
                break;
            case R.id.timeline:
                TimelineFragment timelineFragment = new TimelineFragment();
                FragmentManager fragmentManager2 = getSupportFragmentManager();
                fragmentManager2.beginTransaction().replace(R.id.fragment, timelineFragment).commit();
                break;
            case R.id.feed:
                FeedFragment feedFragment = new FeedFragment();
                FragmentManager fragmentManager3 = getSupportFragmentManager();
                fragmentManager3.beginTransaction().replace(R.id.fragment, feedFragment).commit();
                break;
            case R.id.logout:
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
                startActivity(new Intent(LandingPageDrawer.this, LoginActivity.class));
                break;


            default:
                throw new IllegalStateException("Unexpected value: " + item.getItemId());
        }
        drawerLayout.closeDrawers();
        return true;

    }
}
