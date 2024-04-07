package ru.leosam.spring;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.leosam.spring.components.DivisionOperation;
import ru.leosam.spring.components.MinusOperation;
import ru.leosam.spring.components.MultiplyOperation;
import ru.leosam.spring.components.PlusOperation;

public class AppTest {
    @Test
    @DisplayName("Тесты выполнения операций")
    public void testOperations() {
        Assertions.assertEquals(6.0d, new MultiplyOperation().apply(2.0d, 3.0d));
        Assertions.assertEquals(12.0d, new PlusOperation().apply(4.0d, 8.0d));
        Assertions.assertEquals(-3.0d, new MinusOperation().apply(2.0d, 5.0d));
        Assertions.assertEquals(-8.0d, new DivisionOperation().apply(-64.0d, 8.0d));
    }
}
