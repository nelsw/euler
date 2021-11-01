package com.nelsw.euler;

import lombok.extern.log4j.Log4j2;

import java.util.Collection;
import java.util.HashMap;
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
 * How many ways can one hundred be written as a sum of at least two positive integers?
 */
@Log4j2
public class Problem76 {

    public Problem76() {

        System.out.println(expandedAnswer());
    }

    public static void main(String[] args) {
        var p = new Problem76();
    }

    int answer() {

        // define a hashmap to store
        Map<Integer, Integer> m = new HashMap<>();
        for (int number : IntStream.rangeClosed(1, 99).boxed().collect(Collectors.toList())) {
            for (int j = number; j <= 100; j++) {
                m.put(j, m.getOrDefault(j, 0) + m.getOrDefault(j - number, 1));
            }
        }

        return m.get(100);
    }

    int expandedAnswer() {

        var a = System.currentTimeMillis();

        // define a map to store the amount of
        // ways we can sum numbers up to 100
        Map<Integer, Integer> m = new HashMap<>();

        // create a collection of ints from 1 -> 99
        // imagine these represent the summation of
        // the left side of the equation
        Collection<Integer> left = IntStream
                .rangeClosed(1, 99)
                .boxed()
                .collect(Collectors.toList());

        // iterate over the left side of the
        // equation with an enhanced for loop
        // ... for each int the "left" collection ...
        for (int l : left) {

            // define a traditional for loop to
            // iterate from the "left" int
            // present in our enhanced for loop
            // while the right int is less than
            // or equal to our target of 100
            for (int r = l; r <= 100; r++) {

                // get the amount ways we can sum this right int
                // or default to zero if no value is present
                int mr = m.getOrDefault(r, 0);

                // get the amount of ways we sum the difference
                // between left and right ints ... by using the
                // difference we ensure to stay at or under 100
                // or default to one if no value is present
                int ml = m.getOrDefault(r - l, 1);

                // finally, put the sum these "ways" into our
                // map so that we can use the result for future
                // calculations. When r reaches 100, for every
                // left int, we will arrive at our answer.
                m.put(r, mr + ml);
            }
        }

        System.out.println(System.currentTimeMillis() - a);

        return m.get(100);
    }

}
