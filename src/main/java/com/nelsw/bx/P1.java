package com.nelsw.bx;

import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Log4j2
public class P1 {

    public static void main(String[] args) {

        var a = rodOffcut(List.of(5, 4, 4, 2, 2, 8));
        var e = List.of(6, 4, 2, 1);
        System.out.println(Objects.equals(a, e));
        log.debug(a);
        log.debug(e);
    }

    public static List<Integer> rodOffcut(List<Integer> lengths) {

        List<Integer> rods = lengths
                .stream()
                .sorted()
                .collect(Collectors.toList());

        List<Integer> result = new ArrayList<>();

        while (!rods.isEmpty()) {

            result.add(rods.size());

            int rod = rods.remove(0);

            rods = rods
                    .stream()
                    .filter(i -> i != rod)
                    .map(i -> i - rod)
                    .collect(Collectors.toList());
        }

        return result;
    }

}
