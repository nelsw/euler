package com.nelsw;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Log4j2
public class ProblemHackerRank {

    public ProblemHackerRank() {

        var indexes = List.of(
                List.of(2, 3),
                List.of(4, -1),
                List.of(5, -1),
                List.of(6, -1),
                List.of(7, 8),
                List.of(-1, 9),
                List.of(-1, -1),
                List.of(10, 11),
                List.of(-1, -1),
                List.of(-1, -1),
                List.of(-1, -1));

        var queries = List.of(2, 4);

        var result = swapNodes(indexes, queries);

        var expected = List.of(
                List.of(2, 9, 6, 4, 1, 3, 7, 5, 11, 8, 10),
                List.of(2, 6, 9, 4, 1, 3, 7, 5, 10, 8, 11)
        );

        System.out.println();
        log.info(result);
        log.error(expected);
    }

    public static List<List<Integer>> swapNodes(List<List<Integer>> indexes, List<Integer> queries) {

        List<List<Integer>> results = new ArrayList<>();

        Map<Integer, List<Integer>> tree = tree(indexes);

        Map<Integer, List<List<Integer>>> levels = levels(tree);

        for (Integer query : queries) {
            var n = levels.size() / query;
            for (int i = 1; i <= n; i++) {
                query(levels, i * query);
            }
            results.add(results(levels));
        }


        return results;
    }

    private static Map<Integer, List<Integer>> tree(List<List<Integer>> indexes) {
        Map<Integer, List<Integer>> tree = new HashMap<>();
        for (int i = 0; i < indexes.size(); i++) {
            if (i == 0) {
                tree.put(1, indexes.get(i));
            } else {
                tree.put(i + 1, indexes.get(i));
                tree.put(i + 2, indexes.get(i));
            }
        }
        return tree;
    }

    private static Map<Integer, List<List<Integer>>> levels(Map<Integer, List<Integer>> tree) {

        Map<Integer, List<List<Integer>>> levels = new HashMap<>();
        for (int i = 1; i < tree.size(); i++) {

            var level = i + 1;

            if (level == 2) {
                levels.put(level, List.of(tree.get(1)));
                continue;
            }

            List<List<Integer>> result = new ArrayList<>();
            if (levels.containsKey(level - 1)) {
                for (List<Integer> pair : levels.get(level - 1)) {
                    for (Integer key : pair) {
                        if (key != -1) {
                            result.add(tree.get(key));
                        }
                    }
                }
                if (!result.isEmpty()) {
                    levels.put(level, result);
                }
            }
        }
        return levels;
    }

    private static void query(Map<Integer, List<List<Integer>>> levels, int query) {

        var q     = query + 1;
        var list  = levels.get(q);
        var lists = new ArrayList<List<Integer>>();

        for (List<Integer> l : list) {

            var k0 = l.get(0);
            var k1 = l.get(1);

            lists.add(List.of(k1, k0));
        }
        levels.put(q, lists);
    }

    private static List<Integer> results(Map<Integer, List<List<Integer>>> levels) {

        Map<Integer, List<Integer>> tree = new HashMap<>();

        int i = 1;
        for (List<List<Integer>> list : levels.values()) {
            for (List<Integer> pair : list) {
                tree.put(i++, pair);
            }
        }

        debug(tree, levels);

        Map<Integer, List<Integer>> oak = tree
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        debug(oak, levels);

        var lastIdx = levels.size() + 1;
        if (levels.get(lastIdx).stream().allMatch(integers -> integers.equals(List.of(-1, -1)))) {
            levels.remove(lastIdx);
        }

        System.out.println();
        levels.entrySet().forEach(log::warn);
        System.out.println();


        foo(oak, levels);

        var results = new ArrayList<Integer>();

        results.add(2);

        for (int j = 5; j > 2; j--) {

            List<List<Integer>> lists = levels.getOrDefault(j, new ArrayList<>());

            if (lists.isEmpty()) {
                continue;
            }

            List<Integer> list = lists.get(0);

            int data = list.get(0);

            if (data != -1) {
                results.add(data);
            }

        }

        for (int j = 5; j > 2; j--) {

            List<List<Integer>> lists = levels.getOrDefault(j, new ArrayList<>());

            if (lists.isEmpty()) {
                continue;
            }

            List<Integer> list = lists.get(0);

            int data = list.get(1);

            if (data != -1) {
                results.add(data);
            }

        }

        results.add(1);
        results.add(3);

        for (int j = 5; j > 2; j--) {

            List<List<Integer>> lists = levels.getOrDefault(j, new ArrayList<>());

            if (lists.isEmpty()) {
                continue;
            }

            List<Integer> list = lists.get(1);

            int data = list.get(0);

            if (data != -1) {
                results.add(data);
            }

        }

        for (int j = 5; j > 2; j--) {

            List<List<Integer>> lists = levels.getOrDefault(j, new ArrayList<>());

            if (lists.isEmpty()) {
                continue;
            }

            List<Integer> list = lists.get(1);

            int data = list.get(1);

            if (data != -1) {
                results.add(data);
            }

        }

        for (int j = 5; j > 3; j--) {

            List<List<Integer>> lists = levels.getOrDefault(j, new ArrayList<>());

            if (lists.size() < 3) {
                continue;
            }

            List<Integer> list = lists.get(2);

            int data = list.get(0);

            if (data != -1) {
                results.add(data);
            }

        }

        for (int j = 5; j > 3; j--) {

            List<List<Integer>> lists = levels.getOrDefault(j, new ArrayList<>());

            if (lists.size() < 3) {
                continue;
            }

            List<Integer> list = lists.get(2);

            int data = list.get(1);

            if (data != -1) {
                results.add(data);
            }

        }

        return results;
    }

    private static void foo(Map<Integer, List<Integer>> tree, Map<Integer, List<List<Integer>>> levels) {

        var result = new ArrayList<Integer>();

        int left  = 2;
        int right = 3;

        result.add(left);

        if (nextLeft(tree, left) == -1) {

            var l = nextLeft(tree, right);
            var r = nextRight(tree, right);


        }
    }

    private static int nextLeft(Map<Integer, List<Integer>> tree, int k) {
        return tree.get(k).get(0);
    }

    private static int nextRight(Map<Integer, List<Integer>> tree, int k) {
        return tree.get(k).get(1);
    }

    private static void debug(Map<Integer, List<Integer>> tree, Map<Integer, List<List<Integer>>> levels) {
        System.out.println();
        tree.entrySet().forEach(log::debug);
        System.out.println();
        levels.entrySet().forEach(log::debug);
    }

    @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
    @RequiredArgsConstructor
    @ToString
    private static class Node {
        int depth, data;
        Node left, right;
    }

}
