package com.nelsw;

import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
public class ProblemHackerRank {

    static List<List<Integer>> results = new ArrayList<>();

    public ProblemHackerRank() {

        var indexes = List.of(
                List.of(2, 3),
                List.of(-1, -1),
                List.of(-1, -1));

        indexes = List.of(
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

        log.info(result);
    }

    public static List<List<Integer>> swapNodes(List<List<Integer>> indexes, List<Integer> queries) {

        Map<Integer, List<Integer>> tree = new HashMap<>();
        for (int i = 0; i < indexes.size(); i++) {
            if (i == 0) {
                tree.put(1, indexes.get(i));
            } else {
                tree.put(i + 1, indexes.get(i));
                tree.put(i + 2, indexes.get(i));
            }
        }


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

        print(tree, levels);

        for (Integer query : queries) {

            var q     = query + 1;
            var lists = levels.get(q);

            for (List<Integer> l1 : lists) {
                var k1 = l1.get(0);
                if (k1 != -1) {
                    List<Integer> pair = tree.get(k1);
//                    tree.put(k1, List.of(pair.get(1), pair.get(0)));
                    tree.remove(k1);
                }
                var k2 = l1.get(1);
                if (k2 != -1) {
                    List<Integer> pair = tree.get(k2);
//                    tree.put(k2, List.of(pair.get(1), pair.get(0)));
                    tree.remove(k2);
                }
            }


//            var l = p.get(0);
//            var r = p.get(1);
//
//            if (l != -1) {
//                var lp = tree.get(l);
//                tree.put(l, List.of(lp.get(1), lp.get(0)));
//            }
//
//
//            if (r != -1) {
//                var rp = tree.get(r);
//                tree.put(r, List.of(rp.get(1), rp.get(0)));
//            }
//

            log.debug(tree);
        }

        return results;
    }


    static void print(Map<Integer, List<Integer>> tree, Map<Integer, List<List<Integer>>> levels) {
        tree.entrySet().forEach(log::debug);
        levels.entrySet().forEach(log::debug);
    }


}
