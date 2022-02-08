package com.example.scorekepper;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    private int mScore1 = 0;
    private int mScore2 = 0;

    public MutableLiveData decreaseScore(@NonNull View view){
        switch(view.getId()){
            case R.id.decreaseTeam1:
                mScore1 -= 1;
                return new MutableLiveData(mScore1);
            case R.id.decreaseTeam2:
                mScore2 -= 1;
                return new MutableLiveData(mScore2);
        }
        return new MutableLiveData(0);
    }

    public MutableLiveData increaseScore(@NonNull View view){
        switch(view.getId()){
            case R.id.increaseTeam1:
                mScore1 += 1;
                return new MutableLiveData(mScore1);
            case R.id.increaseTeam2:
                mScore2 += 1;
                return new MutableLiveData(mScore2);
        }
        return new MutableLiveData(0);
    }
}
