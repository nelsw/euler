package com.nelsw.bx;

import lombok.extern.log4j.Log4j2;

import java.util.*;
import java.util.stream.Collectors;

@Log4j2
public class P2 {

    public static void main(String[] args) {
        var a = arrange("The lines are printed in reverse order.");
        var e = "In the are lines order printed reverse.";
        var b = Objects.equals(a, e);
        System.out.println(b);
        System.out.println(a);
        System.out.println(e);
    }

    public static String arrange(String sentence) {

        Map<Integer, List<String>> m = new TreeMap<>();

        String[] words = sentence
                .replace(".", "")
                .split("\\s");

        for (String word : words) {
            List<String> l = m.getOrDefault(word.length(), new ArrayList<>());
            l.add(word);
            m.put(word.length(), l);
        }

        String s = m
                .values()
                .stream()
                .flatMap(Collection::stream)
                .map(String::toLowerCase)
                .collect(Collectors.joining(" "));

        return s.substring(0, 1).toUpperCase() + s.substring(1) + ".";
    }

}
