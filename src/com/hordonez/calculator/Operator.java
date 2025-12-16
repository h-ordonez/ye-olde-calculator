package com.hordonez.calculator;

/**
 * Handles enumeration of mathematical operators.
 *
 * @author Henry Ordonez
 * @version 1.0
 */
public enum Operator {
    ADD("+", 1, true),
    SUBTRACT("-", 1, true),
    MULTIPLY("*", 2, true),
    DIVIDE("/", 2, true),
    EXPONENT("^", 3, false);    // Right-associative

    private final String symbol;
    private final int precedence;
    private final boolean leftAssociative;

    Operator(String symbol, int precedence, boolean leftAssociative){
        this.symbol = symbol;
        this.precedence = precedence;
        this.leftAssociative = leftAssociative;
    }

    /**
     * Returns the symbol representation of the mathematical operator.
     *
     * @return The operator symbol
     */
    public String getSymbol() { return symbol; }

    /**
     * Returns the precedence value of an operator.
     *
     * @return A numeric representation of the mathematical operator's precedence
     */
    public int getPrecedence() { return precedence; }

    /**
     * Tests whether an operator is left-associative.
     *
     * @return Boolean value indicating if the operator is left associative
     */
    public boolean isLeftAssociative() { return leftAssociative; }

    /**
     * Compares the passed in value to see if it is an Operator enum.
     *
     * @param c A string object that will be used to determine if it is a mathematical operator
     * @return An Operator enum object if the tested value is a mathematical operator or null if it is not
     */
    public static Operator lookupBySymbol(String c) {
        for (Operator op : Operator.values()) {
            if (op.symbol.equals(c)) return op;
        }
        return null;
    }

}
