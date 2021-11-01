package com.nelsw.euler;

import lombok.extern.log4j.Log4j2;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Log4j2
public class Problem22 {

    String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public Problem22() {

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

        String line = null;
        try {
            line = Files.readAllLines(Path.of("names.txt")).get(0);
        } catch (Exception e) {
            return 0;
        }

        List<String> names = Arrays.asList(line.split(","));
        Collections.sort(names);

        int total = 0;

        for (int i = 0; i < names.size(); i++) {
            int rank = i + 1;

            String[] letters = names
                    .get(i)
                    .replaceAll("\"", "")
                    .split("");

            int score = 0;
            for (String letter : letters) {
                score += alphabet.indexOf(letter) + 1;
            }

            total += rank * score;
        }

        return total;
    }

}
