package com.dius.tennis.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class PlayerSetScore {

    private static final Map<Integer, Function<Integer, Integer>> SET_POINT_WON = new HashMap<Integer, Function<Integer, Integer>>() {{
        put(0, (opponentScore) -> 1);
        put(1, (opponentScore) -> 2);
        put(2, (opponentScore) -> 3);
        put(3, (opponentScore) -> 4);
        put(4, (opponentScore) -> 5);
        put(5, (opponentScore) -> 6);
        put(6, (opponentScore) -> opponentScore == 5 ? 7 : 6);
        put(7, (opponentScore) -> 7);
    }};

    private int score = 0;
    private int tieBreaker = 0;

    public int incrementScore(int oppositionScore) {
        this.score = SET_POINT_WON.get(score).apply(oppositionScore);
        return score;
    }
    public int incrementTieBreaker(int oppositionTieBreaker) {
        if (tieBreaker > 6 && (tieBreaker - oppositionTieBreaker) > 1) {
            return this.tieBreaker;
        }
        return ++this.tieBreaker;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTieBreaker() {
        return tieBreaker;
    }

    public void setTieBreaker(int tieBreaker) {
        this.tieBreaker = tieBreaker;
    }

}
