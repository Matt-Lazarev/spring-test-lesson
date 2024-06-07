package com.lazarev.springtestlesson.util;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CalculatorTest {

    @Autowired
    Calculator calculator;

    @Test
    void add() {
        int n1 = 10;
        int n2 = 20;

        int result = calculator.add(n1, n2);
        assertEquals(30, result);
    }

    @Test
    void div_throwArithmeticException_secondParameterZero() {
        int n1 = 10;
        int n2 = 0;

        ArithmeticException ex = assertThrows(
                ArithmeticException.class,
                () -> calculator.div(n1, n2)
        );
        assertEquals("Cannot divide by zero", ex.getMessage());
    }

    @Test
    void div_correctResult_secondParameterNotZero() {
        int n1 = 10;
        int n2 = 5;

        int result = calculator.div(n1, n2);
        assertEquals(2, result);
    }

    @Test
    void isPositive_true_parameterGreaterThenZero() {
        int n = 10;

        assertTrue(calculator.isPositive(n));
    }

    @Test
    void isPositive_false_parameterEqualsZero() {
        int n = 0;

        assertFalse(calculator.isPositive(n));
    }

    @Test
    void isPositive_false_parameterLessThenZero() {
        int n = -2;

        assertFalse(calculator.isPositive(n));
    }

    @Test
    void sub_correctResult_ifSubMoreThenOrEqualZero() {
        assertAll(
                () -> assertEquals(1, calculator.sub(10, 9)),
                () -> assertEquals(0, calculator.sub(10, 10))
        );
    }

    @Test
    void sub_throwArithmeticException_ifSubLessThenZero() {
        int n1 = 10;
        int n2 = 15;

        assertThrows(
                ArithmeticException.class,
                () -> calculator.sub(n1, n2)
        );
    }
}