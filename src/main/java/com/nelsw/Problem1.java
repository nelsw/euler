package com.nelsw;

import lombok.extern.log4j.Log4j2;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.IntStream;


/**
 * Problem 1 - Multiples of 3 and 5
 * <p>
 * If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9, with a sum of 23.
 * <p>
 * Problem: Find the sum of all the multiples of 3 or 5 below 1000.
 */
@Log4j2
public class Problem1 {

    public Problem1(int expected) {
        var then = Instant.now();
        var actual = IntStream
                .range(0, 1_000)
                .boxed()
                .filter(i -> (i % 3 == 0 || i % 5 == 0))
                .mapToInt(Integer::intValue)
                .sum();
        if (actual == expected) {
            log.info("✅ - {} - {}", Duration.between(then, Instant.now()), actual);
        } else {
            log.warn("❌");
        }
    }
}
