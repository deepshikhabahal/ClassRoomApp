package com.example.classroom;

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
    ArrayList<String> VideoTitle;
    ArrayList<String> VideoArtist;
    Context context;

    //Constructor

    public CustomAdapter(ArrayList<String> mainVideoTitle, ArrayList<String> mainVideoArtist, Context context) {
        this.VideoTitle = mainVideoTitle;
        this.VideoArtist = mainVideoArtist;
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
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {
        //set the data in items
        holder.videoTitle.setText(VideoTitle.get(position));
        holder.videoArtist.setText(VideoArtist.get(position));

        //Adding OnclickListener
        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Displaying a message with name
                Toast.makeText(context, VideoTitle.get(position), Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public int getItemCount() {

        return VideoTitle.size();
    }


    //MyHolder class
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private final TextView videoTitle, videoArtist;

        //Constructor
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            videoTitle = itemView.findViewById(R.id.child_video_title);
            videoArtist = itemView.findViewById(R.id.child_video_artist);
        }
    }
}

//1."videoUrl": "https://youtu.be/NCjf6og4qHk",
//  "videoCoverImage": "https://i.ytimg.com/vi/NCjf6og4qHk/maxresdefault.jpg"

//2."videoUrl": "https://youtu.be/z-R3DShHbkA",
//      "videoCoverImage": "https://i.ytimg.com/vi/z-R3DShHbkA/maxresdefault.jpg"

//3."videoUrl": "https://youtu.be/e-P5IFTqB98",
//      "videoCoverImage": "https://i.ytimg.com/vi/e-P5IFTqB98/maxresdefault.jpg"

//4."videoUrl": "https://youtu.be/-wkr_vf18cw",
//      "videoCoverImage": "https://i.ytimg.com/vi/-wkr_vf18cw/maxresdefault.jpg"

//5."videoUrl": "https://youtu.be/grkWGeqW99c",
//      "videoCoverImage": "https://i.ytimg.com/vi/grkWGeqW99c/maxresdefault.jpg"