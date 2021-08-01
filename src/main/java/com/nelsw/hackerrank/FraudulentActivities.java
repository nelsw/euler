package com.nelsw.hackerrank;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FraudulentActivities {

    public static void main(String[] args) throws Exception {
//        case1();
//        case2();
        case3();
    }

    static void case1() {
        var expenditure = List.of(2, 3, 4, 2, 3, 6, 8, 4, 5);
        var d           = 5;
        var e           = 2;

        long alpha = System.currentTimeMillis();
        var  a     = activityNotifications(expenditure, d);
        long omega = System.currentTimeMillis();

        System.out.println(omega - alpha + "ms");
        if (a != e) {
            System.out.println(a);
            System.out.println(e);
        }
        System.out.println();
    }

    static void case2() {
        var expenditure = List.of(1, 2, 3, 4, 4);
        var d           = 4;
        var e           = 0;

        long alpha = System.currentTimeMillis();
        var  a     = activityNotifications(expenditure, d);
        long omega = System.currentTimeMillis();

        System.out.println(omega - alpha + "ms");
        if (a != e) {
            System.out.println(a);
            System.out.println(e);
        }
        System.out.println();
    }

    static void case3() throws Exception {
        String line = Files.readAllLines(Path.of("queries.txt")).get(0);
        List<Integer> expenditure = Stream.of(line.split(" "))
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());
        var  d     = 10000;
        var  e     = 633;
        long alpha = System.currentTimeMillis();
        var  a     = activityNotifications(expenditure, d);
        long omega = System.currentTimeMillis();

        System.out.println(omega - alpha + "ms");
        if (a != e) {
            System.out.println(a);
            System.out.println(e);
        }
        System.out.println();
    }

    public static int activityNotifications(List<Integer> expenditure, int d) {

        int len = expenditure.size();

        int[] countData = new int[201];

        for (int i = 0; i < d; i++) {
            countData[expenditure.get(i)]++;
        }

        int answer = 0;

        for (int i = d; i < len; i++) {
            int    today  = expenditure.get(i);
            double preAvg = getAverage(countData, d);

            if ((double) today >= preAvg * (double) 2) {
                answer++;
            }

            countData[today]++;
            countData[expenditure.get(i - d)]--;
        }
        return answer;
    }

    static double getAverage(int[] countData, int d) {
        if (d % 2 == 0) {

            int targetNTh1 = d / 2;
            int cnt        = 0;

            for (int i = 0; i < countData.length; i++) {

                cnt += countData[i];
                if (cnt > targetNTh1) {
                    return i;
                }

                if (cnt == targetNTh1) {
                    for (int j = i + 1; j < countData.length; j++) {
                        if (countData[j] > 0) {
                            return (double) (i + j) / (double) 2;
                        }
                    }
                }
            }
        } else {
            int targetNTh = d / 2 + 1;

            int cnt = 0;
            for (int i = 0; i < countData.length; i++) {
                cnt += countData[i];
                if (cnt >= targetNTh) {
                    return i;
                }
            }
        }

        return -1;
    }

}
