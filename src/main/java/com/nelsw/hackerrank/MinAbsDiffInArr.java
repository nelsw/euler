package com.nelsw.hackerrank;

import lombok.extern.log4j.Log4j2;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Log4j2
public class MinAbsDiffInArr {

    public static void main(String[] args) {
        case1();
        System.out.println();
        case2();
        System.out.println();
        case3();
    }

    static void case1() {
        var a = minimumAbsoluteDifference(List.of(3, -7, 0));
        var e = 3;

        System.out.println(Objects.equals(a, e));
        System.out.println(a);
        System.out.println(e);
    }

    static void case2() {

        var a = minimumAbsoluteDifference(List.of(-59, -36, -13, 1, -53, -92, -2, -96, -54, 75));
        var e = 1;

        System.out.println(Objects.equals(a, e));
        System.out.println(a);
        System.out.println(e);
    }

    static void case3() {

        try {
            String[] chunks = Files.readAllLines(Path.of("arrs.txt")).get(0).split(" ");
            List<Integer> l = Stream.of(chunks)
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .collect(Collectors.toList());
            var a = minimumAbsoluteDifference(l);
            var e = 334;
            System.out.println(Objects.equals(a, e));
            System.out.println(a);
            System.out.println(e);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int minimumAbsoluteDifference(List<Integer> arr) {

//        List<Integer> l = arr
//                .stream()
//                .map(String::valueOf)
//                .map(s -> s.replace("-", ""))
//                .mapToInt(Integer::parseInt)
//                .boxed()
//                .sorted(Collections.reverseOrder())
//                .collect(Collectors.toList());
//
//        log.info(l);

        int min = Integer.MAX_VALUE;

        for (int i = 0; i < arr.size() - 1; i++) {
            int x = arr.get(i);
            int y = arr.get(i + 1);
            int z = Math.abs(x - y);
            log.debug("x {} y {} z {}", x, y, z);
            if (z < min) {
                min = z;
                if (min == 0) {
                    break;
                }
            }
        }


        return min;
    }

}
