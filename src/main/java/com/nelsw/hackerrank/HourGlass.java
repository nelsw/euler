package com.nelsw.hackerrank;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Given a 6x6 2D Array, arr:
 * <p>
 * 1 1 1 0 0 0
 * 0 1 0 0 0 0
 * 1 1 1 0 0 0
 * 0 0 0 0 0 0
 * 0 0 0 0 0 0
 * 0 0 0 0 0 0
 * <p>
 * An hourglass in is a subset of values with indices falling in this pattern in 's graphical representation:
 * <p>
 * a b c
 * d
 * e f g
 * <p>
 * Print the largest (maximum) hourglass sum found in a given 2D array.
 */
public class HourGlass {

    public static int hourglassSum(List<List<Integer>> arr) {

        int length = arr.get(0).size() - 2;
        int height = arr.size() - 2;

        return IntStream
                .range(0, height)
                .boxed()
                .map(i -> IntStream
                        .range(0, length)
                        .boxed()
                        .map(j -> Arrays.asList(i, j))
                        .collect(Collectors.toList()))
                .flatMap(Collection::stream)
                .map(l -> {
                    int x = l.get(0), y = l.get(1);
                    return IntStream
                            .range(x, x + 3)
                            .boxed()
                            .map(i -> IntStream
                                    .range(y, y + 3)
                                    .boxed()
                                    .filter(j -> i != x + 1 || i == x + 1 && j == y + 1)
                                    .map(j -> arr.get(i).get(j))
                                    .collect(Collectors.toList()))
                            .flatMap(Collection::parallelStream)
                            .mapToInt(Integer::intValue)
                            .sum();
                })
                .mapToInt(Integer::intValue)
                .boxed()
                .max(Integer::compareTo)
                .get();
    }

}
