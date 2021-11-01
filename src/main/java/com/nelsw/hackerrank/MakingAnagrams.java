package com.nelsw.hackerrank;

import lombok.extern.log4j.Log4j2;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Given two strings, a and b, that may or may not be of the same length, determine the minimum number of character
 * deletions required to make and anagrams. Any characters can be deleted from either of the strings.
 */
@Log4j2
public class MakingAnagrams {

    public static void main(String[] args) {

        var a   = "cde";
        var b   = "abc";
        var act = makeAnagram(a, b);
        var exp = 4;

        System.out.println(Objects.equals(act, exp));
    }

    public static int makeAnagram(String a, String b) {

        int deletions = 0;

        Collection<String> aLetters = Stream.of(a.split("")).collect(Collectors.toList());
        Collection<String> bLetters = Stream.of(b.split("")).collect(Collectors.toList());

        Set<String> letters = new HashSet<>(aLetters);
        letters.addAll(bLetters);

        for (String letter : letters) {
            deletions += Math.abs(Collections.frequency(aLetters, letter) - Collections.frequency(bLetters, letter));
        }

        return deletions;
    }

}
