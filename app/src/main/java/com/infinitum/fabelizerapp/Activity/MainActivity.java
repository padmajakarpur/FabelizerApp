package com.infinitum.fabelizerapp.Activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.infinitum.fabelizerapp.Config;
import com.infinitum.fabelizerapp.R;
import com.infinitum.fabelizerapp.Utils.FullScreenHelper;

import static com.google.android.youtube.player.YouTubePlayer.ErrorReason;
import static com.google.android.youtube.player.YouTubePlayer.OnInitializedListener;
import static com.google.android.youtube.player.YouTubePlayer.PlaybackEventListener;
import static com.google.android.youtube.player.YouTubePlayer.PlayerStateChangeListener;
import static com.google.android.youtube.player.YouTubePlayer.PlayerStyle;
import static com.google.android.youtube.player.YouTubePlayer.Provider;

public class MainActivity extends YouTubeBaseActivity implements OnInitializedListener {


    //https://www.youtube.com/watch?v=<VIDEO_ID>
    public static final String VIDEO_ID = "6JYIGclVQdw";
    private int count;
    YouTubePlayerView youTubePlayerView;
//    private FullScreenHelper fullScreenHelper = new FullScreenHelper(this);
    private YouTubePlayer youtubeplayer;
    private String videoID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // attaching layout xml
        setContentView(R.layout.activity_main);
//get the video id
        videoID = getIntent().getStringExtra("video_id");
        // Initializing YouTube player view
        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        youTubePlayerView.initialize(Config.DEVELOPER_KEY, this);

    }

    @Override
    public void onInitializationFailure(Provider provider, YouTubeInitializationResult result) {
        Toast.makeText(this, "Failed to initialize.", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onInitializationSuccess(Provider provider, final YouTubePlayer player, boolean wasRestored) {
        youtubeplayer=player;
        if(null== player)
            return;

        // Start buffering
        if (!wasRestored) {
//            player.cueVideo(Config.YOUTUBE_VIDEO_CODE,0);
            player.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
            player.loadVideo(videoID);
        }
//        addFullScreenListenerToPlayer(player);
        // Add listeners to YouTubePlayer instance
//        player.setPlayerStateChangeListener(new PlayerStateChangeListener() {
//            @Override public void onAdStarted() { }
//            @Override public void onError(ErrorReason arg0) { }
//            @Override public void onLoaded(String arg0) { }
//            @Override public void onLoading() { }
//            @Override public void onVideoEnded() { }
//            @Override public void onVideoStarted() { }
//        });
//
//
//        player.setPlaybackEventListener(new PlaybackEventListener() {
//            @Override public void onBuffering(boolean arg0) { }
//            @Override public void onPaused() { }
//            @Override public void onPlaying() { }
//            @Override public void onSeekTo(int arg0) { }
//            @Override public void onStopped() { }
//        });
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT)
        {

        }
        else if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            //standalone is for betterment of screen landscape mode

//            Intent intent = YouTubeStandalonePlayer.createVideoIntent(this, Config.DEVELOPER_KEY, videoID);
//            startActivity(intent);
            youtubeplayer.loadVideo(videoID);
        }
    }

    //method for fullscreen
    private void addFullScreenListenerToPlayer(final YouTubePlayer youTubePlayer) {
        youTubePlayer.setOnFullscreenListener(new YouTubePlayer.OnFullscreenListener() {
            @Override
            public void onFullscreen(boolean b) {
                if(b==true)
                {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//                    fullScreenHelper.enterFullScreen();
                }else
                {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//                    fullScreenHelper.exitFullScreen();
                }

            }


        });
    }

}