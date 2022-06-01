package com.irpc.api.calculator;

public class Calculator implements CalculatorService {
    public <T> T add(T a, T b) {
        if (a instanceof Number && b instanceof Number) {
           return (T) (Double)((((Number) a).doubleValue() + ((Number) b).doubleValue()));
        } else {
            return(T) (a.toString() + b.toString());
        }
    }
}
