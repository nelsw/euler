package com.nelsw.euler;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;

import java.time.Duration;
import java.time.Instant;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

/**
 * Problem 4 - Largest palindrome product
 * <p>
 * A palindromic number reads the same both ways.
 * <p>
 * The largest palindrome made from the product of two 2-digit numbers is 9009 = 91 × 99.
 * <p>
 * Find the largest palindrome made from the product of two 3-digit numbers.
 */
@Log4j2
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Problem4 {

    static int seed = 999;

    static UnaryOperator<Integer> op = i -> --i;

    static Predicate<String> pal = s -> s.length() % 2 == 0 && s
            .substring(0, s.length() / 2)
            .equals(new StringBuilder(s.substring(s.length() / 2)).reverse().toString());


    public Problem4(int expected) {

        var then = Instant.now();
        var actual = Stream
                .iterate(seed, op)
                .limit(seed)
                .mapToInt(Integer::intValue)
                .map(i -> Stream
                        .iterate(seed, op)
                        .limit(seed)
                        .mapToInt(j -> i * j)
                        .mapToObj(Objects::toString)
                        .filter(pal)
                        .mapToInt(Integer::parseInt)
                        .findFirst()
                        .orElse(0))
                .max()
                .orElseThrow();

        if (actual == expected) {
            log.info("✅ - {} - {}", Duration.between(then, Instant.now()), actual);
        } else {
            log.info("❌ - {} - {}", Duration.between(then, Instant.now()), expected);
        }
    }


}
