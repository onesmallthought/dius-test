package com.dius.tennis.domain;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class GameScoreTest {

    private Player playerA;
    private Player playerB;

    @Before
    public void setup() {
        this.playerA = new Player("player a");
        this.playerB = new Player("player b");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotAllowUnknownPlayerToWinAPoint() {
        GameScore gameScore = new GameScore(playerA, playerB);

        gameScore.gamePointWon(new Player("unknown player"));
    }

    @Test
    public void shouldDefaultNewGameScoreToZeroZero() {
        GameScore gameScore = new GameScore(playerA, playerB);

        assertThat(gameScore.gameScoreStatus(), equalTo("0-0"));
    }

    @Test
    public void shouldIncrementGameScoreToFifteenZeroWhenPlayerAWinsPoint() {
        GameScore gameScore = new GameScore(playerA, playerB);
        gameScore.gamePointWon(playerA);

        assertThat(gameScore.gameScoreStatus(), equalTo("15-0"));
    }

    @Test
    public void shouldIncrementGameScoreToZeroFifteenWhenPlayerBWinsPoint() {
        GameScore gameScore = new GameScore(playerA, playerB);
        gameScore.gamePointWon(playerB);

        assertThat(gameScore.gameScoreStatus(), equalTo("0-15"));
    }

    @Test
    public void shouldShowDeuceWhenPlayersAreBothFortyForty() {
        GameScore gameScore = new GameScore(playerA, playerB);
        gameScore.gamePointWon(playerB);
        gameScore.gamePointWon(playerB);
        gameScore.gamePointWon(playerB);
        gameScore.gamePointWon(playerA);
        gameScore.gamePointWon(playerA);
        gameScore.gamePointWon(playerA);

        assertThat(gameScore.gameScoreStatus(), equalTo("Deuce"));
    }

    @Test
    public void shouldShowAdvantagePlayerAWhenPlayersAreBothFortyFortyAndPlayerAWinsAPoint() {
        GameScore gameScore = new GameScore(playerA, playerB);
        gameScore.gamePointWon(playerB);
        gameScore.gamePointWon(playerB);
        gameScore.gamePointWon(playerB);
        gameScore.gamePointWon(playerA);
        gameScore.gamePointWon(playerA);
        gameScore.gamePointWon(playerA);

        gameScore.gamePointWon(playerA);

        assertThat(gameScore.gameScoreStatus(), equalTo("Advantage " + playerA.name));
    }

    @Test
    public void shouldReturnToZeroZeroWhenGameIsWon() {
        GameScore gameScore = new GameScore(playerA, playerB);
        gameScore.gamePointWon(playerB);
        gameScore.gamePointWon(playerB);
        gameScore.gamePointWon(playerB);
        gameScore.gamePointWon(playerB);

        assertThat(gameScore.gameScoreStatus(), equalTo("0-0"));
    }

    @Test
    public void shouldReturnToZeroZeroWhenGameIsWonAfterDeuce() {
        GameScore gameScore = new GameScore(playerA, playerB);
        gameScore.gamePointWon(playerB);
        gameScore.gamePointWon(playerB);
        gameScore.gamePointWon(playerB);
        gameScore.gamePointWon(playerA);
        gameScore.gamePointWon(playerA);
        gameScore.gamePointWon(playerA);

        gameScore.gamePointWon(playerB);
        gameScore.gamePointWon(playerB);

        assertThat(gameScore.gameScoreStatus(), equalTo("0-0"));
    }

    @Test
    public void shouldReturnToZeroZeroWhenGameIsWonAfterMultipleDeuce() {
        GameScore gameScore = new GameScore(playerA, playerB);
        gameScore.gamePointWon(playerB);
        gameScore.gamePointWon(playerB);
        gameScore.gamePointWon(playerB);
        gameScore.gamePointWon(playerA);
        gameScore.gamePointWon(playerA);
        gameScore.gamePointWon(playerA);

        gameScore.gamePointWon(playerB);
        gameScore.gamePointWon(playerA);
        gameScore.gamePointWon(playerB);
        gameScore.gamePointWon(playerB);

        assertThat(gameScore.gameScoreStatus(), equalTo("0-0"));
    }

    @Test
    public void shouldNotCompleteGameWhenPlayerWinsThreePointsInARow() {
        GameScore gameScore = new GameScore(playerA, playerB);
        gameScore.gamePointWon(playerB);
        gameScore.gamePointWon(playerB);

        assertThat(gameScore.gamePointWon(playerB), equalTo(GameStatus.PLAYING));
    }

    @Test
    public void shouldCompleteGameWhenPlayerWinsFourPointsInARow() {
        GameScore gameScore = new GameScore(playerA, playerB);
        gameScore.gamePointWon(playerB);
        gameScore.gamePointWon(playerB);
        gameScore.gamePointWon(playerB);

        assertThat(gameScore.gamePointWon(playerB), equalTo(GameStatus.COMPLETE));
    }

}