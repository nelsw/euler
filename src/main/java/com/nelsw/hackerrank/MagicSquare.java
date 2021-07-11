package com.nelsw.hackerrank;

import lombok.extern.log4j.Log4j2;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Log4j2
public class MagicSquare {

    static final         Consumer<Object>    print = System.out::println;
    private static final List<List<Integer>> eqs   = Arrays.asList(
            Arrays.asList(0, 1, 2),
            Arrays.asList(3, 4, 5),
            Arrays.asList(6, 7, 8),
            Arrays.asList(0, 3, 6),
            Arrays.asList(1, 4, 7),
            Arrays.asList(2, 5, 8),
            Arrays.asList(0, 4, 8),
            Arrays.asList(6, 4, 2));

    public static int formingMagicSquare(List<List<Integer>> s) {

        List<Integer> origin = s.stream().flatMap(Collection::stream).collect(Collectors.toList());

        Map<Integer, Integer> p = new HashMap<>();
        for (int i = 0; i < 9; i++) {
            p.put(i, origin.get(i));
        }

        System.out.println("points");
        p.entrySet().forEach(System.out::println);
        System.out.println();

        int m = mode(IntStream
                .range(0, eqs.size())
                .boxed()
                .collect(Collectors.toMap(i -> i, i -> eqs.get(i).stream().parallel().mapToInt(p::get).sum()))
                .values());

        System.out.println("mode " + m);
        System.out.println();

        List<Integer> r = IntStream.range(0, 6).boxed().collect(Collectors.toList());

        List<Integer> c = new ArrayList<>();

        r.stream().takeWhile(i -> c.isEmpty()).forEach(i1 -> {

            magic(p, m, 0, 0, i1).ifPresent(c::add);

            r.stream().takeWhile(i -> c.isEmpty()).forEach(i2 -> {

                magic(p, m, i1, 1, i2).ifPresent(c::add);

                r.stream().takeWhile(i -> c.isEmpty()).forEach(i3 -> {

                    magic(p, m, i1 + i2, 2, i3).ifPresent(c::add);

                    r.stream().takeWhile(i -> c.isEmpty()).forEach(i4 -> {

                        magic(p, m, i1 + i2 + i3, 3, i4).ifPresent(c::add);

                        r.stream().takeWhile(i -> c.isEmpty()).forEach(i5 -> {

                            magic(p, m, i1 + i2 + i3 + i4, 4, i5).ifPresent(c::add);

                            r.stream().takeWhile(i -> c.isEmpty()).forEach(i6 -> {

                                magic(p, m, i1 + i2 + i3 + i4 + i5, 5, i6).ifPresent(c::add);

                                r.stream().takeWhile(i -> c.isEmpty()).forEach(i7 -> {

                                    magic(p, m, i1 + i2 + i3 + i4 + i5 + i6, 6, i7).ifPresent(c::add);

                                    r.stream().takeWhile(i -> c.isEmpty()).forEach(i8 -> {

                                        magic(p, m, i1 + i2 + i3 + i4 + i5 + i6 + i7, 7, i8).ifPresent(c::add);

                                        r.stream().takeWhile(i -> c.isEmpty()).forEach(i9 -> {

                                            magic(p, m, i1 + i2 + i3 + i4 + i5 + i6 + i7 + i8, 8, i9).ifPresent(c::add);

                                        });
                                    });
                                });
                            });
                        });
                    });
                });
            });
        });

        c.forEach(print);

        return c.stream().mapToInt(Integer::intValue).min().getAsInt();
    }

    private static OptionalInt magic(Map<Integer, Integer> points, int mode, int sum, int idx, int i) {

        int v = points.get(idx), w = v + i;

        if (w > 9) {
            w = w - 9;
        }

        points.put(idx, w);
        if (magic(points, mode)) {
            return OptionalInt.of(sum + i);
        }

        int x = v - i;
        if (x < 0) {
            x = 9 + x;
        }

        points.put(idx, x);
        if (magic(points, mode)) {
            return OptionalInt.of(sum + i);
        }

        return OptionalInt.empty();

    }

    private static boolean magic(Map<Integer, Integer> p, int mode) {
        return IntStream
                .range(0, 8)
                .parallel()
                .boxed()
                .mapToInt(i -> eqs.get(i).stream().parallel().mapToInt(p::get).sum())
                .allMatch(i -> i == mode);
    }

    private static int mode(Collection<Integer> list) {
        return list
                .stream()
                .parallel()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .get()
                .getKey();
    }

}