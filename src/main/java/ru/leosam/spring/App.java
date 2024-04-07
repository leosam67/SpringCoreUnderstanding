package ru.leosam.spring;

import ru.leosam.spring.components.OperationMaker;

public class App
{
    public static void main( String[] args )
    {
        new ObjectComposer("ru.leosam.spring.components")
                .getObjectByNameAndType("OperationMaker", OperationMaker.class)
                .make();
    }
}
