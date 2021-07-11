package com.nelsw.euler;

/**
 * A permutation is an ordered arrangement of objects.
 * <p>
 * For example, 3124 is one possible permutation of the digits 1, 2, 3 and 4.
 * <p>
 * If all of the permutations are listed numerically or alphabetically, we call it lexicographic order.
 * <p>
 * The lexicographic permutations of 0, 1 and 2 are:
 * <p>
 * 012   021   102   120   201   210
 * <p>
 * What is the millionth lexicographic permutation of the digits 0, 1, 2, 3, 4, 5, 6, 7, 8 and 9?
 */
public class Problem24 {

    String digits = "012";
    int    target = 5;

    public Problem24() {
        long start  = System.currentTimeMillis();
        int  actual = solve();
        long finish = System.currentTimeMillis();
        System.out.println(finish - start);
        System.out.println(actual);
        System.out.println(actual == target);

    }

    private int solve() {

        int    p = 1;
        String d = digits;

        while (p < 5) {


            ++p;
        }

        return 0;
    }

}
