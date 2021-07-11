package com.nelsw.euler;

import lombok.extern.log4j.Log4j2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * It is possible to write five as a sum in exactly six different ways:
 * <p>
 * 4 + 1
 * 3 + 2
 * 3 + 1 + 1
 * 2 + 2 + 1
 * 2 + 1 + 1 + 1
 * 1 + 1 + 1 + 1 + 1
 * <p>
 * How many different ways can one hundred be written as a sum of at least two positive integers?
 */
@Log4j2
public class Problem76 {


    public Problem76() {

        int expected = 190569291;

        List<Integer> numbers = IntStream.rangeClosed(1, 99).boxed().collect(Collectors.toList());

        Map<Integer, Integer> m = new HashMap<>();

        for (int number : numbers) {

            for (int j = number; j <= 100; j++) {

                int c = m.getOrDefault(j, 0);
                int d = m.getOrDefault(j - number, 1);

                m.put(j, c + d);

            }
        }

        System.out.println(m.get(100));
    }


}
