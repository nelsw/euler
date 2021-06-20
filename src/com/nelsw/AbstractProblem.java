package com.nelsw;

import java.text.DecimalFormat;
import java.util.Objects;

public abstract class AbstractProblem {

    private static final DecimalFormat df = new DecimalFormat("###,###,###");

    private static final String
            success = "✅",
            error   = "❌",
            fmt     = "Problem %d %s ... expected=[%s] ... actual=[%s]";

    abstract int number();

    abstract Object expected();

    abstract Object actual();

    String message() {
        if (expected() instanceof Number && actual() instanceof Number) {
            return message(df.format(expected()), df.format(actual()));
        }
        return String.format(fmt, number(), symbol(), expected(), actual());
    }

    private String symbol() {
        return Objects.equals(expected(), actual()) ? success : error;
    }

    private String message(String expected, String actual) {
        return String.format(fmt, number(), symbol(), expected, actual);
    }

}
