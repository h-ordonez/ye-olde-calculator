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
    private String expression;

    public UserInput(){
        scanner = new Scanner(System.in);
        expression = "";
    }

    /**
     * Setter method that receives expression from the user.
     *
     * @param prompt Asks the user to enter an expression or exit command
     */
    public void setExpression(){
        System.out.print("Enter the expression (or 'exit' to quit): ");
        expression = scanner.nextLine();
    }

    /**
     * Getter method that returns the expression.
     *
     * @return A mathematical expression or exit command in the form of a string
     */
    public String getExpression(){
        return this.expression;
    }

    /**
     * Helper method that closes the scanner object.
     */
    public void closeScanner(){
        scanner.close();
    }

}
