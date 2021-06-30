package com.nelsw.hackerrank;

import java.util.*;
import java.util.stream.Collectors;

public class SwapNodes {

    public SwapNodes() {

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

    public static List<List<Integer>> swapNodes(List<List<Integer>> indexes, List<Integer> queries) {

        // make a tree
        Map<Integer, List<Integer>> tree = new HashMap<>();
        for (int i = 0; i < indexes.size(); i++) {
            if (i == 0) {
                tree.put(1, indexes.get(i));
            } else {
                tree.put(i + 1, indexes.get(i));
                tree.put(i + 2, indexes.get(i));
            }
        }

        // map the depths
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

        // collect results
        List<List<Integer>> results = new ArrayList<>();
        for (Integer query : queries) {
            for (int i = 1; i <= levels.size() / query; i++) {
                int q = (i * query) + 1;
                levels.put(q, levels
                        .get(q)
                        .stream()
                        .map(l -> Arrays.asList(l.get(1), l.get(0)))
                        .collect(Collectors.toList()));
            }
            results.add(results(levels));
        }

        return results;
    }

    private static List<Integer> results(Map<Integer, List<List<Integer>>> levels) {

        Map<Integer, List<Integer>> tree = new HashMap<>();

        int i = 1;
        for (List<List<Integer>> list : levels.values()) {
            for (List<Integer> pair : list) {
                tree.put(i++, pair);
            }
        }

        List<Integer> results = new ArrayList<>();

        Node root = node(tree, 1);

        traverse(results, root.left);
        results.add(1);
        traverse(results, root.right);

        return results;
    }

    private static Node node(Map<Integer, List<Integer>> oak, int data) {
        if (!oak.containsKey(data)) {
            return new Node(data, null, null);
        }
        return new Node(data, node(oak, oak.get(data).get(0)), node(oak, oak.get(data).get(1)));
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
