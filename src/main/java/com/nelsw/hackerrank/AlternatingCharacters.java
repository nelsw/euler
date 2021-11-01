package com.nelsw.hackerrank;

import java.util.Objects;

/**
 * You are given a string containing characters A and B only.
 * <p>
 * Your task is to change it into a string such that there are no matching adjacent characters.
 * <p>
 * To do this, you are allowed to delete zero or more characters in the string.
 * <p>
 * Your task is to find the minimum number of required deletions.
 */
public class AlternatingCharacters {

    public static void main(String[] args) {

        var a = "AAAA";

        var act = alternatingCharacters(a);
        var exp = 3;

        System.out.println(Objects.equals(act, exp));
        System.out.println(act);
        System.out.println(exp);
    }

    public static int alternatingCharacters(String s) {

        String chunk     = null;
        int    deletions = 0;
        for (String value : s.split("")) {
            if (Objects.equals(value, chunk)) {
                ++deletions;
            }
            chunk = value;
        }

        return deletions;
    }

}
