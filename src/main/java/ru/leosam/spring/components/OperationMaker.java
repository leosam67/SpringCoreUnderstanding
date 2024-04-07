package ru.leosam.spring.components;

import ru.leosam.spring.Model;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Component
public class OperationMaker {
    @Inject
    Supplier<Model> dataReader;
    @Inject
    Consumer<Model> printer;
    @Inject("mainMap")
    Map<String, BinaryOperator<Double>> operatorMap = new HashMap<>();
    public void make() {
        if(operatorMap.isEmpty()) throw new IllegalArgumentException("Map is empty");
        Model model = dataReader.get();
        model.res = operatorMap.get(model.op).apply(model.x, model.y);
        printer.accept(model);
    }
}
