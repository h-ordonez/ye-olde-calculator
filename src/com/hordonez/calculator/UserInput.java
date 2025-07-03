package com.hordonez.calculator;

import java.util.Scanner;

/**
 * Handles user input.
 *
 * @author Henry Ordonez
 * @version 1.0
 */
public class UserInput {
    private final Scanner scanner;

    public UserInput(){
        scanner = new Scanner(System.in);
    }

    public double getNumber(String prompt){
        System.out.print(prompt);
        return scanner.nextDouble();
    }

    public char getOperator(){
        System.out.print("Please enter the operator (+, -, *, /): ");
        return scanner.next().charAt(0);
    }

    public void closeScanner(){
        scanner.close();
    }

}
