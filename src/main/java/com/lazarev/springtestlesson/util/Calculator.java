package com.lazarev.springtestlesson.util;

import org.springframework.stereotype.Component;

@Component
public class Calculator {
    public int add(int n1, int n2) {
        return n1 + n2;
    }

    public int div(int n1, int n2) {
        if(n2 == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        return n1 / n2;
    }

    public boolean isPositive(int n) {
        return n > 0;
    }

    // n1 - n2 ( < 0 -> ex)
    public int sub(int n1, int n2) {
        int result = n1 - n2;
        if(result < 0) {
            throw new ArithmeticException();
        }
        return result;
    }
}
