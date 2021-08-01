package com.nelsw.hackerrank;

import lombok.extern.log4j.Log4j2;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class MergeSort {

    public static void main(String[] args) throws Exception {
        case1();
        case2();
    }


    static void case1() throws Exception {
        var  queries = List.of(2, 1, 3, 1, 2);
        long alpha   = System.currentTimeMillis();
        long a       = countInversions(queries);
        long omega   = System.currentTimeMillis();
        long e       = 4;

        System.out.println(omega - alpha + "ms");
        if (a != e) {
            System.out.println(a);
            System.out.println(e);
        }
        System.out.println();
    }

    static void case2() throws Exception {

        List<String> lines = Files.readAllLines(Path.of("queries.txt"));

        String l = lines.remove(0);

        l = lines.remove(0);

        String ll = lines.remove(0);

        String[] chunks = ll.split(" ");

        List<Integer> arr = new ArrayList<>();
        for (String chunk : chunks) {
            arr.add(Integer.parseInt(chunk));
        }

        long alpha = System.currentTimeMillis();
        long a     = countInversions(arr);
        long omega = System.currentTimeMillis();
        long e     = 12_309_087;

        System.out.println(omega - alpha + "ms");
        if (a != e) {
            System.out.println(a);
            System.out.println(e);
        }
        System.out.println();
    }

    public static long countInversions(List<Integer> arr) {
        int   n = arr.size();
        int[] a = arr.stream().mapToInt(i -> i).toArray();
        return mergeSort(a, n - 1);
    }

    public static long mergeSort(int[] a, int n) {
        long inversions = 0;
        if (n < 2) {
            return inversions;
        }
        int   mid = n / 2;
        int[] l   = new int[mid];
        int[] r   = new int[n - mid];

        for (int i = 0; i < mid; i++) {
            l[i] = a[i];
            inversions++;
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = a[i];
        }


        inversions += mergeSort(l, mid);
        inversions += mergeSort(r, n - mid);

        inversions += merge(a, l, r, mid, n - mid);

        return inversions;
    }

    public static long merge(int[] a, int[] l, int[] r, int left, int right) {
        long inversions = 0;
        int  mid        = (left + right) / 2;
        int  i          = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i] <= r[j]) {
                a[k++] = l[i++];
                inversions += mid - i + 1;
            } else {
                a[k++] = r[j++];
            }
        }
        while (i < left) {
            a[k++] = l[i++];
        }
        while (j < right) {
            a[k++] = r[j++];
        }
        return inversions;
    }

    static void swap(int[] arr, int x, int y) {
        int a = arr[x];
        int b = arr[y];
        arr[x] = b;
        arr[y] = a;
    }

}
