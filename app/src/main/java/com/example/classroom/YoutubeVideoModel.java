package com.example.classroom;

import androidx.annotation.NonNull;

public class YoutubeVideoModel {

    private String videoId;

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }
    @NonNull
    @Override
    public String toString() {
        return "YoutubeVideoModel{" +
                "videoId='" + videoId + '\'' +
                '}';
    }

}
