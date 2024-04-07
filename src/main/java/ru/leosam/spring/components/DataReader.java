package ru.leosam.spring.components;

import ru.leosam.spring.Model;

import java.util.Scanner;
import java.util.function.Supplier;

@Component
public class DataReader implements Supplier<Model> {
    @Override
    public Model get() {
        System.out.print("> ");
        Model model = new Model();
        Scanner sc = new Scanner(System.in);
        model.op = sc.next();
        model.x = sc.nextDouble();
        model.y = sc.nextDouble();
        return model;
    }
}
