package com.nelsw;

import lombok.extern.log4j.Log4j2;

import java.math.BigInteger;
import java.time.Duration;
import java.time.Instant;
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
@Log4j2
public class Problem3 {

    public Problem3(long expected) {
        var then = Instant.now();
        if (actual() == expected) {
            log.info("✅ - {} - {}", Duration.between(then, Instant.now()), expected);
        } else {
            log.warn("❌");
        }
    }

    private long actual() {

        // jic k > Integer.MAX_VALUE
        BigInteger n = new BigInteger("600851475143");

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
