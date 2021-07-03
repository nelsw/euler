package com.nelsw.hackerrank;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HourGlassTest {

    @Test
    public void test() {

        var arr = List.of(
                List.of(-9, -9, -9, 1, 1, 1),
                List.of(0, -9, 0, 4, 3, 2),
                List.of(-9, -9, -9, 1, 2, 3),
                List.of(0, 0, 8, 6, 6, 0),
                List.of(0, 0, 0, -2, 0, 0),
                List.of(0, 0, 1, 2, 4, 0));

        var expected = 28;

        var actual = HourGlass.hourglassSum(arr);

        assertEquals(expected, actual);

    }


}
