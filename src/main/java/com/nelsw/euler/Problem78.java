package com.nelsw.euler;

import lombok.extern.log4j.Log4j2;

import java.util.*;

/**
 * Let p(n) represent the number of different ways in which n coins can be separated into piles.
 * <p>
 * For example, five coins can be separated into piles in exactly seven different ways, so p(5)=7.
 * <p>
 * Find the least value of n for which p(n) is divisible by one million.
 */
@Log4j2
public class Problem78 {

    static Map<Integer, Set<List<Integer>>> combos = new HashMap<>();
    int coins    = 500;
    int expected = 55374;

    public Problem78() {

        for (int i = 1; i <= coins; i++) {
            System.out.println(getPentagonalNumber(i));
        }
//        combos.entrySet().forEach(log::debug);

//        System.out.println(combos.get(coins).size());
    }

    public static int getPentagonalNumber(int i) {
        return (i * (3 * i - 1)) / 2;
    }

    private static void updateCombos(int n) {

        var r = new HashSet<List<Integer>>();
        r.add(List.of(n));

        for (int i = 1; i < n / 2; i++) {
            var t1 = new ArrayList<>(List.of(n - i, i));
            t1.sort((o1, o2) -> o1.compareTo(o2));
            t1.su
            Collections.sort(t1);
            r.add(t1);
        }

        updateCombos(n, r);

        if (n % 1000 == 0) {
            log.info("{}={}", n, r);
        }

        combos.put(n, r);

    }

    private static void updateCombos(int n, Set<List<Integer>> r) {

        int p = n / 2, q = n - p;
        if (p < 1) {
            return;
        }

        updateCombos(p, q, r);
        updateCombos(q, p, r);
        updateCombos(p, r);
    }

    private static void updateCombos(int p, int q, Set<List<Integer>> r) {
        for (List<Integer> list : combos.getOrDefault(p, Collections.emptySet())) {
            if (list.stream().allMatch(i -> i == 1) && q == 1) {
                continue;
            }
            var t1 = new ArrayList<>(list);
            t1.add(q);
            Collections.sort(t1);
            r.add(t1);
        }
    }

}
