package com.infinitum.fabelizerapp;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.google.android.youtube.player.YouTubePlayerView;
import com.infinitum.fabelizerapp.Utils.FullScreenHelper;
import com.infinitum.fabelizerapp.Utils.MyFragment;


public class FragmentHome extends Fragment {
    private View view;
    YouTubePlayerView youTubePlayerView;
//    private FullScreenHelper fullScreenHelper = new FullScreenHelper(getActivity());
    private YouTubePlayer youtubeplayer;

    public FragmentHome() {

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_home,container,false);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        // Initializing YouTube player view
//        youTubePlayerView = (YouTubePlayerView) view.findViewById(R.id.youtube_view);
        YouTubePlayerSupportFragment youTubePlayerFragment = YouTubePlayerSupportFragment.newInstance();

        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.youtube_view, youTubePlayerFragment).commit();

        youTubePlayerFragment.initialize(Config.DEVELOPER_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
                youtubeplayer=player;
                if(null== player) return;

                // Start buffering
                if (!wasRestored) {
                    player.cueVideo(Config.YOUTUBE_VIDEO_CODE);
                }
//                addFullScreenListenerToPlayer(player);
                // Add listeners to YouTubePlayer instance
                player.setPlayerStateChangeListener(new YouTubePlayer.PlayerStateChangeListener() {
                    @Override public void onAdStarted() { }
                    @Override public void onError(YouTubePlayer.ErrorReason arg0) { }
                    @Override public void onLoaded(String arg0) { }
                    @Override public void onLoading() { }
                    @Override public void onVideoEnded() { }
                    @Override public void onVideoStarted() { }
                });


                player.setPlaybackEventListener(new YouTubePlayer.PlaybackEventListener() {
                    @Override public void onBuffering(boolean arg0) { }
                    @Override public void onPaused() { }
                    @Override public void onPlaying() { }
                    @Override public void onSeekTo(int arg0) { }
                    @Override public void onStopped() { }
                });
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Toast.makeText(getActivity(), "Failed to initialize.", Toast.LENGTH_LONG).show();

            }
        });
    }

    //method for fullscreen
    private void addFullScreenListenerToPlayer(final YouTubePlayer youTubePlayer) {
        youTubePlayer.setOnFullscreenListener(new YouTubePlayer.OnFullscreenListener() {
            @Override
            public void onFullscreen(boolean b) {
                if(b==true)
                {
                    getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//                    fullScreenHelper.enterFullScreen();
                }else
                {
                    getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//                    fullScreenHelper.exitFullScreen();
                }

            }


        });
    }

}
