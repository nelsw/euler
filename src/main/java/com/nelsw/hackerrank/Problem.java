package com.nelsw.hackerrank;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class Problem {

    public static void main(String[] args) {
        long alpha  = System.currentTimeMillis();
        int  actual = sherlockAndAnagrams("ifailuhkqqhucpoltgtyovarjsnrbfpvmupwjjjfiwwhrlkpekxxnebfrwibylcvkfealgonjkzwlyfhhkefuvgndgdnbelgruel");
        long omega  = System.currentTimeMillis();
        System.out.println(omega - alpha);
        int expect = 399;
        System.out.println(actual == expect);
        if (actual != expect) {
            System.out.println(actual);
            System.out.println(expect);
        }
    }

    public static int sherlockAndAnagrams(String s) {

        int result = 0;
        int len    = s.length();

        for (int i = 1; i < len; i++) {

            for (int j = 0; j + i <= len; j++) {

                for (int k = j + 1; k + i <= len; k++) {

                    if (isAnagram(s, i, j, k)) {
                        ++result;
                    }
                }
            }
        }

        return result;
    }

    private static boolean isAnagram(String s, int h, int j, int k) {

        char[] x = new char[26];
        char[] y = new char[26];

        for (int i = 0; i < h; i++) {
            x[s.substring(j, j + h).charAt(i) - 97] += 1;
            y[s.substring(k, k + h).charAt(i) - 97] += 1;
        }

        for (int i = 0; i < 26; i++) {
            if (x[i] != y[i]) {
                return false;
            }
        }

        return true;
    }

}
