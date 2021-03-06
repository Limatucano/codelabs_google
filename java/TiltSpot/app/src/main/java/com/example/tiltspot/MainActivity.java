package com.example.tiltspot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import com.example.tiltspot.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private ActivityMainBinding binding;
    private SensorManager mSensorManager;
    private Sensor mSensorAccelerometer;
    private Sensor mSensorMagnetometer;
    private float[] mAccelerometerData = new float[3];
    private float[] mMagnetometerData = new float[3];

    // Very small values for the accelerometer (on all three axes) should
    // be interpreted as 0. This value is the amount of acceptable
    // non-zero drift.
    private static final float VALUE_DRIFT = 0.05f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorMagnetometer = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

    }

    @Override
    public void onStart(){
        super.onStart();

        if(mSensorMagnetometer != null){
            mSensorManager.registerListener(this,mSensorMagnetometer,SensorManager.SENSOR_DELAY_NORMAL);
        }
        if(mSensorAccelerometer != null){
            mSensorManager.registerListener(this,mSensorAccelerometer,SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        int sensorType = sensorEvent.sensor.getType();

        switch (sensorType) {
            case Sensor.TYPE_ACCELEROMETER:
                mAccelerometerData = sensorEvent.values.clone();
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                mMagnetometerData = sensorEvent.values.clone();
                break;
        }
        float[] rotationMatrix = new float[9];
        boolean rotationOK = SensorManager.getRotationMatrix(rotationMatrix,null,mAccelerometerData,mMagnetometerData);
        float orientationValues[] = new float[3];
        if(rotationOK){
            SensorManager.getOrientation(rotationMatrix,orientationValues);
        }
        float azimuth = orientationValues[0];
        float pitch = orientationValues[1];
        float roll = orientationValues[2];

        if(Math.abs(pitch) < VALUE_DRIFT){
            pitch = 0;

        }
        if(Math.abs(roll) < VALUE_DRIFT){
            roll = 0;
        }


        binding.valueAzimuth.setText(getResources().getString(R.string.value_format, azimuth));
        binding.valuePitch.setText(getResources().getString(R.string.value_format,pitch));
        binding.valueRoll.setText(getResources().getString(R.string.value_format,roll));

        binding.spotTop.setAlpha(0f);
        binding.spotBottom.setAlpha(0f);
        binding.spotRight.setAlpha(0f);
        binding.spotLeft.setAlpha(0f);

        if (pitch > 0) {
            binding.spotBottom.setAlpha(pitch);
        } else {
            binding.spotTop.setAlpha(Math.abs(pitch));
        }
        if (roll > 0) {
            binding.spotLeft.setAlpha(roll);
        } else {
            binding.spotRight.setAlpha(Math.abs(roll));
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}