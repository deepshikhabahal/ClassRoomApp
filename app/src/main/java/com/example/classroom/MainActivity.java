package com.example.classroom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {//implements YoutubeVideoAdapter.itemClickListener {
    RecyclerView recyclerView;

    private ActionBarDrawerToggle actionBarDrawerToggle;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        recyclerView = findViewById(R.id.video_list);
        recyclerView.setHasFixedSize(true);

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
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        populateRecyclerView();
    }

    //getting Json
       /* try {
            //getting JSON object from Json file
            JSONObject obj = new JSONObject(loadJSONFromAssets());
            //fetch JSONArray named users
            JSONArray videoArray = obj.getJSONArray("videos");
            for (int i =0; i<videoArray.length() ; i++){
                //creating a JSONObject for fetching a single user data
                JSONObject videoDetail = videoArray.getJSONObject(i);
                VideoTitle.add(videoDetail.getString("videoTitle"));
                VideoArtist.add(videoDetail.getString("videoArtist"));
                VideoDuration.add(videoDetail.getString("videoDuration"));
            }
            //calling the CustomAdapter to send the reference and data to adapter
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(linearLayoutManager);
            CustomAdapter customAdapter = new CustomAdapter(VideoTitle, VideoArtist,VideoDuration,MainActivity.this);
            recyclerView.setAdapter(customAdapter);

        }
        catch (JSONException e){
            e.printStackTrace();
        }
  }*/

    private void populateRecyclerView() {
        final ArrayList<YoutubeVideoModel> youtubeVideoModelArrayList = generateDummyVideoList();
        YoutubeVideoAdapter adapter = new YoutubeVideoAdapter(this, youtubeVideoModelArrayList); //this);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerViewOnClickListener(this, (view, position) -> startActivity(new Intent(MainActivity.this, YoutubePlayerActivity.class)
                .putExtra("video_id", youtubeVideoModelArrayList.get(position).getVideoId()))));
    }

   /* @Override
    public void onItemClick(int position) {
        Toast.makeText(this, "Position is " + position, Toast.LENGTH_SHORT).show();
        final ArrayList<YoutubeVideoModel> youtubeVideoModelArrayList = generateDummyVideoList();
        YoutubeVideoAdapter adapter = new YoutubeVideoAdapter(this, youtubeVideoModelArrayList, this);
        recyclerView.setAdapter(adapter);
        startActivity(new Intent(MainActivity.this, YoutubePlayerActivity.class)
                .putExtra("video_id", youtubeVideoModelArrayList.get(position).getVideoId()));

    } */

    private ArrayList<YoutubeVideoModel> generateDummyVideoList() {
        ArrayList<YoutubeVideoModel> youtubeVideoModelArrayList = new ArrayList<>();
//get the video id array, title array and duration array from strings.xml
        String[] videoIDArray = getResources().getStringArray(R.array.video_id_array);
        String[] videoTitleArray = getResources().getStringArray(R.array.video_title_array);
        String[] videoArtistArray = getResources().getStringArray(R.array.video_artist_array);
        String[] videoDurationArray = getResources().getStringArray(R.array.video_duration_array);

        //loop through all items and add them to arraylist
        for (int i = 0; i < videoIDArray.length; i++) {

            YoutubeVideoModel youtubeVideoModel = new YoutubeVideoModel();
            youtubeVideoModel.setVideoId(videoIDArray[i]);
            youtubeVideoModel.setTitle(videoTitleArray[i]);
            youtubeVideoModel.setArtist(videoArtistArray[i]);
            youtubeVideoModel.setDuration(videoDurationArray[i]);

            youtubeVideoModelArrayList.add(youtubeVideoModel);
        }

        return youtubeVideoModelArrayList;
    }


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser == null) {
            SendUserToLoginActivity();
        }

    }


    private void SendUserToLoginActivity() {
        Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(loginIntent);
        finish();
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

            case R.id.nav_logout:
                mAuth.signOut();
                SendUserToLoginActivity();
                break;


        }
    }
}

    /*private String loadJSONFromAssets() {
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
}*/



