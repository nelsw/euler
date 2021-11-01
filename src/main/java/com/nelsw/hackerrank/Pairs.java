package com.nelsw.hackerrank;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Pairs {

    public static void main(String[] args) {

        var a = pairs(2, List.of(1, 5, 3, 4, 2));
        var e = 3;

        System.out.println(a == e);
        System.out.println(a);
        System.out.println(e);
    }


    public static int pairs(int k, List<Integer> arr) {

        Set<Integer> results = arr
                .stream()
                .mapToInt(integer -> integer + k)
                .boxed()
                .collect(Collectors.toSet());

        return (int) arr
                .stream()
                .filter(results::contains)
                .count();

    }


}
