package com.nelsw.hackerrank;

import lombok.extern.log4j.Log4j2;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Log4j2
public class Frequency {

    public static void main(String[] args) throws Exception {

        List<List<Integer>> queries = new ArrayList<>();

        for (String line : Files.readAllLines(Path.of("queries.txt"))) {
            queries.add(Stream
                    .of(line.split("\\s"))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList()));
        }

        var expect = Files
                .readAllLines(Path.of("answers.txt"))
                .stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        long alpha  = System.currentTimeMillis();
        var  actual = freqQuery(queries);
        long omega  = System.currentTimeMillis();
        System.out.println(omega - alpha + "ms");

        for (int i = 0; i < expect.size(); i++) {
            var x = actual.get(i);
            var y = expect.get(i);
            if (!x.equals(y)) {
                System.out.println(" index " + i);
                System.out.println("actual " + x);
                System.out.println("expect " + y);
                System.out.println(queries.get(i));
                return;
            }
        }

//        queries = List.of(
//                List.of(1, 5),
//                List.of(1, 6),
//                List.of(3, 2),
//                List.of(1, 10),
//                List.of(1, 10),
//                List.of(1, 6),
//                List.of(2, 5),
//                List.of(3, 2));
//
//        expect = List.of(0,1);
//        alpha  = System.currentTimeMillis();
//        actual = freqQuery(queries);
//        omega  = System.currentTimeMillis();
//        System.out.println(omega - alpha + "ms");
//
//        for (int i = 0; i < expect.size(); i++) {
//            var x = actual.get(i);
//            var y = expect.get(i);
//            if (!x.equals(y)) {
//                System.out.println(" index " + i);
//                System.out.println("actual " + x);
//                System.out.println("expect " + y);
//                System.out.println(queries.get(i));
//                return;
//            }
//        }
    }


    static List<Integer> freqQuery(List<List<Integer>> queries) {

        Set<Integer> critical = queries
                .stream()
                .filter(l -> l.get(0) == 3)
                .map(l -> l.get(1))
                .collect(Collectors.toSet());

        List<Integer> results = new ArrayList<>();

        Map<Integer, Integer> m = new LinkedHashMap<>();

        for (int i = 0; i < queries.size(); i++) {

            List<Integer> query = queries.get(i);

            int x = query.get(0);
            int y = query.get(1);

            if (x == 1) {
                if (m.containsKey(y)) {
                    m.put(y, m.getOrDefault(y, 0) + 1);
                } else {
                    m.put(y, 1);
                }
                continue;
            }

            if (x == 2) {
                if (m.containsKey(y)) {
                    long z = m.get(y);
                    if (z > 0) {
                        m.put(y, m.get(y) - 1);
                    }
                }
                continue;
            }

            if (i < y) {
                results.add(0);
                continue;
            }

            boolean found = false;

            if (Collections.frequency(m.values(), y) > 0) {
                found = true;
            }

            results.add(found ? 1 : 0);

        }

        return results;
    }

}
