package com.nelsw.euler;

import lombok.extern.log4j.Log4j2;

import java.time.*;
import java.util.Objects;

/**
 * Counting Sundays
 * <p>
 * You are given the following information, but you may prefer to do some research for yourself.
 * <p>
 * 1 Jan 1900 was a Monday.
 * Thirty days has September,
 * April, June and November.
 * All the rest have thirty-one,
 * Saving February alone,
 * Which has twenty-eight, rain or shine.
 * And on leap years, twenty-nine.
 * A leap year occurs on any year evenly divisible by 4, but not on a century unless it is divisible by 400.
 * <p>
 * How many Sundays fell on the first of the month during the twentieth century (1 Jan 1901 to 31 Dec 2000)?
 */
@Log4j2
public class Problem19 {

    public Problem19() {

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

        int total = 0;

        for (int i = 1901; i < 2001; i++) {

            for (int j = 1; j < 13; j++) {

                var ldt = LocalDateTime.of(LocalDate.of(i, j, 1), LocalTime.MIN);
                if (ldt.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
                    total++;
                }

            }

        }


        return total;
    }


}
