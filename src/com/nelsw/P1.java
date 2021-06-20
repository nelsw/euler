package com.nelsw;

import java.util.stream.IntStream;

/*

If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9.
The sum of these multiples is 23.

Find the sum of all the multiples of 3 or 5 below 1000.

 */
public class P1 extends AbstractProblem {

    protected P1() {
        super(1);
    }

    @Override
    Object expected() {
        return 233_168;
    }

    @Override
    Object actual() {
        return IntStream
                .range(0, 1_000)
                .boxed()
                .filter(i -> (i % 3 == 0 || i % 5 == 0))
                .mapToInt(Integer::intValue)
                .sum();
    }
}
