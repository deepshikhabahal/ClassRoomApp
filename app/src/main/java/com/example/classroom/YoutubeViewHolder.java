package com.example.classroom;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.youtube.player.YouTubeThumbnailView;

public class YoutubeViewHolder extends RecyclerView.ViewHolder {
    public YouTubeThumbnailView videoThumbnailImageView;

    public YoutubeViewHolder(@NonNull View itemView) {
        super(itemView);
        videoThumbnailImageView = itemView.findViewById(R.id.video_thumbnail_image_view);
    }
}
