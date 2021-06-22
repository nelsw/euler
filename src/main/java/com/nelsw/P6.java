package com.nelsw;

import java.util.stream.IntStream;

/**
 * Find the difference between the sum of the squares of the first one hundred natural numbers & the square of the sum.
 */
public class P6 extends AbstractProblem {

    @Override
    public int number() {
        return 6;
    }

    @Override
    public Object expected() {
        return 25_164_150;
    }

    @Override
    public Object actual() {
        int n = 100;
        return squareOfTheSum(n) - sumOfTheSquares(n);
    }

    Integer sumOfTheSquares(int n) {
        return IntStream.rangeClosed(1, n).map(i -> (int) Math.pow(i, 2)).sum();
    }

    Integer squareOfTheSum(int n) {
        return (int) Math.pow(IntStream.rangeClosed(1, n).sum(), 2);
    }

}
