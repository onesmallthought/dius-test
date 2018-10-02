package com.dius.tennis.domain;

import static com.dius.tennis.domain.GameStatus.COMPLETE;

public class Match {

    private Player playerA;
    private Player playerB;

    private GameScore gameScore;

    public void pointWonBy(String playerName) {
        Player playerThatWonPoint = getPlayerByName(playerName);
        Player playerThatDidntWinPoint = getOpponent(playerName);

        GameStatus gameStatus = gameScore.gamePointWon(playerThatWonPoint);

        if (gameStatus == COMPLETE) {
            // increment set
        }
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

}
