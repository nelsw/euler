package com.nelsw.hackerrank;

import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Log4j2
public class ShiftToLeft {

    public static void main(String[] args) {

        var p = List.of(1, 2, 3, 4, 5);
        var a = rotateLeft(2, p);
        var e = List.of(3, 4, 5, 1, 2);
        System.out.println(Objects.equals(a, e));
        log.debug(a);
        log.debug(e);
    }


    public static List<Integer> rotateLeft(int d, List<Integer> arr) {
        List<Integer> l3 = new ArrayList<>();
        l3.addAll(arr.subList(d, arr.size()));
        l3.addAll(arr.subList(0, d));
        return l3;

    }


}
