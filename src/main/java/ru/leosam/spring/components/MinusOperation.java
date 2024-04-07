package ru.leosam.spring.components;

import java.util.function.BinaryOperator;

@Component("-")
public class MinusOperation implements BinaryOperator<Double> {
    @Override
    public Double apply(Double x, Double y) {
        return x - y;
    }
}
