package com.hordonez.calculator;

/**
* This class is the main driver program.
*
* @author Henry Ordonez
* @version 1.0
* */
public class CalculatorApp {
    public static void main(String[] args) {
        UserInput input = new UserInput();
        Calculator calc = new Calculator();
        double num1;
        double num2;
        double result;
        char operator;

        System.out.println("Hello and welcome to Ye Olde Calculator!");

        num1 = input.getNumber("Please enter the first number: ");
        operator = input.getOperator();
        num2 = input.getNumber("Please enter the second number: ");

        if(operator == '+'){
            result = calc.add(num1, num2);
        }
        else if(operator == '-'){
            result = calc.subtract(num1, num2);
        }
        else if(operator == '*'){
            result = calc.multiply(num1, num2);
        }
        else if(operator == '/'){
            result = calc.divide(num1, num2);
        }
        else {
            result = 0.0;
            System.out.println("You must enter a valid operator.");
        }

        System.out.println("Result: " + result);
        input.closeScanner();

    }
}