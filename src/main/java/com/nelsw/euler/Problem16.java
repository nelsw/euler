package com.nelsw.euler;

import java.math.BigInteger;

/**
 * 2^15 = 32768 and the sum of its digits is 3 + 2 + 7 + 6 + 8 = 26.
 * <p>
 * What is the sum of the digits of the number 2^1000?
 */
public class Problem16 {

    int exp = 1000;

    public Problem16() {
        var actual = solve();
        System.out.println(actual);
    }

    private BigInteger solve() {

        var x = BigInteger.TWO.pow(exp).toString();

        var y = BigInteger.ZERO;
        for (int i = 0; i < x.length(); i++) {
            y = y.add(BigInteger.valueOf(Long.parseLong(x.substring(i, i + 1))));
        }


        System.out.println(y);

        return y;
    }


}
