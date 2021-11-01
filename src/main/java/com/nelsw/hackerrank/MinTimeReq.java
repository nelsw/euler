package com.nelsw.hackerrank;

import lombok.extern.log4j.Log4j2;

import java.util.Map;
import java.util.TreeMap;

@Log4j2
public class MinTimeReq {

    public static void main(String[] args) {

        var  a = minTime(new long[]{2, 3}, 5);
        long e = 6;
        System.out.println(a >= e);
        System.out.println(a);
        System.out.println(e);

        a = minTime(new long[]{1, 3, 4}, 10);
        e = 7;
        System.out.println(a >= e);
        System.out.println(a);
        System.out.println(e);
    }

    static long minTime(long[] machines, long goal) {

        Map<Long, Long> m   = new TreeMap<>();
        long            min = Long.MAX_VALUE;
        long            max = Long.MIN_VALUE;

        for (long machine : machines) {
            min = Math.min(machine, min);
            max = Math.max(machine, max);
            m.put(machine, m.getOrDefault(machine, 0L) + 1);
        }

        return minTime(m, goal, min, max, machines.length);

    }

    static long minTime(Map<Long, Long> m, long goal, long min, long max, int machinesLength) {

        long maxDays = goal * min / machinesLength;
        long minDays = goal * max / machinesLength;

        long best = Long.MAX_VALUE;

        while (minDays-- != maxDays) {

            int result = 0;
            for (long machine : m.keySet()) {
                result += (minDays / machine) * m.get(machine);
            }

            if (result >= goal && result < best) {
                best = result;
            }
        }

        if (best == goal) {
            return best;
        }

        long midDays = (maxDays + minDays) / 2;
        long r       = minTime(m, goal, minDays, midDays, machinesLength);

        if (r >= goal) {
            return r;
        }
        return minTime(m, goal, midDays, maxDays, machinesLength);

    }

}
