package com.nelsw.euler;

import org.apache.commons.math3.primes.Primes;

import java.util.Arrays;

/**
 * The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.
 * <p>
 * Find the sum of all the primes below two million.
 */
public class Problem10 {

    public Problem10() {
        long ex = 142913828922L;
        int  i  = 0, p = 0;
        long s  = 0;
        do {
            if (Primes.isPrime(++p)) {
                s += p;
            }
        } while (i++ < Math.pow(10, 6) * 2);
        System.out.println(s);
        System.out.println(s == ex);
    }

    public static void main(String[] args) {

        int       limit  = 2000000; // 2 Million
        long      sum    = 0;
        boolean[] primes = new boolean[limit];
        // Assign true to all array elements
        Arrays.fill(primes, true);
        generatePrimes(primes);
        for (int i = 2; i < limit; i++) {
            if (primes[i]) {
                sum = sum + i;
            }
        }
        System.out.println("The sum of all the primes below two million is : " + sum);
    }

    // Sieve of Eratosthenes - Algorithm for generating prime numbers.
    private static void generatePrimes(boolean[] primes) {

        int length = primes.length;
        int sqrt   = (int) Math.sqrt(length);
        for (int i = 2; i <= sqrt; i++) {
            for (int j = i * i; j < length; j = j + i) {
                primes[j] = false;
            }
        }

    }


}
