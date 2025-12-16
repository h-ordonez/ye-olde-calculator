package com.hordonez.calculator;

/**
* Handles arithmetic operations.
*
* @author Henry Ordonez
* @version 1.1
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
     * Calculates the base number raised to an exponent.
     *
     * @param num1 the first number
     * @param num2 the second number
     * @return the power of num1 raised to exponent num2
     * */
    public double exponent(double num1, double num2){
        return Math.pow(num1, num2);
    }

    /**
     * Calculates the quotient of two numbers.
     *
     * @param num1 the first number
     * @param num2 the second number
     * @return the quotient of num1 and num2
     * @throws  ArithmeticException if num2 is zero
     * */
    public double divide(double num1, double num2){
        if(num2 == 0){
            throw new ArithmeticException("Cannot divide by zero.");
        }
        return num1 / num2;
    }

    /**
     * Performs the appropriate operation given two operands.
     *
     * @param op the operation to be performed
     * @param num1 the first number
     * @param num2 the second number
     * @return the result of the operation
     * @throws IllegalArgumentException Invalid operator was entered
     * */
    public double doCalculation(char op, double num1, double num2){
        double result = 0.0;

        if(op == '+'){
            result = add(num1, num2);
        }
        else if(op == '-'){
            result = subtract(num1, num2);
        }
        else if(op == '*'){
            result = multiply(num1, num2);
        }
        else if(op == '/'){
            result = divide(num1, num2);
        }
        else if(op == '^'){
            result = exponent(num1, num2);
        }
        else {
            throw new IllegalArgumentException("The following operator is not valid: " + op);
        }

        return result;
    }
}
