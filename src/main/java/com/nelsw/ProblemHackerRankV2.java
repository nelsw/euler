package com.nelsw;

import java.util.*;
import java.util.stream.Collectors;

public class ProblemHackerRankV2 {

    public ProblemHackerRankV2() {

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

//        indexes = List.of(
//                List.of(2, 3),
//                List.of(4, 5),
//                List.of(6, -1),
//                List.of(-1, 7),
//                List.of(8, 9),
//                List.of(10, 11),
//                List.of(12, 13),
//                List.of(-1, 14),
//                List.of(-1, -1),
//                List.of(15, -1),
//                List.of(16, 17),
//                List.of(-1, -1),
//                List.of(-1, -1),
//                List.of(-1, -1),
//                List.of(-1, -1),
//                List.of(-1, -1),
//                List.of(-1, -1));

        var queries = List.of(2, 4);

//        queries = List.of(2, 3);

        var result = swapNodes(indexes, queries);

        var expected = List.of(
                List.of(2, 9, 6, 4, 1, 3, 7, 5, 11, 8, 10),
                List.of(2, 6, 9, 4, 1, 3, 7, 5, 10, 8, 11)
        );

//        expected = List.of(
//                List.of(14, 8, 5, 9, 2, 4, 13, 7, 12, 1, 3, 10, 15, 6, 17, 11, 16),
//                List.of(9, 5, 14, 8, 2, 13, 7, 12, 4, 1, 3, 17, 11, 16, 6, 10, 15)
//        );

        System.out.println();
        System.out.println(Arrays.toString(result.toArray()));
        System.out.println(Arrays.toString(expected.toArray()));
    }

    public static void root(List<Integer> results, Node node) {
        traverse(results, node.left);
        results.add(1);
        traverse(results, node.right);
    }

    public static void traverse(List<Integer> results, Node node) {


        if (node == null) {
            return;
        }

        int d = node.data;

        if (node.left == null || node.right == null) {
            if (d != -1 && !results.contains(d)) {
                results.add(d);
            }
            return;
        }


        int l = node.left.data;
        int r = node.right.data;

        if (l == -1) {

            if (!results.contains(d)) {
                results.add(d);
            }

            traverse(results, node.right);

            if (r != -1 && !results.contains(r)) {
                results.add(r);
            }
            return;
        }


        if (r != -1) {

            traverse(results, node.left);

            if (!results.contains(l)) {
                results.add(l);
            }

            if (!results.contains(d)) {
                results.add(d);
            }

            traverse(results, node.right);

            if (!results.contains(r)) {
                results.add(r);
            }
            return;
        }

        traverse(results, node.left);
        traverse(results, node.right);

        if (!results.contains(d)) {
            results.add(d);
        }
    }

    public static List<List<Integer>> swapNodes(List<List<Integer>> indexes, List<Integer> queries) {

        List<List<Integer>> results = new ArrayList<>();

        Map<Integer, List<Integer>> tree = tree(indexes);

        Map<Integer, List<List<Integer>>> levels = levels(tree);

        for (Integer query : queries) {
            int n = levels.size() / query;
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

            int level = i + 1;

            if (level == 2) {
                List<List<Integer>> list = new ArrayList<>();
                list.add(tree.get(1));
                levels.put(level, list);
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

        int                 q     = query + 1;
        List<List<Integer>> list  = levels.get(q);
        List<List<Integer>> lists = new ArrayList<>();

        for (List<Integer> l : list) {

            int k0 = l.get(0);
            int k1 = l.get(1);

            List<Integer> newList = new ArrayList<>();
            newList.add(k1);
            newList.add(k0);

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

        Map<Integer, List<Integer>> oak = tree
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        int lastIdx = levels.size() + 1;
        if (levels.get(lastIdx).stream().allMatch(integers -> integers.equals(List.of(-1, -1)))) {
            levels.remove(lastIdx);
        }

        List<Integer> results = new ArrayList<>();

        Node root = node(oak, 1);

        root(results, root);

        return results;
    }

    private static Node node(Map<Integer, List<Integer>> oak, int data) {
        if (!oak.containsKey(data)) {
            return new Node(data, null, null);
        }
        return new Node(data, node(oak, oak.get(data).get(0)), node(oak, oak.get(data).get(1)));
    }

    private static class Node {

        int data;

        Node left, right;

        public Node(int data, Node left, Node right) {
            this.data  = data;
            this.left  = left;
            this.right = right;
        }

    }


}
