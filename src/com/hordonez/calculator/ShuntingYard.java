package com.hordonez.calculator;

import java.util.*;

/**
 * Converts a mathematical expression to postfix form.
 *
 * @author Henry Ordonez
 * @version 2.0
 * */
public class ShuntingYard {
    private Queue<String> postfixQ;

    public ShuntingYard(){
        postfixQ = new LinkedList<>();
    }

    /**
     * Returns the postfix expression as a Queue.
     *
     * @return postfixQ A Queue made up of operators and operands
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
        Deque<String> stack = new ArrayDeque<>();  // Holds operator and parenthesis tokens

        if(!parensAreBalanced(tokens)){
            throw new IllegalArgumentException("Unbalanced parenthesis.");
        }

        for(int i = 0; i < tokens.size(); i++){
            String token = tokens.get(i);

            if(isNumeric(token)){
                postfixQ.add(token);
            }
            else if(token.equals("(")){
                stack.addFirst(token);
            }
            else if(token.equals(")")){
                String tempToken = stack.pollFirst();
                while(!"(".equals(tempToken)){
                    postfixQ.add(tempToken);
                    tempToken = stack.pollFirst();
                }
            }
            else if(token.matches("[+\\-*/^]")){
                Operator currentOp = Operator.lookupBySymbol(token);

                while (!stack.isEmpty()) {
                    Operator topOp = Operator.lookupBySymbol(stack.peekFirst());

                    if(topOp == null) {
                        break; // topOp is not an Operator enum
                    }

                    if (shouldPopOperator(currentOp, topOp)) {
                        postfixQ.add(stack.pollFirst());
                    }
                    else {
                        break;  // Stop comparing operators. The current token has higher precedence.
                    }
                }

                stack.addFirst(token);
            }
            else
                throw new IllegalArgumentException("Unknown token: " + token);
        }

        // Empty the stack
        while(!stack.isEmpty()){
            String remainingOp = stack.pollFirst();
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

    /**
     * Checks if left parenthesis that are present have a corresponding right parenthesis.
     *
     * @param tokens String objects of the mathematical expression
     * @return true if parenthesis are balanced and false otherwise
     */
    private boolean parensAreBalanced(ArrayList<String> tokens){
        Deque<String> stack = new ArrayDeque<>();
        String stackTop;

        for(String token : tokens){
            if("(".equals(token)){
                stack.addFirst(token);
            }
            else if(")".equals(token)){
                stackTop = stack.pollFirst();

                if(!"(".equals(stackTop)){
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }
}
