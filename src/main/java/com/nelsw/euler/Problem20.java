package com.nelsw.euler;

import lombok.extern.log4j.Log4j2;

import java.math.BigInteger;
import java.time.Duration;
import java.time.Instant;
import java.util.Objects;

/**
 * Factoral Digit Sum
 * <p>
 * n! means n × (n − 1) × ... × 3 × 2 × 1
 * <p>
 * For example, 10! = 10 × 9 × ... × 3 × 2 × 1 = 3628800,
 * and the sum of the digits in the number 10! is 3 + 6 + 2 + 8 + 8 + 0 + 0 = 27.
 * <p>
 * Find the sum of the digits in the number 100!
 */
@Log4j2
public class Problem20 {

    public Problem20() {
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

        BigInteger total = BigInteger.ONE;
        for (long i = 100; i > 0; i--) {
            total = total.multiply(BigInteger.valueOf(i));
        }

        long     sum  = 0;
        String[] nums = total.toString().split("");
        for (String num : nums) {
            sum += Long.parseLong(num);
        }

        return sum;
    }

}
