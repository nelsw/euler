package com.nelsw.euler;

import lombok.extern.log4j.Log4j2;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Euler's Totient function, φ(n) [sometimes called the phi function],
 * is used to determine the number of positive numbers less than or
 * equal to n which are relatively prime to n.
 * <p>
 * For example, as 1, 2, 4, 5, 7, and 8, are all less than nine and relatively prime to nine, φ(9)=6.
 * <p>
 * The number 1 is considered to be relatively prime to every positive number, so φ(1)=1.
 * <p>
 * Interestingly, φ(87109)=79180, and it can be seen that 87109 is a permutation of 79180.
 * <p>
 * Find the value of n, 1 < n < 10^7, for which φ(n) is a permutation of n and the ratio n/φ(n) produces a minimum.
 */
@Log4j2
public class Problem70 {


    static List<Integer> nums = IntStream
            .range(2, (int) Math.pow(10, 7))
            .parallel()
            .boxed()
            .collect(Collectors.toList());

    public Problem70() {


//        bar(9, 6);

        int f = 87109;
        int r = 79180;

//        bar(f, r);

        foo(f);


        List<Integer> results = IntStream
                .range(2, (int) Math.pow(10, 7))
                .parallel()
                .boxed()
                .map(Problem70::foo)
                .filter(i -> i > 0)
                .collect(Collectors.toList());
        log.debug(results);

//        foo(9_999_999);

        var then = Instant.now();

        var actual   = solve(9_999_999);
        var expected = 8_319_823;

        var x = String.valueOf(expected);


        List<Integer> l1 = new ArrayList<>();
        for (int i = 0; i < x.length(); i++) {
            l1.add(Integer.valueOf(x.substring(i, i + 1)));
        }


        if (actual == expected) {
            log.info("✅ - {} - {}", Duration.between(then, Instant.now()), actual);
        } else {
            log.warn("❌ - expected=[{}] actual=[{}]", expected, actual);
        }
    }

    private static int foo(int n) {

        List<Integer> mods = IntStream
                .range(2, n)
                .parallel()
                .boxed()
                .filter(i -> i != n)
                .filter(i -> n % i == 0)
                .collect(Collectors.toList());

        int result = n - mods
                .stream()
                .mapToInt(Integer::intValue)
                .sum() + 1;

        String x = String.valueOf(n), y = String.valueOf(result);
        if (result > 0 && isPermutation(x, y)) {
            log.warn("x=[{}] y=[{}]", x, y);
            return result;
        }

        return 0;
    }


    private static void bar(int f, int r) {

        log.debug("f=[{}]", f);

        List<Integer> nums = IntStream
                .range(2, f)
                .parallel()
                .boxed()
                .collect(Collectors.toList());

        List<Integer> mods = nums
                .stream()
                .parallel()
                .filter(i -> f % i == 0)
                .collect(Collectors.toList());

        mods.forEach(log::warn);

        int sum = mods
                .stream()
                .mapToInt(Integer::intValue)
                .sum() - mods.size() + 1;

        System.out.println(sum);

        var result = nums
                .stream()
                .parallel()
                .filter(i -> !isMultiple(mods, i))
                .count() + 1;

        if (result < 10 || isPermutation(String.valueOf(f), String.valueOf(result))) {
            System.out.println("winning");
            System.out.println(result);
            return;
        }

        bar(f - 1, r);
    }

    private static boolean isMultiple(List<Integer> mods, int x) {
        return !mods.isEmpty() && mods.stream().anyMatch(i -> Math.max(i, x) % Math.min(i, x) == 0);
    }


    private static int solve(int n) {

        int p = 0;
        for (int i = n - 1; i > 0; i--) {

            if (n % i == 0) {
                ++p;
            }

            if (isPermutable(p)) {
                break;
            }
        }

        return p;
    }

    private static boolean isPermutable(int n) {

        int primes = 0;

        double d = Math.sqrt(n);

        boolean skipSqrt = !String.valueOf(d).contains(".0");

        for (int i = 2; i <= n; i++) {

            if (n % i == 0) {
                continue;
            }

            if (!skipSqrt && i % d == 0) {
                continue;
            }

            String x = String.valueOf(i);

            if (x.length() == 1) {
                // must be permutable
                ++primes;
                continue;
            }

            double e = Math.sqrt(i);
            if (d % e == 0) {
                continue;
            }

            ++primes;


            x = String.valueOf(n);
            String y = String.valueOf(primes);

            if (x.length() == y.length() && isPermutation(x, y)) {

                return true;
            }
        }
        return false;
    }


    private static boolean isPermutation(String x, String y) {

        if (x.length() != y.length()) {
            return false;
        }

        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();
        for (int i = 0; i < x.length(); i++) {

            l1.add(Integer.valueOf(x.substring(i, i + 1)));
            l2.add(Integer.valueOf(y.substring(i, i + 1)));

        }


        Collections.sort(l1);
        Collections.sort(l2);

        return l1.equals(l2);
    }


}
