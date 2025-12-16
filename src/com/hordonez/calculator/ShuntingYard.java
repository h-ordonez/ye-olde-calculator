package com.hordonez.calculator;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Converts a mathematical expression to postfix form.
 *
 * @author Henry Ordonez
 * @version 1.0
 * */
public class ShuntingYard {
    private Queue<String> postfixQ;

    public ShuntingYard(){
        postfixQ = new LinkedList<>();
    }

    /**
     * Getter method
     *
     * @return postfixQ
     */
    public Queue<String> getPostfixQ(){
        return this.postfixQ;
    }

    /**
     * Converts a mathematical expression from an infix expression to a postfix expression and
     * stores the result in the postfix queue. This method is based on the Shunting Yard algorithm.
     *
     * @param tokens Operands and operators taken as strings
     * @throws IllegalArgumentException for unbalanced parenthesis and unknown tokens
     */
    public void makePostfix(ArrayList<String> tokens){
        postfixQ.clear();   // Ensure queue is empty before starting
        Stack<String> stack = new Stack<String>();  // Holds operator and parenthesis tokens
        boolean isLeftParenPresent = false;
        boolean parensAreBalanced = true;   // Default assumption

        for(String token : tokens){
            String tempToken = "";

            if(isNumeric(token)){
                postfixQ.add(token);
            }
            else if(token.equals("(")){
                isLeftParenPresent = true;
                parensAreBalanced = false;
                stack.push(token);
            }
            else if(token.equals(")")){
                if(!isLeftParenPresent){
                    parensAreBalanced = false;  // Should never encounter a right paren before a left paren
                    throw new IllegalArgumentException("Unbalanced expression: Missing left parenthesis.");
                }
                else {
                    tempToken = stack.pop();
                    while(!tempToken.equals("(")){
                        postfixQ.add(tempToken);
                        tempToken = stack.pop();    // Will ultimately pop left paren
                    }

                    isLeftParenPresent = false; // Reset flag because left paren was popped.
                    parensAreBalanced = true;   // Matching paren found so assume parens are balanced.
                }
            }
            else if(token.matches("[+\\-*/^]")){
                Operator currentOp = Operator.lookupBySymbol(token);

                if (currentOp == null) {
                    throw new IllegalArgumentException("Unknown operator: " + token);
                }

                while (!stack.isEmpty()) {
                    Operator topOp = Operator.lookupBySymbol(stack.peek());
                    if(topOp == null)
                        break; // topOp is not an Operator enum

                    if (shouldPopOperator(currentOp, topOp)) {
                        postfixQ.add(stack.pop());
                    }
                    else {
                        break;  // Stop comparing operators. The current token has higher precedence.
                    }
                }

                stack.push(token);
            }
            else
                throw new IllegalArgumentException("Unknown token: " + token);
        }

        if(!parensAreBalanced){
            throw new IllegalArgumentException("Unbalanced parenthesis: Missing right parenthesis.");
        }

        // Empty the stack
        while(!stack.isEmpty()){
            String remainingOp = stack.pop();
            postfixQ.add(remainingOp);
        }
    }

    /**
     * Determines if one operator should be popped off the stack or if another should be pushed on. This is based
     * on whether an operator is right or left associative and on the operator's precedence. An operator with lower
     * precedence should not be on top of an operator with higher precedence on the stack.
     *
     * @param currOp The operator whose precedence will be compared to the one on the stack
     * @param topOp The operator that is at the top of the stack
     * @return Boolean value that will indicate if the top operator should be popped off the stack or if the current
     *         operator should be pushed on
     */
    private static boolean shouldPopOperator(Operator currOp, Operator topOp){
        return (currOp.isLeftAssociative() && currOp.getPrecedence() <= topOp.getPrecedence()) ||
                (!currOp.isLeftAssociative() && currOp.getPrecedence() < topOp.getPrecedence());
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
