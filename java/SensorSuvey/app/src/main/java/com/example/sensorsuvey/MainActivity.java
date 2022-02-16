package com.example.sensorsuvey;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;

import com.example.sensorsuvey.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private ActivityMainBinding binding;
    private SensorManager mSensorManager;
    private Sensor mSensorLight;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
//
//        List<Sensor> sensorList = mSensorManager.getSensorList(Sensor.TYPE_ALL);
//
//        StringBuilder sensorText = new StringBuilder();
//        for(Sensor currentSensor : sensorList) {
//            String currentSensorValue = mSensorManager.getDefaultSensor(currentSensor.getType());
//            sensorText.append(currentSensor.getName()).append(System.getProperty("line.separator"));
//        }
//        binding.sensorList.setText(sensorText);

        mSensorLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);


    }
    @Override
    protected void onStop() {
        super.onStop();
        mSensorManager.unregisterListener(this);
    }
    @Override
    public void onStart() {
        super.onStart();
        if(mSensorLight != null){
            mSensorManager.registerListener(this,mSensorLight,SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        int sensorType = sensorEvent.sensor.getType();

        switch (sensorType){
            case Sensor.TYPE_LIGHT:
                binding.sensorList.setText("valor: " + sensorEvent.values[0]);
                break;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}