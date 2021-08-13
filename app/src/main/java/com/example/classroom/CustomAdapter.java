package com.example.classroom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    //Creating Arraylists
    ArrayList<String> videoTitle;
    ArrayList<String> videoArtist;
    ArrayList<String> videoDuration;
    Context context;

    //Constructor

    public CustomAdapter(ArrayList<String> mainVideoTitle, ArrayList<String> mainVideoArtist, ArrayList<String> mainVideoDuration, Context context) {
        this.videoTitle = mainVideoTitle;
        this.videoArtist = mainVideoArtist;
        this.videoDuration= mainVideoDuration;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //ViewHolders
        //Inflate the item layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        //set the data in items
        holder.videoTitle.setText(videoTitle.get(position));
        holder.videoArtist.setText(videoArtist.get(position));
        holder.videoDuration.setText(videoDuration.get(position));

        //Adding OnclickListener
        holder.itemView.setOnClickListener(v -> {
            //Displaying a message with name
            Toast.makeText(context, videoTitle.get(position), Toast.LENGTH_SHORT).show();

        });
    }

    @Override
    public int getItemCount() {

        return videoTitle.size();
    }


    //MyHolder class
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private final TextView videoTitle, videoArtist, videoDuration;

        //Constructor
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            videoTitle = itemView.findViewById(R.id.child_video_title);
            videoArtist = itemView.findViewById(R.id.child_video_artist);
            videoDuration = itemView.findViewById(R.id.video_duration_label);
        }
    }
}

//1."videoUrl": "https://youtu.be/NCjf6og4qHk",


//2."videoUrl": "https://youtu.be/z-R3DShHbkA",


//3."videoUrl": "https://youtu.be/e-P5IFTqB98",


//4."videoUrl": "https://youtu.be/-wkr_vf18cw",

//5."videoUrl": "https://youtu.be/grkWGeqW99c",
