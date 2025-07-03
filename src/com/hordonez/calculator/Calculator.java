package com.hordonez.calculator;

/**
* Description: Performs the arithmetic operations.
*
* @author Henry Ordonez
* @version 1.0
* */
public class Calculator {
    /**
     * Calculates the sum of two numbers.
     *
     * @param num1 the first number
     * @param num2 the second number
     * @return the sum of num1 and num2
     * */
    public double add(double num1, double num2){
        return num1 + num2;
    }

    /**
     * Calculates the difference of two numbers.
     *
     * @param num1 the first number
     * @param num2 the second number
     * @return the difference of num1 and num2
     * */
    public double subtract(double num1, double num2){
        return num1 - num2;
    }

    /**
     * Calculates the product of two numbers.
     *
     * @param num1 the first number
     * @param num2 the second number
     * @return the product of num1 and num2
     * */
    public double multiply(double num1, double num2){
        return num1 * num2;
    }

    /**
     * Calculates the quotient of two numbers.
     *
     * @param num1 the first number
     * @param num2 the second number
     * @return the quotient of num1 and num2
     * */
    public double divide(double num1, double num2){
        return num1 / num2;
    }
}
