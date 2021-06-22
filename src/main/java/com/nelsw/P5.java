package com.nelsw;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Problem 5 - Smallest multiple
 * <p>
 * 2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.
 * </p>
 * What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?
 */
public class P5 extends AbstractProblem {

    @Override
    public int number() {
        return 5;
    }

    @Override
    public Object expected() {
        return 232_792_560;
    }

    @Override
    public Object actual() {
        int alpha = 1, omega = 20, lcm = 0;
        for (int i = omega; i >= alpha; i--) {
            if (lcm > omega) {
                lcm = lcm(i, lcm);
            } else {
                lcm = lcm(i, omega);
            }
        }
        return lcm;
    }

    int lcm(int thisNum, int thatNum) {

        Map<Integer, Integer> primeFactorsForNum1 = primeFactors(thisNum);
        Map<Integer, Integer> primeFactorsForNum2 = primeFactors(thatNum);

        Set<Integer> primeFactorsUnionSet = new HashSet<>();
        primeFactorsUnionSet.addAll(primeFactorsForNum1.keySet());
        primeFactorsUnionSet.addAll(primeFactorsForNum2.keySet());

        int lcm = 1;

        for (Integer primeFactor : primeFactorsUnionSet) {
            lcm *= Math.pow(primeFactor,
                    Math.max(primeFactorsForNum1.getOrDefault(primeFactor, 0),
                            primeFactorsForNum2.getOrDefault(primeFactor, 0)));
        }

        return lcm;
    }

    private Map<Integer, Integer> primeFactors(int number) {

        int                   absNumber       = Math.abs(number);
        Map<Integer, Integer> primeFactorsMap = new HashMap<>();

        for (int factor = 2; factor <= absNumber; factor++) {

            while (absNumber % factor == 0) {
                primeFactorsMap.put(factor, primeFactorsMap.getOrDefault(factor, 0) + 1);
                absNumber /= factor;
            }
        }

        return primeFactorsMap;
    }

}
