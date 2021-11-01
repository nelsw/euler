package com.nelsw.euler;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.math3.util.CombinatoricsUtils;

import java.time.Duration;
import java.time.Instant;
import java.util.Objects;

/**
 * Starting in the top left corner of a 2×2 grid,
 * and only being able to move to the right and down,
 * there are exactly 6 routes to the bottom right corner.
 * <p>
 * How many such routes are there through a 20×20 grid?
 */
@Log4j2
public class Problem15 {

    public Problem15() {

        var then = Instant.now();

        var actual   = solve();
        var expected = 6;

        if (Objects.equals(actual, expected)) {
            log.info("✅ - {} - {}", Duration.between(then, Instant.now()), actual);
        } else {
            log.warn("❌ - expected=[{}] actual=[{}]", expected, actual);
        }
    }

    private long solve() {
        return CombinatoricsUtils.binomialCoefficient(40, 20);
    }

}
