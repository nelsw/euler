package com.nelsw;

import java.math.BigInteger;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import static java.math.BigInteger.TWO;
import static java.math.BigInteger.ZERO;

/**
 * Problem 3 - Largest prime factor
 * <p>
 * The prime factors of 13195 are 5, 7, 13 and 29.
 * <p>
 * What is the largest prime factor of the number 600851475143 ?
 */
public class P3 extends AbstractProblem {

    @Override
    int number() {
        return 3;
    }

    @Override
    Object expected() {
        return 6_857L;
    }

    @Override
    Object actual() {
        return solution("600851475143");
    }

    private Object solution(String k) {

        // jic k > Integer.MAX_VALUE
        BigInteger n = new BigInteger(k);

        // divide n in half until it becomes an odd number
        while (n.mod(TWO).equals(ZERO)) {
            n = n.divide(TWO);
        }

        Set<Long>  results = new HashSet<>();
        double     sqrt    = n.sqrt().intValue();
        BigInteger v;

        // for every odd number from the first prime factor to the sqrt of n
        for (long i = 3; i <= sqrt; i += 2) {

            // define a BigInteger from the primitive int iteration value
            v = BigInteger.valueOf(i);

            // while n / i has a remainder of 0, add to results, divide n by i
            while (n.mod(v).equals(ZERO)) {
                results.add(i);
                n = n.divide(v);
            }
        }

        // if n is a prime number greater than 2
        if (n.compareTo(TWO) > 0) {
            results.add(n.longValue());
        }

        return results
                .stream()
                .sorted(Comparator.reverseOrder())
                .limit(1)
                .findFirst()
                .orElseThrow();
    }

}
