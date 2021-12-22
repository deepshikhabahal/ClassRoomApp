package com.example.classroom;


import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class YoutubePlayerActivity extends YouTubeBaseActivity {
    private static final String TAG = YoutubePlayerActivity.class.getSimpleName();
    private String videoID;
    private YouTubePlayerView youTubePlayerView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.youtube_player_activity);
        videoID = getIntent().getStringExtra("video_id");
        youTubePlayerView = findViewById(R.id.youtube_player_view);
        initializeYoutubePlayer();
        button = findViewById(R.id.video_button);

    }

    private void initializeYoutubePlayer() {
        youTubePlayerView.initialize(YoutubeConfig.API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                //if initialization success then load the video id to youtube player
                if (!b) {
                    //set the player style here: like CHROMELESS, MINIMAL, DEFAULT
                    youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);

                    //load the video
                    //youTubePlayer.loadVideo(videoID);

                    //OR

                    //cue the video
                    youTubePlayer.cueVideo(videoID);

                    button.setOnClickListener(v -> {
                       // youTubePlayer.setFullscreen(true);
                        youTubePlayer.play();
                        button.setText("Pause");
                        if (youTubePlayer.isPlaying()) {
                            youTubePlayer.pause();
                            button.setText("Resume");
                        } else {
                            youTubePlayer.play();
                        }
                    });

                    //if you want when activity start it should be in full screen uncomment below comment
                    //  youTubePlayer.setFullscreen(true);

                    //If you want to control the full screen event you can uncomment the below code
                    //Tell the player you want to control the fullscreen change
                   /*player.setFullscreenControlFlags(YouTubePlayer.FULLSCREEN_FLAG_CUSTOM_LAYOUT);
                    Tell the player how to control the change
                    player.setOnFullscreenListener(new YouTubePlayer.OnFullscreenListener() {
                        @Override
                        public void onFullscreen(boolean arg0) {
                                                                                                                                                                                                                                     do full screen stuff here, or don't.
                            Log.e(TAG,"Full screen mode");
                        }
                    });*/
                }
            }


            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Log.e(TAG, "Youtube Player View initialization failed");
            }
        });

    }


}