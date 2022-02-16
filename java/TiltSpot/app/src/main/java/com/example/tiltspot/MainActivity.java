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
        float z = orientationValues[0];
        float y = orientationValues[1];
        float x = orientationValues[2];

        binding.valueAzimuth.setText(getResources().getString(R.string.value_format, z));
        binding.valuePitch.setText(getResources().getString(R.string.value_format,y));
        binding.valueRoll.setText(getResources().getString(R.string.value_format,x));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}