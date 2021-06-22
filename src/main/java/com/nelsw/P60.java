package com.nelsw;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.log4j.Log4j2;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Problem 60 - Prime pair sets
 * <p>
 * The primes 3, 7, 109, and 673, are quite remarkable.
 * <p>
 * By taking any two primes and concatenating them in any order the result will always be prime.
 * <p>
 * For example, taking 7 and 109, both 7109 and 1097 are prime.
 * <p>
 * The sum of these four primes, 792, represents the lowest sum for a set of four primes with this property.
 * <p>
 * Find the lowest sum for a set of five primes for which any two primes concatenate to produce another prime.
 */
@Log4j2
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class P60 extends AbstractProblem {

    static Map<Integer, List<Integer>> pairs = new TreeMap<>();
    @NonFinal
    int result = Integer.MAX_VALUE;

    private static List<Integer> pairs(int prime) {

        List<Integer> result = new ArrayList<>();

        String p = String.valueOf(prime), k;

        for (Map.Entry<Integer, List<Integer>> entry : pairs.entrySet()) {

            if (entry.getKey() <= prime) {
                continue;
            }

            k = String.valueOf(entry.getKey());
            if (isPrime(p, k) && isPrime(k, p)) {
                result.add(entry.getKey());
            }
        }

        return result;
    }

    private static boolean isPrime(String a, String z) {

        int n = Integer.parseInt(a + z);

        if (n < 2) {
            return false;
        }

        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int number() {
        return 60;
    }

    @Override
    public Object expected() {
        return 26033;
//        return 792;
    }

    @Override
    public Object actual() {

        long alpha = System.currentTimeMillis();

        List<Integer> primes = IntStream
                .rangeClosed(0, (int) Math.pow(10, 4))
                .boxed()
                .filter(this::isPrime)
                .collect(Collectors.toList());

        for (Integer integer : primes) {
            pairs.put(integer, new ArrayList<>());
        }

        for (Integer prime : primes) {

            if (result != Integer.MAX_VALUE && prime > Math.sqrt(result)) {
                break;
            }

            int sum = work(prime);
            if (sum != 0 && sum < result) {
                log.info("result [{}] [{}]", Duration.ofMillis(System.currentTimeMillis() - alpha), sum);
                result = sum;
            }
        }

        long omega = System.currentTimeMillis();

        log.info("duration [{}]", Duration.ofMillis(System.currentTimeMillis() - alpha));

        return result;
    }

    private int work(int... primes) {

        int len = primes.length;
        if (len == 5) {
            return Arrays.stream(primes).sum();
        }

        int lastPrime = primes[len - 1], sum;

        long alpha = System.currentTimeMillis();

        for (Integer pair : pairs(lastPrime, Arrays.stream(primes).boxed().collect(Collectors.toList()))) {

            if (len == 4) {
                sum = work(primes[0], primes[1], primes[2], lastPrime, pair);
                long omega = System.currentTimeMillis();
                log.info("sum [{}] {}", Duration.ofMillis(omega - alpha), Arrays.toString(primes));
            } else if (len == 3) {
                sum = work(primes[0], primes[1], lastPrime, pair);
            } else if (len == 2) {
                sum = work(primes[0], lastPrime, pair);
            } else {
                sum = work(lastPrime, pair);
            }

            if (sum == 0) {
                continue;
            }


            return sum;
        }
        return 0;
    }

    private List<Integer> pairs(int prime, List<Integer> results) {
        return pairs(prime)
                .stream()
                .filter(nextPrime -> nextPrime < result)
                .filter(nextPrime -> arePrime(results, nextPrime))
                .collect(Collectors.toList());
    }

    private boolean arePrime(List<Integer> primes, int prime) {
        List<Integer> p = new ArrayList<>(primes);
        p.add(prime);
        return arePrime(p);
    }

    private boolean arePrime(List<Integer> primes) {

        int len = primes.size();
        if (len < 2) {
            return isPrime(primes.get(0));
        }

        String a, z;
        for (int j = 0; j < len; j++) {

            a = String.valueOf(primes.get(j));

            for (int k = 0; k < len; k++) {

                if (j == k) {
                    continue;
                }

                z = String.valueOf(primes.get(k));
                if (isNotPrime(a + z) || isNotPrime(z + a)) {
                    return false;
                }
            }
        }

        return true;
    }

}
