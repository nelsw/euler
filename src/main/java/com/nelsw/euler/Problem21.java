package com.nelsw.euler;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.math3.primes.Primes;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Amicable Numbers
 * <p>
 * <p>
 * Let d(n) be defined as the sum of proper divisors of n (numbers less than n which divide evenly into n).
 * If d(a) = b and d(b) = a, where a ≠ b, then a and b are an amicable pair and each of a and b are called amicable numbers.
 * <p>
 * For example,
 * the proper divisors of 220 are 1, 2, 4, 5, 10, 11, 20, 22, 44, 55 and 110; therefore d(220) = 284.
 * The proper divisors of 284 are 1, 2, 4, 71 and 142; so d(284) = 220.
 * <p>
 * Evaluate the sum of all the amicable numbers under 10000.
 */
@Log4j2
public class Problem21 {

    // 1, 2, 4, 5, 10, 11, 20, 22, 44, 55

    public Problem21() {

        var then = Instant.now();

        var actual   = solve();
        var expected = 6;

        if (Objects.equals(actual, expected)) {
            log.info("✅ - {} - {}", Duration.between(then, Instant.now()), actual);
        } else {
            log.warn("❌ - expected=[{}] actual=[{}]", expected, actual);
        }
    }

    private Object solve() {

        int result = 0;

        for (int i = 1; i < 300; i++) {

            System.out.println("i = " + i);

            int sumA = sumProperDivisors(i);
            System.out.print("sumA: " + sumA + "\n");

            int sumB = sumProperDivisors(sumA);
            System.out.print("sumB: " + sumB + "\n");

            if (sumA != i && i == sumB) {
                System.out.println("match " + i + " = " + sumB);
                result = result + sumA + sumB;
            }

            System.out.println();
        }
        return result;
    }


    private int sumProperDivisors(int i) {

        if (i > 2) {
            System.out.println(Arrays.toString(Primes.primeFactors(i).toArray()));
        }


        List<Integer> l = new ArrayList<>();

        for (int j = 1; j <= i / 2; j++) {
            if (i % j == 0) {
                l.add(j);
            }
        }

        System.out.println(Arrays.toString(l.toArray()));

        return l.stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

}
