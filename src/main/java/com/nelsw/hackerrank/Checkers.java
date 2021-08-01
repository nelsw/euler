package com.nelsw.hackerrank;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Checkers {


    public static void main(String[] args) {

        List<String>  names  = List.of("amy", "david", "heraldo", "aakansha", "aleksa");
        List<Integer> scores = List.of(100, 100, 50, 75, 150);

        Player[] player  = new Player[5];
        Checker  checker = new Checker();

        for (int i = 0; i < 5; i++) {
            player[i] = new Player(names.get(i), scores.get(i));
        }

        Arrays.sort(player, checker);
        for (Player value : player) {
            System.out.printf("%s %s\n", value.name, value.score);
        }
    }

    static class Player {
        String name;
        int    score;

        Player(String name, int score) {
            this.name  = name;
            this.score = score;
        }
    }

    static class Checker implements Comparator<Player> {
        // complete this method
        public int compare(Player a, Player b) {
            int c2 = Integer.compare(b.score, a.score);
            if (c2 == 0) {
                return a.name.compareTo(b.name);
            } else {
                return c2;
            }

        }
    }

}
