package com.dius.tennis.domain;

import static com.dius.tennis.domain.GameStatus.COMPLETE;
import static com.dius.tennis.domain.SetStatus.TIE_BREAKER;

public class Match {

    private Player playerA;
    private Player playerB;

    private GameScore gameScore;

    private Set set;

    public void pointWonBy(String playerName) {
        if (set.getSetStatus() == SetStatus.COMPLETE) {
            // do nothing, report error?
            return;
        }
        Player playerThatWonPoint = getPlayerByName(playerName);

        if (set.getSetStatus() == TIE_BREAKER) {
            // engage tie breaker increments
            set.incrementTieBreaker(playerThatWonPoint);
            return;
        }

        // assume REGULAR_PROGRESS for set
        GameStatus gameStatus = gameScore.gamePointWon(playerThatWonPoint);

        if (gameStatus == COMPLETE) {
            // increment set
            set.incrementSetScore(playerThatWonPoint);
        }
    }

    public String score() {
        return set.getSetScore() + ", " + gameScore.gameScoreStatus();
    }

    private Player getPlayerByName(String playerName) {
        if (playerA.name.equalsIgnoreCase(playerName)) {
            return playerA;
        }
        if (playerB.name.equalsIgnoreCase(playerName)) {
            return playerB;
        }
        throw new IllegalArgumentException("Cannot find a player by this name");
    }

    private Player getOpponent(String playerName) {
        if (playerName.equalsIgnoreCase(playerA.name)) {
            return playerB;
        }
        if (playerName.equalsIgnoreCase(playerB.name)) {
            return playerA;
        }
        throw new IllegalArgumentException("Cannot find an opponent to unknown player");
    }

    public Match(String playerAName, String playerBName) {
        this.playerA = new Player(playerAName);
        this.playerB = new Player(playerBName);
        this.set = new Set(playerA, playerB);
        this.gameScore = new GameScore(playerA, playerB);
    }

}
