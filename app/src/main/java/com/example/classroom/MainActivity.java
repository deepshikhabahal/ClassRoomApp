package com.example.classroom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<String> VideoTitle = new ArrayList<>();
    ArrayList<String> VideoArtist = new ArrayList<>();

    private ActionBarDrawerToggle actionBarDrawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.video_list);
        NavigationView navigationView = findViewById(R.id.navigation_view);
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);

        Toolbar mToolbar = findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("CLASSROOM");

        actionBarDrawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        View navView = navigationView.inflateHeaderView(R.layout.navigation_header);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                userMenuSelector(item);
                return false;
            }
        });



        //getting Json
        try {
            //getting JSON object from Json file
            JSONObject obj = new JSONObject(loadJSONFromAssets());
            //fetch JSONArray named users
            JSONArray videoArray = obj.getJSONArray("videos");
            for (int i =0; i<videoArray.length() ; i++){
                //creating a JSONObject for fetching a single user data
                JSONObject videoDetail = videoArray.getJSONObject(i);
                VideoTitle.add(videoDetail.getString("videoTitle"));
                VideoArtist.add(videoDetail.getString("videoArtist"));
            }
            //calling the CustomAdapter to send the reference and data to adapter
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(linearLayoutManager);
            CustomAdapter customAdapter = new CustomAdapter(VideoTitle, VideoArtist, MainActivity.this);
            recyclerView.setAdapter(customAdapter);
        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("NonConstantResourceId")
    private void userMenuSelector(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_maths:
                Toast.makeText(MainActivity.this, "Maths", Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_english:
                Toast.makeText(MainActivity.this, "English", Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_science:
                Toast.makeText(MainActivity.this, "Science", Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_social_science:
                Toast.makeText(MainActivity.this, "Social Science", Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_hindi:
                Toast.makeText(MainActivity.this, "Hindi", Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_gk:
                Toast.makeText(MainActivity.this, "General Knowledge", Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_settings:
                Toast.makeText(MainActivity.this, "Settings", Toast.LENGTH_SHORT).show();
                break;


        }
    }

    private String loadJSONFromAssets() {
        String json = null;

        try {
            InputStream is = getApplicationContext().getAssets().open("classroom.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");

        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
        return json;
    }
}


