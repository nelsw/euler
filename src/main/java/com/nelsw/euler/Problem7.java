package com.nelsw.euler;

import org.apache.commons.math3.primes.Primes;

/**
 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see that the 6th prime is 13.
 * <p>
 * What is the 10 001st prime number?
 */
public class Problem7 {

    public Problem7() {
        int i = 0, p = 0;
        do {
            if (Primes.isPrime(++p)) {
                i++;
            }
        } while (i < 10_001);
        System.out.println(p);
    }

}
