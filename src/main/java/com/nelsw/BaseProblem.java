package com.nelsw;

import java.text.DecimalFormat;
import java.util.Objects;

public interface BaseProblem {

    int number();

    Object expected();

    Object actual();

    default String message() {

        Object e = expected(),
                a = actual();

        String s = Objects.equals(e, a) ? "✅" : "❌";

        if (e instanceof Number && a instanceof Number) {
            DecimalFormat df = new DecimalFormat("###,###,###");
            e = df.format(e);
            a = df.format(a);
        }

        return String.format("Problem %d %s ... expected=[%s] ... actual=[%s]", number(), s, e, a);
    }

    default boolean isNotPrime(String k) {
        return !isPrime(Integer.parseInt(k));
    }

    default boolean isPrime(String k) {
        return isPrime(Integer.parseInt(k));
    }

    default boolean isPrime(int n) {

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

}
