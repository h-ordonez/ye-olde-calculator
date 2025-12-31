package com.hordonez.calculator;

import java.util.Queue;
import java.util.Stack;

/**
 * Evaluates postfix expressions.
 *
 * @author Henry Ordonez
 * @version 1.0
 * */
public class PostfixEvaluator {

    /**
     * Evaluates postfix expressions.
     *
     * @param postfixQ Queue in postfix form
     * @return Result of evaluating the postfix expression
     * */
    public double evalPostfix(Queue<String> postfixQ){
        Stack<Double> operands = new Stack<>();
        Calculator calculator = new Calculator();
        double result = 0.0;

        String currentToken = postfixQ.poll();
        while(currentToken != null){
            if(isNumeric(currentToken)){
                operands.push(Double.parseDouble(currentToken));
            }
            else {
                double num1 = operands.pop();
                double num2 = operands.pop();
                result = calculator.doCalculation(currentToken.charAt(0), num2, num1);
                operands.push(result);
            }

            currentToken = postfixQ.poll();
        }
        result = operands.pop();
        return result;
    }

    /**
     * Determines if the string is a number.
     *
     * @param str String to be tested
     * @return Boolean value indicating if string is a number
     */
    private boolean isNumeric(String str) {
        if (str == null) return false;
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
