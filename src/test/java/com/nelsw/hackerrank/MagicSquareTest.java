package com.nelsw.hackerrank;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MagicSquareTest {

    @Test
    public void easyTest() {

        var arr = List.of(
                List.of(4, 9, 2),
                List.of(3, 5, 7),
                List.of(8, 1, 5));

        var expected = 1;

        var actual = MagicSquare.formingMagicSquare(arr);

        assertEquals(expected, actual);
    }

    @Test
    public void hardTest() {

        var arr = List.of(
                List.of(4, 8, 2),
                List.of(4, 5, 7),
                List.of(6, 1, 6));

        var expected = 4;

        var actual = MagicSquare.formingMagicSquare(arr);

        assertEquals(expected, actual);
    }

}
