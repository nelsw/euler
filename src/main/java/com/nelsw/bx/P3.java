package com.nelsw.bx;

import lombok.extern.log4j.Log4j2;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Log4j2
public class P3 {

    public static void main(String[] args) {

        var a1 = List.of("a", "jk", "abb", "mn", "abc");
        var a2 = List.of("bb", "kj", "bbc", "op", "def");
        var a  = getMinimumDifference(a1, a2);
        var e  = List.of(-1, 0, 1, 2, 3);
        var b  = Objects.equals(a, e);
        System.out.println(b);

        a1 = List.of("nzdvdqtgotierllkqtvfztesncymazaoigzubwmrwhbdjhvjeuiudbcapaxkmjouafhcrecuajnttdtsuruwyjjuwarmbqeoeupczckvlxbqkjyooloycdzqkryevetidwgqwhnpjmtkkfaosnkxggwyrwfyhldjoxitphsaouguxqmfexvzmwcscghzpabdravqavvtowunseshtmwtznjqxxidabbzqybcxzdrdfvbpdrmcfzxgaunshgcasarnjmgbocbphwtjwxwgbxofcsnhftptpagpsphqtefdhfbusvhfaoqhyuzndyhqeduesdlnsgdtihxjmplquzunzbccnmakyaqkzoyzdwltkwtszsozoiuokuqbezltoujbzbgbtobuqttlwdilfpgqnilklanmxuahsrzxqahmfhgbflhboiyntvrvwxcvblxngmnhtksabkufqhwktr");
        a2 = List.of("iyfbpvaoohjlftasyotlbjycdqckqfwugsqqokhaolltremzkdouhtwkhxcjnjvzgevlhatvmibnhleqhwiscncgqzkcsedjyidqketigvsidklteisqzzecssseswawgntsubrvhkjdxzxlmnhkknwpfhpavtbowseovzliqntbzrazxpihyxwemougxnssmtbhsomzbnxszgrlkemajvlvzoiyxqtaedetftnkmnsqogpptjzngadmeidxvbbabhsfttfoqhwfzjocslvuepjwnwshoujrwbhiobirflentxqkzdjipxpdflujjzyopfafhfqimbqakxgpqnpevrwdizsyowgdpzdlhczbgxbjvzhveytajflfhoxlusxbkvanxspqhosviygqdjymceyngdwqtbsfglhnnethbweacnztqyculrlmqsibyhoebymlkonlhrteyskaogk");
        a  = getMinimumDifference(a1, a2);
        e  = List.of(51);
        b  = Objects.equals(a, e);
        System.out.println(b);
        log.debug(a);
        log.debug(e);
    }

    public static List<Integer> getMinimumDifference(List<String> a, List<String> b) {

        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < a.size(); i++) {

            String s1 = a.get(i);
            String s2 = b.get(i);

            if (s1.length() != s2.length()) {
                result.add(-1);
                continue;
            }

            List<Integer> l1 = s1.chars().sorted().boxed().collect(Collectors.toList());
            List<Integer> l2 = s2.chars().sorted().boxed().collect(Collectors.toList());
            if (l1.equals(l2)) {
                result.add(0);
                continue;
            }

            int changes = 0;

            Collection<String> aLetters = Stream.of(s1.split("")).collect(Collectors.toList());
            Collection<String> bLetters = Stream.of(s2.split("")).collect(Collectors.toList());

            Set<String> letters = new HashSet<>(aLetters);
            letters.addAll(bLetters);

            for (String letter : letters) {
                changes += Math.abs(Collections.frequency(aLetters, letter) - Collections.frequency(bLetters, letter));
            }

            result.add(changes / 2);
        }

        return result;
    }

}
