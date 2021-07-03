package com.nelsw.hackerrank;

import java.util.List;
import java.util.stream.Collectors;

public class SparseArrays {

    public static List<Integer> matchingStrings(List<String> strings, List<String> queries) {
        return queries
                .stream()
                .map(q -> strings.stream().filter(s -> s.equals(q)).count())
                .mapToInt(Long::intValue)
                .boxed()
                .collect(Collectors.toList());
    }

}
