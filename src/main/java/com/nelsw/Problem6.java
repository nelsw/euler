package com.nelsw;

import lombok.extern.log4j.Log4j2;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.IntStream;

@Log4j2
public class Problem6 {

    public Problem6() {
        var then     = Instant.now();
        var n        = 100;
        var actual   = squareOfTheSum(n) - sumOfTheSquares(n);
        var expected = 25_164_150;
        if (actual == expected) {
            log.info("✅ - {} - {}", Duration.between(then, Instant.now()), actual);
        } else {
            log.warn("❌");
        }
    }

    Integer sumOfTheSquares(int n) {
        return IntStream.rangeClosed(1, n).map(i -> (int) Math.pow(i, 2)).sum();
    }

    Integer squareOfTheSum(int n) {
        return (int) Math.pow(IntStream.rangeClosed(1, n).sum(), 2);
    }
}
