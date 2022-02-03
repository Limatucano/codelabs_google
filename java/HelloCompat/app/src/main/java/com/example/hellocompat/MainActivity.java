package com.example.hellocompat;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Build;
import android.os.Bundle;

import com.example.hellocompat.databinding.ActivityMainBinding;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding viewBinding;
    private String[] colors = {"red", "pink", "purple", "deep_purple",
            "indigo", "blue", "light_blue", "cyan", "teal", "green",
            "light_green", "lime", "yellow", "amber", "orange", "deep_orange",
            "brown", "grey", "blue_grey", "black" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(viewBinding.getRoot());

        viewBinding.colorButton.setOnClickListener(onClick -> {
            changeColor();
        });
        if(savedInstanceState != null){
            viewBinding.helloTextView.setTextColor(savedInstanceState.getInt("color"));
        }
    }

    private void changeColor(){
        Random random = new Random();
        String colorName = colors[random.nextInt(colors.length -1)];
        int colorResourceName = getResources().getIdentifier(colorName,"color", getApplicationContext().getPackageName());
        int colorId = ContextCompat.getColor(this,colorResourceName);
        viewBinding.helloTextView.setTextColor(colorId);
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("int", viewBinding.helloTextView.getCurrentTextColor());
    }

}