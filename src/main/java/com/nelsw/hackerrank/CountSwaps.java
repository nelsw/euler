package com.nelsw.hackerrank;

import lombok.extern.log4j.Log4j2;

import java.util.Arrays;
import java.util.List;

@Log4j2
public class CountSwaps {

    public static void main(String[] args) throws Exception {

        var queries = List.of(3, 2, 1);

        long alpha = System.currentTimeMillis();
        countSwaps(queries);
        long omega = System.currentTimeMillis();
        System.out.println(omega - alpha + "ms");
    }

    public static void countSwaps(List<Integer> a) {
        int   n   = a.size();
        int[] arr = a.stream().mapToInt(i -> i).toArray();
        int   s   = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1; j++) {
                while (arr[j] > arr[j + 1]) {
                    log.debug(Arrays.toString(arr));
                    swap(arr, j, j + 1);
                    log.debug(Arrays.toString(arr));
                    ++s;
                }
            }
        }
        String s1 = String.format("Array is sorted in %d swaps.", s);
        String s2 = String.format("First Element: %d", arr[0]);
        String s3 = String.format("Last Element: %d", arr[n - 1]);
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
    }

    static void swap(int[] arr, int x, int y) {
        int a = arr[x];
        int b = arr[y];
        arr[x] = b;
        arr[y] = a;
    }

}
