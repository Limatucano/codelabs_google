package com.example.simplecalc;

import static android.app.PendingIntent.getActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.util.Log;

import com.example.simplecalc.databinding.ActivityMainBinding;

import java.util.concurrent.atomic.AtomicReference;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding viewBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(viewBinding.getRoot());

        Calculator calculator = new Calculator();
        MutableLiveData result = new MutableLiveData<Double>();
        viewBinding.operationAddBtn.setOnClickListener(onClick -> {
            result.postValue(calculator.add(getOperandEditTexts()[0], getOperandEditTexts()[1]));
        });

        viewBinding.operationSubBtn.setOnClickListener(onClick -> {
            result.postValue(calculator.sub(getOperandEditTexts()[0], getOperandEditTexts()[1]));
        });

        viewBinding.operationMulBtn.setOnClickListener(onClick -> {
            result.postValue(calculator.mult(getOperandEditTexts()[0], getOperandEditTexts()[1]));
        });

        viewBinding.operationDivBtn.setOnClickListener(onClick -> {
            result.postValue(calculator.div(getOperandEditTexts()[0], getOperandEditTexts()[1]));
        });

        result.observe(this, new Observer() {

            @Override
            public void onChanged(Object o) {
                viewBinding.operationResultTextView.setText(o.toString());
            }
        });


    }

    public double[] getOperandEditTexts(){
        double operandOne = Double.parseDouble(viewBinding.operandOneEditText.getText().toString());
        double operandTwo = Double.parseDouble(viewBinding.operandTwoEditText.getText().toString());

        return new double[]{operandOne,operandTwo};
    }

}