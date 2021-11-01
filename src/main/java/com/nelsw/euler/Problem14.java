package com.nelsw.euler;

import lombok.extern.log4j.Log4j2;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Longest Collatz sequence
 * <p>
 * <p>
 * The following iterative sequence is defined for the set of positive integers:
 * <p>
 * n → n/2 (n is even)
 * n → 3n + 1 (n is odd)
 * <p>
 * Using the rule above and starting with 13, we generate the following sequence:
 * 13 → 40 → 20 → 10 → 5 → 16 → 8 → 4 → 2 → 1
 * <p>
 * It can be seen that this sequence (starting at 13 and finishing at 1) contains 10 terms. Although it has not been proved yet (Collatz Problem), it is thought that all starting numbers finish at 1.
 * <p>
 * Which starting number, under one million, produces the longest chain?
 * <p>
 * NOTE: Once the chain starts the terms are allowed to go above one million.
 */
@Log4j2
public class Problem14 {

    public Problem14() {

        var then = Instant.now();

        var actual   = solve();
        var expected = 10;

        if (Objects.equals(actual, expected)) {
            log.info("✅ - {} - {}", Duration.between(then, Instant.now()), actual);
        } else {
            log.warn("❌ - expected=[{}] actual=[{}]", expected, actual);
        }
    }

    private int solve() {
        int pos = 0;
        int max = 0;
        for (int i = 3; i < 1_000_000; i++) {

            long       term  = collatz(i);
            List<Long> terms = new ArrayList<>();
            terms.add(term);
            while (term > 1) {
                term = collatz(term);
                terms.add(term);
            }
            if (terms.size() > max) {
                max = terms.size();
                pos = i;
                System.out.println(max);
            }
        }
        return pos;
    }

    private long collatz(long i) {
        if (i % 2 == 0) {
            return i / 2;
        } else {
            return (3 * i) + 1;
        }
    }


}
