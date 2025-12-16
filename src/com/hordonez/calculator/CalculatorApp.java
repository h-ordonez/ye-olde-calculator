package com.hordonez.calculator;

/**
* The main driver of the program.
*
* @author Henry Ordonez
* @version 2.0
* */
public class CalculatorApp {
    public static void main(String[] args) {
        UserInput input = new UserInput();
        Tokenizer tokenizer = new Tokenizer();
        ShuntingYard postfixMaker = new ShuntingYard();
        PostfixEvaluator postfixEvaluator = new PostfixEvaluator();
        String prompt = "Please enter the expression (or 'exit' to quit): ";
        String expression;
        double result;

        System.out.println("Hello and welcome to Ye Olde Calculator!");

        while(true){
            input.setExpression(prompt);
            expression = input.getExpression();
            if(expression.equalsIgnoreCase("exit")) break;

            try {
                tokenizer.tokenize(expression);
                postfixMaker.makePostfix(tokenizer.getTokens());
                System.out.println("Postfix: " + String.join(" ", postfixMaker.getPostfixQ()));
                result = postfixEvaluator.evalPostfix(postfixMaker.getPostfixQ());
                System.out.println("Answer: " + result);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

        }

        input.closeScanner();
    }
}