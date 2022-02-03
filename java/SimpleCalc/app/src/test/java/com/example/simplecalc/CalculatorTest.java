package com.example.simplecalc;


import org.junit.Test;
import static org.junit.Assert.*;

public class CalculatorTest {
    Calculator calculator = new Calculator();
    @Test
    public void addTwoNumbersPositive(){
        double result = calculator.add(2.3, 2.2);
        assertEquals(4.5, result,0);
    }
    @Test
    public void addTwoNumbersNegative(){
        double result = calculator.add(-1.4, -2.6);
        assertEquals(-4.0, result,0);
    }

    @Test
    public void subTwoNumbersPositive(){
        double result = calculator.sub(2.0,4.6);
        assertEquals(-2.6,result, 0.01);
    }
    @Test
    public void subTwoBigNumbers(){
        double result = calculator.sub(312383210.34231, 24122.33);
        assertEquals(312359088.01231, result, 0);
    }
    @Test
    public void mulTwoNumbers(){
        double result = calculator.mult(3,6);
        assertEquals(18.0, result,0.001);
    }
    @Test
    public void divTwoNumbers(){
        double result = calculator.div(4,2);
        assertEquals(2,result,0.001);
    }

    @Test
    public void divNumberByZero(){
        double result = calculator.div(4,0);
        assertEquals(0,result,0);

    }


}
