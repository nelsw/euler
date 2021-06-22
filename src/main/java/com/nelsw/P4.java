package com.nelsw;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

/**
 * Problem 4 - Largest palindrome product
 * <p>
 * A palindromic number reads the same both ways.
 * <p>
 * The largest palindrome made from the product of two 2-digit numbers is 9009 = 91 × 99.
 * <p>
 * Find the largest palindrome made from the product of two 3-digit numbers.
 */
public class P4 extends AbstractProblem {

    @Override
    public int number() {
        return 4;
    }

    @Override
    public Object expected() {
        return 906_609;
    }

    @Override
    public Object actual() {
        return solution(3);
    }

    private Object solution(int k) {
        Set<Integer> results = new HashSet<>();
        int          n       = Integer.parseInt("9".repeat(Math.max(0, k))), p;
        for (int i = n; i > 0; i--) {
            for (int j = n; j > 0; j--) {
                p = i * j;
                if (isPalindrome(p)) {
                    results.add(p);
                }
            }
        }
        return results
                .stream()
                .sorted(Comparator.reverseOrder())
                .limit(1)
                .findFirst()
                .orElseThrow();
    }

    private boolean isPalindrome(int i) {

        String s = String.valueOf(i);

        int len = s.length();
        if (len % 2 != 0) {
            return false;
        }

        int hlf = len / 2;
        String alpha = s.substring(0, hlf),
                omega = new StringBuilder(s.substring(hlf)).reverse().toString();

        return alpha.equals(omega);
    }

}
