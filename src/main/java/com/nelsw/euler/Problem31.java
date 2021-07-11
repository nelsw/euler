package com.nelsw.euler;

import lombok.extern.log4j.Log4j2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * In the United Kingdom the currency is made up of pound (£) and pence (p). There are eight coins in general circulation:
 * <p>
 * 1p, 2p, 5p, 10p, 20p, 50p, £1 (100p), and £2 (200p).
 * <p>
 * It is possible to make £2 in the following way:
 * <p>
 * 1×£1 + 1×50p + 2×20p + 1×5p + 1×2p + 3×1p
 * <p>
 * How many different ways can £2 be made using any number of coins?
 */
@Log4j2
public class Problem31 {


    public Problem31() {

        Map<Integer, Integer> m = new HashMap<>();

        for (int coin : List.of(1, 2, 5, 10, 20, 50, 100, 200)) {

            for (int j = coin; j <= 200; j++) {

                int c = m.getOrDefault(j, 0);
                int d = m.getOrDefault(j - coin, 1);

                m.put(j, c + d);

            }
        }

        System.out.println(m.get(200));
    }


}
