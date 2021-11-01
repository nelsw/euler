package com.nelsw.euler;

import java.math.BigInteger;

/**
 * The Fibonacci sequence is defined by the recurrence relation:
 * <p>
 * Fn = Fn−1 + Fn−2, where F1 = 1 and F2 = 1.
 * <p>
 * Hence the first 12 terms will be:
 * <p>
 * F1 = 1
 * F2 = 1
 * F3 = 2
 * F4 = 3
 * F5 = 5
 * F6 = 8
 * F7 = 13
 * F8 = 21
 * F9 = 34
 * F10 = 55
 * F11 = 89
 * F12 = 144
 * <p>
 * The 12th term, F12, is the first term to contain three digits.
 * <p>
 * What is the index of the first term in the Fibonacci sequence to contain 1000 digits?
 */
public class Problem25 {

    public static void main(String[] args) {
        new Problem25();
    }

    public Problem25() {
        int  expected = 4782;
        long start    = System.currentTimeMillis();
        int  actual   = solve();
        long finish   = System.currentTimeMillis();
        System.out.println(finish - start);
        System.out.println(expected == actual);
    }

    private int solve() {

        // For this problem to complete in a timely manner,
        // we MUST use immutable objects to leave the smallest
        // memory footprint and leverage the performance of
        // encapsulated functions for performing semantic
        // arithmetic operations over arbitrary precision
        // numbers. Thus, we'll use BigInteger, a Java 1.1 OG.
        BigInteger
                a = BigInteger.ONE, // Fibonacci term 1 at index 0
                b = BigInteger.ONE, // Fibonacci term 2 at index 1
                c = BigInteger.TWO; // Fibonacci term 3 at index 2

        int i = 2; // Fibonacci term index counter

        // while the String value length of this
        // number is less than 1,000 digits ...
        while (c.toString().length() < 1000) {
            c = a.add(b); // move forward in the sequence by one position
            a = b;
            b = c;
            i++; // and increment the index counter by one
        }

        // if we're here, we've exited our while loop
        // and found the first integer in the Fibonacci
        // sequence to contain 1,000 digits
        return i;
    }

    private int solve2() {
        BigInteger a = BigInteger.ONE, b = BigInteger.ONE, c = BigInteger.TWO;
        int        i = 2;
        while (c.toString().length() < 1000) {
            c = a.add(b);
            a = b;
            b = c;
            i++;
        }
        return i;
    }
}
