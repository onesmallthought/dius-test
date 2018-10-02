package com.dius.tennis;

import com.dius.tennis.domain.Match;

public class Application {

    private static final String PLAYER_A = "player 1";
    private static final String PLAYER_B = "player 2";

    public static void main(String[] args) {
        Match match = new Match(PLAYER_A, PLAYER_B);

        // as per the original spec
        match.pointWonBy("player 1");
        match.pointWonBy("player 2");
        // this will return "0-0, 15-15"
        print(match.score());

        match.pointWonBy("player 1");
        match.pointWonBy("player 1");
        // this will return "0-0, 40-15"
        print(match.score());

        match.pointWonBy("player 2");
        match.pointWonBy("player 2");
        // this will return "0-0, Deuce"
        print(match.score());

        match.pointWonBy("player 1");
        // this will return "0-0, Advantage player 1"
        print(match.score());

        match.pointWonBy("player 1");
        // this will return "1-0"
        print(match.score());
    }

    private static void print(String value) {
        System.out.println(value);
    }
}
