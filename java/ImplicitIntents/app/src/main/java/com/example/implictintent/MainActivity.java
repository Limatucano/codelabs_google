package com.example.implictintent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.example.implictintent.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding viewBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(viewBinding.getRoot());

        viewBinding.buttonOpenWebsite.setOnClickListener(onClick -> {
            openWebSite();
        });

        viewBinding.buttonOpenLocation.setOnClickListener(onClick -> {
            openLocation();
        });

        viewBinding.buttonShareText.setOnClickListener(onClick -> {
            shareText();
        });
    }

    private void shareText() {
        String textToShare = viewBinding.editShareText.getText().toString();
        String mimeType = "text/plain";

        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setChooserTitle("Share this text with: ")
                .setText(textToShare)
                .startChooser();

    }
    private void openLocation(){
        String locationAddress = viewBinding.editOpenLocation.getText().toString();
        Uri addessUri = Uri.parse("geo:0,0?q=" + locationAddress);
        Intent intent = new Intent(Intent.ACTION_VIEW, addessUri);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d("ImplicitIntents", "Can't handle this intent!");
        }
    }
    private void openWebSite(){
        String url = viewBinding.editOpenWebsite.getText().toString();
        Uri webPage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webPage);

        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
            return;
        }
        Log.d("ImplicitIntents", "Can't handle this!");
    }
}