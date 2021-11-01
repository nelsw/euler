package com.nelsw.euler;

import lombok.extern.log4j.Log4j2;

import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class Problem13 {

    public Problem13() {

        var then = Instant.now();

        var actual   = solve();
        var expected = "76576500";

        if (actual.equals(expected)) {
            log.info("✅ - {} - {}", Duration.between(then, Instant.now()), actual);
        } else {
            log.warn("❌ - expected=[{}] actual=[{}]", expected, actual);
        }
    }

    private String solve() {
        List<String> lines;
        try {
            lines = Files.readAllLines(Path.of("problem13.txt"));
        } catch (Exception e) {
            return "";
        }

        List<BigInteger> bigIntegers = new ArrayList<>();
        for (String line : lines) {
            bigIntegers.add(new BigInteger(line));
        }

        BigInteger sum = BigInteger.ZERO;
        for (BigInteger bi : bigIntegers) {
            sum = sum.add(bi);
        }

        return sum.toString().substring(0, 10);
    }

}
