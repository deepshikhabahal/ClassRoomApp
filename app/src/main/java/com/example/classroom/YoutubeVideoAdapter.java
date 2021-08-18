package com.example.classroom;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

import java.util.ArrayList;

public class YoutubeVideoAdapter extends RecyclerView.Adapter<YoutubeVideoAdapter.YoutubeViewHolder> {//implements View.OnClickListener {
    private static final String TAG = YoutubeVideoAdapter.class.getSimpleName();
    private final ArrayList<YoutubeVideoModel> youtubeVideoModelArrayList;
    //itemClickListener itemClickListener;

  /*  @Override
    public void onClick(View v) {
        itemClickListener.onItemClick(1);
    }

     interface itemClickListener {
        void onItemClick(int position);
    }*/


    public YoutubeVideoAdapter(Context context, ArrayList<YoutubeVideoModel> youtubeVideoModelArrayList) { //itemClickListener itemClickListener) {
        this.youtubeVideoModelArrayList = youtubeVideoModelArrayList;
        //this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public YoutubeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_layout, parent, false);
        return new YoutubeViewHolder(view); //itemClickListener);
    }


    @Override
    public void onBindViewHolder(@NonNull YoutubeViewHolder holder, int position) {
        final YoutubeVideoModel youtubeVideoModel = youtubeVideoModelArrayList.get(position);

        holder.videoTitle.setText(youtubeVideoModel.getTitle());
        holder.videoArtist.setText(youtubeVideoModel.getArtist());
        holder.videoDuration.setText(youtubeVideoModel.getDuration());

        /* To initialize the thumbnail image view , we need to pass Developer Key */
        Log.d(TAG, "readyForLoadingYoutubeThumbnail");
        holder.videoThumbnailImageView.initialize(YoutubeConfig.API_KEY, new YouTubeThumbnailView.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, final YouTubeThumbnailLoader youTubeThumbnailLoader) {
                //when initialization is success, set the video id to thumbnail to load
                youTubeThumbnailLoader.setVideo(youtubeVideoModel.getVideoId());

                youTubeThumbnailLoader.setOnThumbnailLoadedListener(new YouTubeThumbnailLoader.OnThumbnailLoadedListener() {
                    @Override
                    public void onThumbnailLoaded(YouTubeThumbnailView youTubeThumbnailView, String s) {
                        //when thumbnail loaded successfully release the thumbnail loader as we are showing thumbnail in adapter
                        youTubeThumbnailLoader.release();
                    }

                    @Override
                    public void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader.ErrorReason errorReason) {
                        //print or show error when thumbnail load failed
                        Log.e(TAG, "Youtube Thumbnail Error");
                    }
                });
            }

            @Override
            public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {
                //print or show error when initialization failed
                Log.e(TAG, "Youtube Initialization Failure");
            }
        });

    }

    @Override
    public int getItemCount() {
        return youtubeVideoModelArrayList != null ? youtubeVideoModelArrayList.size() : 0;
    }

    public static class YoutubeViewHolder extends RecyclerView.ViewHolder { //implements View.OnClickListener {
        public YouTubeThumbnailView videoThumbnailImageView;
        public TextView videoTitle, videoArtist, videoDuration;
        // itemClickListener itemClickListener;

        public YoutubeViewHolder(@NonNull View itemView) { //itemClickListener itemClickListener) {
            super(itemView);
            // this.itemClickListener = itemClickListener;
            // itemView.setOnClickListener(this);
            videoThumbnailImageView = itemView.findViewById(R.id.video_thumbnail_image_view);
            videoTitle = itemView.findViewById(R.id.child_video_title);
            videoArtist = itemView.findViewById(R.id.child_video_artist);
            videoDuration = itemView.findViewById(R.id.child_video_duration);

        }
    }
}

       /* @Override
        public void onClick(View v) {
            itemClickListener.onItemClick(getAdapterPosition());
        }
    }
}*/
