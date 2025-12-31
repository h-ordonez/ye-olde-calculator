package com.hordonez.calculator;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Tokenizes mathematical expressions.
 *
 * @author Henry Ordonez
 * @version 1.1
 * */
public class Tokenizer {
    private ArrayList<String> tokens;

    public Tokenizer(){
        tokens = new ArrayList<>();
    }

    /**
     * Getter method returns tokens.
     *
     * @return An ArrayList of tokens as strings
     */
    public ArrayList<String> getTokens(){
        return this.tokens;
    }

    /**
     * Converts a mathematical expression into individual tokens and stores the tokens as
     * strings in an ArrayList.
     *
     * @param expression A mathematical expression
     * @throws IllegalArgumentException Method invoked without providing a mathematical expression
     */
    public void tokenize(String expression) {
        tokens.clear(); // Ensure ArrayList is empty

        if(expression == null || expression.isEmpty()){
            throw new IllegalArgumentException("No expression to tokenize.");
        }

        expression = handleImplicitMultiplication(expression);

        Pattern pattern = Pattern.compile("\\d+|[+\\-*/()^]");
        Matcher matcher = pattern.matcher(expression);

        while (matcher.find()) {
            tokens.add(matcher.group());
        }
    }

    /**
     * Pre-processes the expression by converting implicit multiplication into
     * explicit multiplication.
     *
     * @param expression a String representing a mathematical expression
     * @return a String representing a mathematical expression
     */
    private String handleImplicitMultiplication(String expression){
        // Case: number followed by left paren
        expression = expression.replaceAll("(\\d+)\\(", "$1*(");

        // Case: right paren followed by number
        expression = expression.replaceAll("\\)(\\d+)", ")*$1");

        // Case: right paren followed by left paren
        expression = expression.replaceAll("\\)\\(", ")*(");

        return expression;
    }

    /**
     * Helper method used for testing to see how mathematical expression was tokenized.
     */
    public void printTokens(){
        for (String token : this.tokens) {
            System.out.println(token);
        }
    }
}
