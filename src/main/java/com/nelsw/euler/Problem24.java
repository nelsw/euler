package com.nelsw.euler;

import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
@Log4j2
public class Problem24 {

    static List<String> results = new ArrayList<>();

    public static void main(String[] args) {
        case1();
        System.out.println();
        case2();
    }

    private static void case1() {

        var source   = "012";
        var expected = "210";

        long start  = System.currentTimeMillis();
        var  actual = solve(source, 5);
        long finish = System.currentTimeMillis();

        System.out.println(Objects.equals(actual, expected));
        System.out.println(finish - start);
    }

    private static void case2() {
        var source   = "123456789";
        var expected = "?";

        long start  = System.currentTimeMillis();
        var  actual = solve(source, 999_999);
        long finish = System.currentTimeMillis();
        System.out.println(results.size());
        System.out.println(actual);
        System.out.println(Objects.equals(actual, expected));
        System.out.println(finish - start);
    }

    private static String solve(String s, int n) {

        perm(s.split(""), 0);
//        Collections.sort(results);
        return results.get(n);
    }

    private static void perm(String[] chunks, int index) {
        if (index == chunks.length - 1) {
            results.add(String.join("", chunks));
        }
        for (int i = index; i < chunks.length; i++) {
            swap(chunks, index, i);
            perm(chunks, index + 1);
            swap(chunks, index, i);
        }
    }

    private static void swap(String[] chunks, int i, int j) {
        String a = chunks[i];
        chunks[i] = chunks[j];
        chunks[j] = a;
    }


}
