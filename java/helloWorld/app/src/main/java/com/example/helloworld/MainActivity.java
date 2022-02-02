package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.example.helloworld.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding viewBinding;
    private int counter = 0;
    @Override
    protected void onStart() {
        super.onStart();
        Log.d("LIFECYCLE", "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("LIFECYCLE", "onResume()");
    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.d("LIFECYCLE", "onPause()");
    }
    @Override
    protected void onStop(){
        super.onStop();
        Log.d("LIFECYCLE", "onStop()");
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d("LIFECYCLE", "onDestroy()");
    }
    @Override
    protected void onRestart(){
        super.onRestart();
        Log.d("LIFECYCLE", "onRestart()");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("LIFECYCLE", "onCreate()");
        viewBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = viewBinding.getRoot();
        setContentView(view);


        viewBinding.btnSum.setOnClickListener(count->{
            counter();
        });

    }

    private void counter() {
        counter++;
        viewBinding.textView.setText(Integer.toString(counter));
    }
}