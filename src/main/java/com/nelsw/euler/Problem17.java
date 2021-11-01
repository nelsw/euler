package com.nelsw.euler;

import lombok.extern.log4j.Log4j2;

import java.time.Duration;
import java.time.Instant;
import java.util.Objects;

/**
 * Number Letter Counts
 * <p>
 * If the numbers 1 to 5 are written out in words: one, two, three, four, five, then there are 3 + 3 + 5 + 4 + 4 = 19 letters used in total.
 * <p>
 * If all the numbers from 1 to 1000 (one thousand) inclusive were written out in words, how many letters would be used?
 * <p>
 * NOTE: Do not count spaces or hyphens. For example, 342 (three hundred and forty-two) contains 23 letters and 115 (one hundred and fifteen) contains 20 letters. The use of "and" when writing out numbers is in compliance with British usage.
 */
@Log4j2
public class Problem17 {

    String[] ones = new String[]{
            "", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine",
            "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
    String[] tens = new String[]{"", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};

    public Problem17() {

        var then = Instant.now();

        var actual   = solve();
        var expected = 19;

        if (Objects.equals(actual, expected)) {
            log.info("✅ - {} - {}", Duration.between(then, Instant.now()), actual);
        } else {
            log.warn("❌ - expected=[{}] actual=[{}]", expected, actual);
        }
    }

    private int solve() {

        int sum = 0;
        for (int i = 1; i <= 1000; i++) {
            sum += letterCount(i);
        }


        return sum;
    }

    private int letterCount(int i) {

        if (i == 1000) {
            return 11;
        }

        if (i < 20) {
            return ones[i].length();
        }

        String[] chunks = String.valueOf(i).split("");
        String   s1     = chunks[0];
        String   s2     = chunks[1];

        int i1 = Integer.parseInt(s1);


        if (i < 100) {
            int i2 = Integer.parseInt(s2);
            return tens[i1].length() + ones[i2].length();
        }

        String s3 = chunks[2];

        int r = Integer.parseInt(s2 + s3);

        if (r == 0) {
            return ones[i1].length() + 7;
        }

        return ones[i1].length() + 10 + letterCount(r);
    }


}
