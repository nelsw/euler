package com.nelsw.hackerrank;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SparseArraysTest {

    @Test
    public void test() {


        var strings = List.of(
                "abcde",
                "sdaklfj",
                "asdjf",
                "na",
                "basdn",
                "sdaklfj",
                "asdjf",
                "na",
                "asdjf",
                "na",
                "basdn",
                "sdaklfj",
                "asdjf"
        );

        var queries = List.of(
                "abcde",
                "sdaklfj",
                "asdjf",
                "na",
                "basdn"
        );

        var expected = List.of(1, 3, 4, 3, 2);

        var actual = SparseArrays.matchingStrings(strings, queries);

        assertEquals(expected, actual);

    }


}
