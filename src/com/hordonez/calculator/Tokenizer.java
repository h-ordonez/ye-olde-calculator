package com.hordonez.calculator;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Tokenizes mathematical expressions.
 *
 * @author Henry Ordonez
 * @version 1.0
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
        Pattern pattern = Pattern.compile("\\d+|[+\\-*/()^]");
        Matcher matcher;

        if(expression != null && !expression.isEmpty()){
            matcher = pattern.matcher(expression);

            while (matcher.find()) {
                tokens.add(matcher.group());
            }
        }
        else{
            throw new IllegalArgumentException("No expression to tokenize.");
        }
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
