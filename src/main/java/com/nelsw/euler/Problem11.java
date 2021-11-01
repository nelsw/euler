package com.nelsw.euler;

import lombok.extern.log4j.Log4j2;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

/**
 * Largest Product in a grid
 * What is the greatest product of four adjacent numbers in the same direction (up, down, left, right, or diagonally) in the 20×20 grid?
 */
@Log4j2
public class Problem11 {

    public Problem11() {

        var then = Instant.now();

        var actual   = solve();
        var expected = 70600674;

        if (actual == expected) {
            log.info("✅ - {} - {}", Duration.between(then, Instant.now()), actual);
        } else {
            log.warn("❌ - expected=[{}] actual=[{}]", expected, actual);
        }
    }

    private int solve() {

        List<String> lines;
        try {
            lines = Files.readAllLines(Path.of("problem11.txt"));
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

        int[][] grid = new int[20][20];

        for (int i = 0; i < 20; i++) {
            String[] chunks = lines.get(i).split("\\s");
            for (int j = 0; j < 20; j++) {
                grid[i][j] = Integer.parseInt(chunks[j]);
            }
        }

        for (int i = 0; i < 20; i++) {
            log.debug(Arrays.toString(grid[i]));
        }

        int max = 0;

        // across
        for (int i = 0; i < 20; i++) {

            for (int j = 0; j < 17; j++) {

                int a = grid[i][j];
                int b = grid[i][j + 1];
                int c = grid[i][j + 2];
                int d = grid[i][j + 3];
                int p = a * b * c * d;

                if (p > max) {
                    max = p;
                }
            }
        }

        // down diag
        for (int i = 0; i < 17; i++) {
            for (int j = 0; j < 17; j++) {

                int a = grid[i][j];
                int b = grid[i + 1][j + 1];
                int c = grid[i + 2][j + 2];
                int d = grid[i + 3][j + 3];
                int p = a * b * c * d;

                if (p > max) {
                    max = p;
                }
            }
        }

        for (int i = 3; i < 20; i++) {
            for (int j = 0; j < 17; j++) {

                int a = grid[i][j];
                int b = grid[i - 1][j + 1];
                int c = grid[i - 2][j + 2];
                int d = grid[i - 3][j + 3];
                int p = a * b * c * d;

                if (p > max) {
                    max = p;
                }
            }
        }

        for (int i = 0; i < 17; i++) {
            for (int j = 0; j < 20; j++) {
                int a = grid[i][j];
                int b = grid[i + 1][j];
                int c = grid[i + 2][j];
                int d = grid[i + 3][j];
                int p = a * b * c * d;

                if (p > max) {
                    max = p;
                }
            }
        }


        return max;
    }

}
