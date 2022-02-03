package com.example.implicitintentsreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.example.implicitintentsreceiver.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding viewBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(viewBinding.getRoot());

        Intent intent = getIntent();
        Uri uri = intent.getData();

        if(uri != null){
            String uriString = "URI: " + uri.toString();
            viewBinding.textUriMessage.setText(uriString);
        }
    }
}