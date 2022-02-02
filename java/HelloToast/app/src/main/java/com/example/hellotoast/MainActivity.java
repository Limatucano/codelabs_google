package com.example.hellotoast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.hellotoast.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private int mCount = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding viewBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(viewBinding.getRoot());

        viewBinding.countButton.setOnClickListener(onClick -> {
            countUp();
            updateResult(viewBinding);
        });

        viewBinding.toastButton.setOnClickListener(onClick -> {
            showToast();
        });
    }

    private void showToast(){
        Toast.makeText(this,R.string.toast_message, Toast.LENGTH_SHORT).show();
    }

    private void countUp(){
        mCount++;
    }
    private void updateResult(ActivityMainBinding viewBinding){
        viewBinding.resultText.setText(Integer.toString(mCount));
    }
}