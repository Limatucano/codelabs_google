package com.example.simplevideoview;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    private static final String VIDEO_SAMPLE = "teste";
    private VideoView mVideoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mVideoView = findViewById(R.id.videoView);

    }
    @Override
    protected void onStart(){
        super.onStart();
        initializePlayer();
    }
    @Override
    protected void onStop(){
        super.onStop();
        releasePlayer();
    }
    private void initializePlayer() {
        Uri videoUri = getMedia(VIDEO_SAMPLE);
        mVideoView.setVideoURI(videoUri);
        mVideoView.start();
    }
    @Override
    protected void onPause() {
        super.onPause();

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            mVideoView.pause();
        }
    }
    private Uri getMedia(String mediaName){
        return Uri.parse("android.resource://" + getPackageName() + "/raw/" + mediaName);
    }
    private void releasePlayer() {
        mVideoView.stopPlayback();
    }
}