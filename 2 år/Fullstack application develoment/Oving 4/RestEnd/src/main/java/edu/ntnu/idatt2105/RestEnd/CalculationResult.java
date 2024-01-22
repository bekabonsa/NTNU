package edu.ntnu.idatt2105.RestEnd;

public class CalculationResult {

    private final String expression;
    private final double result;

    public CalculationResult(String expression, String result) {
        this.expression = expression;
        this.result = Double.parseDouble(result);
    }

    public String getExpression() {
        return expression;
    }

    public double getResult() {
        return result;
    }


}