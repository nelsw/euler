package com.nelsw;

import java.util.Objects;

public abstract class AbstractProblem {

    private static final String
            success = "✅",
            error   = "❌",
            fmt     = "Problem %d %s ... expected=[%s] ... actual=[%s]";

    final int number;

    protected AbstractProblem(int number) {
        this.number = number;
    }

    abstract Object expected();

    abstract Object actual();

    String message() {
        return String.format(fmt, number, Objects.equals(expected(), actual()) ? success : error, expected(), actual());
    }

}
