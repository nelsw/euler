package com.nelsw.euler;

import lombok.extern.log4j.Log4j2;

import java.util.function.IntUnaryOperator;
import java.util.stream.IntStream;

@Log4j2
public class Problem9 {

    int goal = (int) Math.pow(10, 3), max = goal / 2, other = 0;
    IntUnaryOperator a = a -> IntStream.range(a + 1, max).map(b(a)).max().orElse(other);

    public Problem9() {
        System.out.println(solve());
    }

    private int solve() {
        return IntStream.range(100, max).map(a).max().orElseThrow();
    }

    IntUnaryOperator b(int a) {
        return b -> IntStream.range(b + 1, max).map(c(a, b)).max().orElse(other);
    }

    IntUnaryOperator c(int a, int b) {
        return c -> (Math.pow(a, 2) + Math.pow(b, 2) == Math.pow(c, 2) && a + b + c == goal) ? a * b * c : 0;
    }
}
