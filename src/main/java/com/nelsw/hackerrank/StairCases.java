package com.nelsw.hackerrank;

import java.util.Objects;

public class StairCases {

    public static void main(String[] args) {
        case1();
        System.out.println();
        case2();
        System.out.println();
        case3();
    }

    private static void case1() {
        var n = 1;
        var a = stepPerms(n);
        var e = 1;
        var b = Objects.equals(a, e);
        System.out.println(b);
        if (!b) {
            System.out.println(a);
            System.out.println(e);
        }
    }

    private static void case2() {
        var n = 3;
        var a = stepPerms(n);
        var e = 4;
        var b = Objects.equals(a, e);
        System.out.println(b);
        if (!b) {
            System.out.println(a);
            System.out.println(e);
        }
    }

    private static void case3() {
        var n = 7;
        var a = stepPerms(n);
        var e = 44;
        var b = Objects.equals(a, e);
        System.out.println(b);
        if (!b) {
            System.out.println(a);
            System.out.println(e);
        }
    }

    public static int stepPerms(int n) {

        if (n < 1) {
            return 0;
        }

        if (n == 1) {
            return 1;
        }

        if (n == 2) {
            return 3;
        }

        if (n == 3) {
            return 4;
        }

        int sum = 0;
        sum += stepPerms(n - 1);
        return sum;
    }

}
