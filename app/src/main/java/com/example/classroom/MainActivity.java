package com.example.classroom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.video_list);


        //getting Json
        try {
            //getting JSON object from Json file
            JSONObject obj = new JSONObject(loadJSONFromAssets());
            //fetch JSONArray named users
            JSONArray videoArray = obj.getJSONArray("videos");
            for (int i =0; i<videoArray.length() ; i++){
                //creating a JSONObject for fetching a single user data
                JSONObject videoDetail = videoArray.getJSONObject(i);
                VideoTitle.add(videoDetail.getString("videotitle"));
                VideoArtist.add(videoDetail.getString("videoartist"));
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


