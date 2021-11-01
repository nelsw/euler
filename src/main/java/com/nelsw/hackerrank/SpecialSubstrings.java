package com.nelsw.hackerrank;

import java.util.Arrays;

/**
 * A string is said to be a special string if either of two conditions is met:
 * <p>
 * All of the characters are the same, e.g. aaa.
 * All characters except the middle one are the same, e.g. aadaa.
 * <p>
 * A special substring is any substring of a string which meets one of those criteria.
 * <p>
 * Given a string, determine how many special substrings can be formed from it.
 */
public class SpecialSubstrings {

    public static void main(String[] args) {
        case1();
        System.out.println();
        case2();
    }

    static void case1() {
        var x = "abcbaba";
        var y = x.length();
        var a = substrCount(y, x);
        var e = 10;

        System.out.println(a == e);
        System.out.println(a);
        System.out.println(e);
    }

    static void case2() {
        var x = "aaaa";
        var y = x.length();
        var a = substrCount(y, x);
        var e = 10;

        System.out.println(a == e);
        System.out.println(a);
        System.out.println(e);
    }

    static long substrCount(int n, String s) {

        long count = 0;

        if (isSpecial(s)) {
            count++;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j + i <= n; j++) {
                String r = s.substring(j, j + i);
                if (isSpecial(r)) {
                    count++;
                }
            }
        }

        return count;

    }

    static boolean isSpecial(String s) {
        boolean b = Arrays.stream(s.split("")).distinct().count() == 1;
        if (b) {
            return true;
        }
        int m = s.length() / 2 + s.length() % 2;
        return s.substring(0, m - 1).equals(s.substring(m));
    }

}
