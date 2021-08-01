package com.nelsw.hackerrank;

import lombok.extern.log4j.Log4j2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
public class CountTrips {

    public static void main(String[] args) {
        var  arr      = List.of(1L, 2L, 2L, 4L);
        var  r        = 2;
        long expected = 2;

        long alpha  = System.currentTimeMillis();
        long actual = countTriplets(arr, r);
        long omega  = System.currentTimeMillis();

        System.out.println(omega - alpha + "ms");
        System.out.println(actual == expected);
        if (actual != expected) {
            System.out.println(actual);
            System.out.println(expected);
        }
    }


    static long countTriplets(List<Long> arr, long r) {
        long            total   = 0;
        Map<Long, Long> count   = new HashMap<>(); // count of ints
        Map<Long, Long> tuplets = new HashMap<>(); // map 2nd -> count of links

        for (int i = 0; i < arr.size(); ++i) {

            long val = arr.get(i);

            long key = val / r;

            long mod = val % r;

            // inc total
            if (mod == 0 && tuplets.containsKey(key)) {
                total += tuplets.get(key);
            }

            // create links/tuplets
            if (tuplets.containsKey(val)) {
                tuplets.put(val, tuplets.get(val) + count.get(key));
            } else if (mod == 0 && count.containsKey(key)) {
                tuplets.put(val, count.get(key));
            }

            // inc count
            count.put(val, count.getOrDefault(val, 0L) + 1);
        }

        return total;
    }

}
