package com.nelsw.hackerrank;

import lombok.extern.log4j.Log4j2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class Toys {


    public static void main(String[] args) {
        case1();
        case2();
    }

    static void case1() {
        var p = List.of(1, 12, 5, 111, 200, 1000, 10);
        var k = 50;
        var e = 4;

        long alpha = System.currentTimeMillis();
        var  a     = maximumToys(p, k);
        long omega = System.currentTimeMillis();

        System.out.println(omega - alpha + "ms");
        if (a != e) {
            System.out.println(a);
            System.out.println(e);
        }
        System.out.println();
    }

    static void case2() {
        var p = List.of(3, 7, 2, 9, 4);
        var k = 15;
        var e = 3;

        long alpha = System.currentTimeMillis();
        var  a     = maximumToys(p, k);
        long omega = System.currentTimeMillis();

        System.out.println(omega - alpha + "ms");
        if (a != e) {
            System.out.println(a);
            System.out.println(e);
        }
        System.out.println();
    }

    public static int maximumToys(List<Integer> prices, int k) {

        prices = prices.stream().sorted().collect(Collectors.toList());

        System.out.println(Arrays.toString(prices.toArray()));

        int qty = 0;
        int sum = 0;

        int index = 0;
        while (sum < k) {
            int a = prices.get(index++);
            sum += a;
            if (sum < k) {
                ++qty;
            }
        }

        return qty;
    }


}
