package com.nelsw.hackerrank;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Log4j2
public class SwapNodes {

    static Predicate<Map.Entry<Integer, List<List<Integer>>>>
            isNotEmpty     = e -> !e.getValue().isEmpty(),
            isNotAllLeaves = e -> !e.getValue().stream().allMatch(l -> l.equals(Arrays.asList(-1, -1)));
    static Collector<Map.Entry<Integer, List<List<Integer>>>, ?, Map<Integer, List<List<Integer>>>> depthMap =
            Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue);
    static Function<List<Integer>, List<Integer>>                                                   toSwap   = l -> Arrays.asList(l.get(1), l.get(0));

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
    }


    public SwapNodes() {

        var indexes = List.of(
                List.of(2, 3),
                List.of(4, 5),
                List.of(6, -1),
                List.of(-1, 7),
                List.of(8, 9),
                List.of(10, 11),
                List.of(12, 13),
                List.of(-1, 14),
                List.of(-1, -1),
                List.of(15, -1),
                List.of(16, 17),
                List.of(-1, -1),
                List.of(-1, -1),
                List.of(-1, -1),
                List.of(-1, -1),
                List.of(-1, -1),
                List.of(-1, -1));

        var queries = List.of(2, 3);

        var actual = swapNodes(indexes, queries);

        var expected = List.of(
                List.of(14, 8, 5, 9, 2, 4, 13, 7, 12, 1, 3, 10, 15, 6, 17, 11, 16),
                List.of(9, 5, 14, 8, 2, 13, 7, 12, 4, 1, 3, 17, 11, 16, 6, 10, 15)
        );

        if (!actual.equals(expected)) {
            throw new Error();
        }
    }

    public static List<List<Integer>> swapNodes(List<List<Integer>> indexes, List<Integer> queries) {

        // create a map of the given indexes
        Map<Integer, List<Integer>> index = IntStream
                .range(0, indexes.size())
                .boxed()
                .collect(Collectors.toMap(i -> i + 1, indexes::get));

        // create a map of depths from our index map
        Map<Integer, List<List<Integer>>> depth = new HashMap<>();

        // root the map with initial indexes
        depth.put(2, Collections.singletonList(index.get(1)));

        // for depth after, put a list of indexes from previous indices
        for (int i = 2; i < index.size(); i++) {
            depth.put(i + 1, depth
                    .get(i)
                    .stream()
                    .map(toBranch(index))
                    .flatMap(Collection::stream)
                    .collect(Collectors.toList()));
        }

        // filter out empty lists and the final row of all leaves
        depth = depth
                .entrySet()
                .stream()
                .filter(isNotEmpty.and(isNotAllLeaves))
                .collect(SwapNodes.depthMap);

        // collect results of each multiple of each query
        List<List<Integer>> results = new ArrayList<>();

        // for each given query
        for (Integer query : queries) {

            // for each multiple of dividing depth size by query value
            for (int i = 1; i <= depth.size() / query; i++) {

                // children of the node are swapped, so depth + 1
                int q = (i * query) + 1;
                depth.put(q, depth.get(q).stream().map(toSwap).collect(Collectors.toList()));
            }

            // reconstitute a swapped index map
            var swapped = new HashMap<Integer, List<Integer>>();
            int i       = 1;
            for (List<List<Integer>> list : depth.values()) {
                for (List<Integer> pair : list) {
                    swapped.put(i++, pair);
                }
            }

            // build a tree from the index map
            Node root = node(swapped, 1);

            // traverse the result
            List<Integer> result = new ArrayList<>();
            traverse(result, root.left);
            result.add(1);
            traverse(result, root.right);

            // add the result to the final results
            results.add(result);
        }

        depth.entrySet().forEach(log::debug);

        return results;
    }

    private static Function<List<Integer>, List<List<Integer>>> toBranch(Map<Integer, List<Integer>> index) {
        return pair -> pair
                .stream()
                .filter(k -> k > 0)
                .map(index::get)
                .collect(Collectors.toList());
    }

    @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
    @RequiredArgsConstructor
    private static class Node {
        int  data;
        Node left, right;
    }
}
