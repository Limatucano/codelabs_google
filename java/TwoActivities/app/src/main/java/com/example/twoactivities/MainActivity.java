package com.example.twoactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.twoactivities.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.twoactivities.extra.MESSAGE";
    public static final int TEXT_REQUEST = 1;
    public ActivityMainBinding viewBinding;
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(LOG_TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "onResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "onPause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(LOG_TAG, "onRestart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "onStop");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LOG_TAG, "onCreate");
        viewBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(viewBinding.getRoot());

        viewBinding.buttonSend.setOnClickListener(onClick -> {

            Intent intent = new Intent(this,SecondActivity.class);
            intent.putExtra(EXTRA_MESSAGE, viewBinding.editTextMain.getText().toString());
            startActivityForResult(intent,TEXT_REQUEST);
        });


    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        boolean replyIsVisible = savedInstanceState.getBoolean("reply_visible");
        if(replyIsVisible){
            viewBinding.textHeaderReply.setVisibility(View.VISIBLE);
            viewBinding.textMessageReply.setText(savedInstanceState.getString("reply_text"));
            viewBinding.textMessageReply.setVisibility(View.VISIBLE);

        }

    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if(viewBinding.textHeaderReply.getVisibility() == View.VISIBLE) {
            bundle.putBoolean("reply_visible", true);
            bundle.putString("reply_text", viewBinding.textMessageReply.getText().toString());
        }

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TEXT_REQUEST) {
            if (resultCode == RESULT_OK) {
                String reply = data.getStringExtra(SecondActivity.EXTRA_REPLY);
                viewBinding.textHeaderReply.setVisibility(View.VISIBLE);
                viewBinding.textMessageReply.setText(reply);
                viewBinding.textMessageReply.setVisibility(View.VISIBLE);
            }
        }
    }
}