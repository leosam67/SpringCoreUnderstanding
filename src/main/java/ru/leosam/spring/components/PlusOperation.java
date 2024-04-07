package ru.leosam.spring.components;

import java.util.function.BinaryOperator;

@Component("+")
public class PlusOperation implements BinaryOperator<Double> {
    @Override
    public Double apply(Double x, Double y) {
        return x + y;
    }
}
