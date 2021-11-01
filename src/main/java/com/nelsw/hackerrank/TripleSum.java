package com.nelsw.hackerrank;

import java.util.ArrayList;
import java.util.List;

public class TripleSum {

    public static void main(String[] args) {
        case1();
    }

    private static void case1() {
        int[] a    = new int[]{1, 3, 5};
        int[] b    = new int[]{2, 3};
        int[] c    = new int[]{1, 2, 3};
        var   act  = triplets(a, b, c);
        var   exp  = 8L;
        var   bool = act == exp;
        System.out.println(bool);
        if (!bool) {
            System.out.println(act);
            System.out.println(exp);
        }

    }


    static long triplets(int[] a, int[] b, int[] c) {

        int           max = Integer.MIN_VALUE;
        List<Integer> bb  = new ArrayList<>();
        for (int i : b) {
            if (i > max) {
                max = i;
            }
            bb.add(i);
        }

        List<Integer> aa = new ArrayList<>();
        for (int i : a) {
            if (i <= max) {
                aa.add(i);
            }
        }


        List<Integer> cc = new ArrayList<>();
        for (int i : c) {
            if (i <= max) {
                cc.add(i);
            }
        }

        int as = aa.size();
        int bs = bb.size();
        int cs = cc.size();

        return ((long) as * bs) + ((long) bs * cs);
    }
}
