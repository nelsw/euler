package com.nelsw.hackerrank;

import lombok.extern.log4j.Log4j2;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Sherlock considers a string to be valid if all characters of the string appear the same number of times.
 * <p>
 * It is also valid if he can remove just 1 character at 1 index in the string, and the remaining characters will occur
 * the same number of times.
 * <p>
 * Given a string S, determine if it is valid. If so, return YES, otherwise return NO.
 */
@Log4j2
public class SherlockAndTheValidString {

    public static void main(String[] args) {

        var a   = "ibfdgaeadiaefgbhbdghhhbgdfgeiccbiehhfcggchgghadhdhagfbahhddgghbdehidbibaeaagaeeigffcebfbaieggabcfbiiedcabfihchdfabifahcbhagccbdfifhghcadfiadeeaheeddddiecaicbgigccageicehfdhdgafaddhffadigfhhcaedcedecafeacbdacgfgfeeibgaiffdehigebhhehiaahfidibccdcdagifgaihacihadecgifihbebffebdfbchbgigeccahgihbcbcaggebaaafgfedbfgagfediddghdgbgehhhifhgcedechahidcbchebheihaadbbbiaiccededchdagfhccfdefigfibifabeiaccghcegfbcghaefifbachebaacbhbfgfddeceababbacgffbagidebeadfihaefefegbghgddbbgddeehgfbhafbccidebgehifafgbghafacgfdccgifdcbbbidfifhdaibgigebigaedeaaiadegfefbhacgddhchgcbgcaeaieiegiffchbgbebgbehbbfcebciiagacaiechdigbgbghefcahgbhfibhedaeeiffebdiabcifgccdefabccdghehfibfiifdaicfedagahhdcbhbicdgibgcedieihcichadgchgbdcdagaihebbabhibcihicadgadfcihdheefbhffiageddhgahaidfdhhdbgciiaciegchiiebfbcbhaeagccfhbfhaddagnfieihghfbaggiffbbfbecgaiiidccdceadbbdfgigibgcgchafccdchgifdeieicbaididhfcfdedbhaadedfageigfdehgcdaecaebebebfcieaecfagfdieaefdiedbcadchabhebgehiidfcgahcdhcdhgchhiiheffiifeegcfdgbdeffhgeghdfhbfbifgidcafbfcd";
        var act = isValid(a);
        var exp = "YES";
        System.out.println(Objects.equals(act, exp));
    }

    public static String isValid(String s) {

        List<String> letters = Arrays.asList(s.split(""));

        Map<String, Integer> letterFrequency = new HashSet<>(letters)
                .stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        chunk -> Collections.frequency(letters, chunk)));

        Collection<Integer> frequencies = letterFrequency.values().stream().sorted().collect(Collectors.toList());

        log.debug(frequencies);

        Map<Integer, Integer> m = frequencies
                .stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        chunk -> Collections.frequency(frequencies, chunk),
                        (o1, o2) -> o1));

        int mode = 0;
        for (Map.Entry<Integer, Integer> f : m.entrySet()) {
            if (f.getValue() > mode) {
                mode = f.getKey();
            }
        }

        boolean removalUsed = false;
        for (int value : frequencies) {

            if (value == mode) {
                continue;
            }

            if (removalUsed) {
                return "NO";
            }

            removalUsed = true;
        }


        return "YES";
    }

}
