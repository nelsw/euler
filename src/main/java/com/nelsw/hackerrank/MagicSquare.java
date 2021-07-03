package com.nelsw.hackerrank;

import lombok.extern.log4j.Log4j2;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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

        List<Integer> p = s.stream().flatMap(Collection::stream).collect(Collectors.toList());

        Map<Integer, Integer> points = new HashMap<>();
        for (int i = 0; i < p.size(); i++) {
            points.put(i, p.get(i));
        }

        System.out.println("points");
        points.entrySet().forEach(System.out::println);
        System.out.println();

        Map<Integer, Integer> map = IntStream.range(0, eqs.size()).boxed().collect(Collectors.toMap(i -> i, sum(p)));

        System.out.println("sums");
        map.values().forEach(print);
        System.out.println();

        int mode = mode(map.values());

        System.out.println("mode");
        System.out.println(mode);
        System.out.println();

        List<Integer> keys = map.entrySet().stream().filter(e -> !e.getValue().equals(mode)).map(Map.Entry::getKey).collect(Collectors.toList());

        System.out.println("keys");
        keys.forEach(print);
        System.out.println();

        List<Integer> all = keys.stream().map(eqs::get).flatMap(Collection::stream).sorted().collect(Collectors.toList());

        List<Integer> indexes = new ArrayList<>();
        while (!all.isEmpty()) {
            int thisMode = mode(all);
            indexes.add(thisMode);
            all.removeAll(Collections.singletonList(thisMode));
        }

        System.out.println("indexes");
        indexes.forEach(print);
        System.out.println();

        int x1 = indexes.get(0);
        int x2 = indexes.get(1);
        int x3 = indexes.get(2);

        List<List<Integer>> problems = keys
                .stream()
                .map(eqs::get)
                .sorted((o1, o2) -> {

                    if (o1.contains(x1) && o1.contains(x2) && o1.contains(x3) &&
                            o2.contains(x1) && o2.contains(x2) && !o2.contains(x3)) {
                        return -1;
                    }

                    if (o1.contains(x1) && o1.contains(x2) && !o1.contains(x3) &&
                            o2.contains(x1) && o2.contains(x2) && o2.contains(x3)) {
                        return 1;
                    }

                    if (o1.contains(x1) && o1.contains(x2) &&
                            o2.contains(x1) && !o2.contains(x2)) {
                        return -1;
                    }

                    if (o1.contains(x1) && !o1.contains(x2) &&
                            o2.contains(x1) && o2.contains(x2)) {
                        return 1;
                    }

                    if (o1.contains(x1) && !o2.contains(x1)) {
                        return -1;
                    }

                    if (!o1.contains(x1) && o2.contains(x1)) {
                        return 1;
                    }

                    return 0;
                })
                .collect(Collectors.toList());

        System.out.println("problems");
        problems.forEach(print);
        System.out.println();


        var c = cost(p, indexes);
        if (c != 0) {
            return c;
        }

        return foo(p, indexes, problems, mode);
    }

    private static Comparator<Integer> bySeverity(List<Integer> indexes) {
        return (o1, o2) -> {
            if (o1.equals(indexes.get(0)) || o1.equals(indexes.get(1))) {
                return -1;
            }
            if (o2.equals(indexes.get(0)) || o2.equals(indexes.get(1))) {
                return 1;
            }
            return o1.compareTo(o2);
        };
    }


    private static int foo(List<Integer> p, List<Integer> indexes, List<List<Integer>> problems, int mode) {

        int cost = 0;


        for (List<Integer> problem : problems) {

            problem.sort(bySeverity(indexes));

            int x = p.get(problem.get(0));
            int y = p.get(problem.get(1));
            int z = p.get(problem.get(2));

            int sum = x + y + z;

            System.out.println("sum " + sum);

            for (int i = 1; i < 10; i++) {

                x = x + i;
                if (x > 9) {
                    x = x - 9;
                }

                boolean found = Stream.of(x, y, z).mapToInt(Integer::intValue).sum() == mode;

                if (found) {

                    p = swap(p, problem.get(0), x);
                    p = swap(p, problem.get(1), y);
                    p = swap(p, problem.get(2), z);

                    if (isMagicSquare(p)) {
                        System.out.println("yeah boy");
                        return cost;
                    }
                    break;
                }

                for (int j = 1; j < 10; j++) {

                    y = y + j;
                    if (y > 9) {
                        y = y - 9;
                    }


                    found = Stream.of(x, y, z).mapToInt(Integer::intValue).sum() == mode;
                    if (found) {
                        p = swap(p, problem.get(0), x);
                        p = swap(p, problem.get(1), y);
                        p = swap(p, problem.get(2), z);
                        if (isMagicSquare(p)) {
                            System.out.println("yeah boy");
                            return cost;
                        }
                        break;
                    }


                    for (int k = 1; k < 10; k++) {
                        z = z + k;
                        if (z > 9) {
                            z = z - 9;
                        }

                        found = Stream.of(x, y, z).mapToInt(Integer::intValue).sum() == mode;
                        if (found) {
                            p = swap(p, problem.get(0), x);
                            p = swap(p, problem.get(1), y);
                            p = swap(p, problem.get(2), z);
                            if (isMagicSquare(p)) {
                                System.out.println("yeah boy");
                                return cost;
                            }
                            break;
                        }
                    }
                    if (found) {
                        break;
                    }
                }
                if (found) {
                    break;
                }
            }
        }

        if (isMagicSquare(p)) {
            System.out.println("yeah boy");
            return cost;
        }

        return 0;
    }

    private static int cost(List<Integer> p, List<Integer> indexes) {

        List<Integer> c = result(p, indexes);

        if (!c.isEmpty()) {
            return c.get(0);
        }

        return 0;
    }

    private static List<Integer> result(List<Integer> p, List<Integer> indexes) {
        return result(p, indexes, 0);
    }

    private static List<Integer> result(List<Integer> p, List<Integer> indexes, int start) {
        return IntStream.range(1, 5)
                .boxed()
                .map(i ->
                        IntStream.range(start, indexes.size())
                                .boxed()
                                .map(j -> cost(p, indexes, i, j, 0))
                                .collect(Collectors.toList())
                )
                .flatMap(Collection::stream)
                .filter(i -> i > 0)
                .sorted()
                .collect(Collectors.toList());
    }

    private static List<Integer> swapDn(List<Integer> p, int idx, int i) {
        int x = p.get(idx) - i;
        if (x < 1) {
            x = 9 - x;
        }
        return swap(p, idx, x);
    }

    private static List<Integer> swapUp(List<Integer> p, int idx, int i) {
        int x = p.get(idx) + i;
        if (x > 9) {
            x = x - 9;
        }
        return swap(p, idx, x);
    }

    private static List<Integer> swap(List<Integer> p, int idx, int x) {
        List<Integer> t = new ArrayList<>(p);
        t.remove(idx);
        t.add(idx, x);
        return t;
    }

    private static int cost(List<Integer> p, List<Integer> indexes, int i, int j, int sum) {

        if (j >= indexes.size()) {
            return 0;
        }

        sum += i;

        int idx = indexes.get(j);

        List<Integer> t1 = swapUp(p, idx, i);
        if (isMagicSquare(t1)) {
            return sum;
        }

        List<Integer> t2 = swapDn(p, idx, i);
        if (isMagicSquare(t2)) {
            return sum;
        }

        int cost = cost(t1, indexes, i, j + 1, sum);
        if (cost != 0) {
            return cost;
        }

        return cost(t2, indexes, i, j + 1, sum);
    }


    private static Function<Integer, Integer> sum(List<Integer> v) {
        return i -> eqs.get(i).stream().parallel().mapToInt(v::get).sum();
    }

    private static boolean isMagicSquare(List<Integer> v) {

        Collection<Integer> values = IntStream
                .range(0, eqs.size())
                .parallel()
                .boxed()
                .collect(Collectors.toMap(i -> i, sum(v)))
                .values();

        return values.stream().allMatch(i -> i.equals(mode(values)));
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