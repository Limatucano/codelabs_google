package com.example.simplecalc;

public class Calculator {

    public double add(double firstOperand, double secondOperand) {
        return firstOperand + secondOperand;
    }
    public double sub(double firstOperand, double secondOperand) {
        return firstOperand - secondOperand;
    }
    public double mult(double firstOperand, double secondOperand) {
        return firstOperand * secondOperand;
    }
    public double div(double firstOperand, double secondOperand) {
        if(secondOperand == 0){
            return 0.0;
        }
        return firstOperand / secondOperand;
    }
}
