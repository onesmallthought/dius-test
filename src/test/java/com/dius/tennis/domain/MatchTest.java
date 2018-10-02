package com.dius.tennis.domain;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class MatchTest {

    private String playerAName = "player a";
    private String playerBName = "player b";
    private Match match;

    @Before
    public void init() {
        match = new Match(playerAName, playerBName);
    }

    @Test
    public void shouldHaveZeroZeroWhenNewMatch() {
        assertThat(match.score(), equalTo("0-0, 0-0"));
    }

    @Test
    public void shouldHaveGamePointsWhenNewMatchAndPointsWon() {
        match.pointWonBy(playerAName);
        match.pointWonBy(playerAName);
        match.pointWonBy(playerBName);

        assertThat(match.score(), equalTo("0-0, 30-15"));
    }

    @Test
    public void shouldHaveSetPointWhenFourPointsWonByPlayerA() {
        match.pointWonBy(playerAName);
        match.pointWonBy(playerAName);
        match.pointWonBy(playerAName);
        match.pointWonBy(playerBName);
        match.pointWonBy(playerBName);
        match.pointWonBy(playerAName);

        assertThat(match.score(), equalTo("1-0, 0-0"));
    }

    @Test
    public void shouldHaveMultipleSetPointsWhenMidMatch() {
        match.pointWonBy(playerAName);
        match.pointWonBy(playerAName);
        match.pointWonBy(playerAName);
        match.pointWonBy(playerBName);
        match.pointWonBy(playerBName);
        match.pointWonBy(playerAName);

        match.pointWonBy(playerBName);
        match.pointWonBy(playerBName);
        match.pointWonBy(playerAName);
        match.pointWonBy(playerBName);
        match.pointWonBy(playerBName);

        match.pointWonBy(playerAName);
        match.pointWonBy(playerAName);

        assertThat(match.score(), equalTo("1-1, 30-0"));
    }

    @Test
    public void shouldHaveTieBreakerWhenSetScoreIsSixSix() {
        winSetPointFor(playerAName);
        winSetPointFor(playerAName);
        winSetPointFor(playerAName);
        winSetPointFor(playerAName);
        winSetPointFor(playerAName);
        winSetPointFor(playerBName);
        winSetPointFor(playerBName);
        winSetPointFor(playerBName);
        winSetPointFor(playerBName);
        winSetPointFor(playerBName);

        winSetPointFor(playerAName);
        winSetPointFor(playerBName);

        assertThat(match.score(), equalTo("6-6(0-0), 0-0"));
    }

    @Test
    public void shouldHaveIncrementTieBreakerScoreWhenSetScoreIsSixSix() {
        winSetPointFor(playerAName);
        winSetPointFor(playerAName);
        winSetPointFor(playerAName);
        winSetPointFor(playerAName);
        winSetPointFor(playerAName);
        winSetPointFor(playerBName);
        winSetPointFor(playerBName);
        winSetPointFor(playerBName);
        winSetPointFor(playerBName);
        winSetPointFor(playerBName);
        winSetPointFor(playerAName);
        winSetPointFor(playerBName);

        // tie breaker points
        match.pointWonBy(playerAName);
        match.pointWonBy(playerAName);
        match.pointWonBy(playerBName);

        assertThat(match.score(), equalTo("6-6(2-1), 0-0"));
    }

    @Test
    public void shouldHaveCompletedMatchWhenTieBreakerFinished() {
        winSetPointFor(playerAName);
        winSetPointFor(playerAName);
        winSetPointFor(playerAName);
        winSetPointFor(playerAName);
        winSetPointFor(playerAName);
        winSetPointFor(playerBName);
        winSetPointFor(playerBName);
        winSetPointFor(playerBName);
        winSetPointFor(playerBName);
        winSetPointFor(playerBName);
        winSetPointFor(playerAName);
        winSetPointFor(playerBName);

        // tie breaker points
        match.pointWonBy(playerAName);
        match.pointWonBy(playerAName);
        match.pointWonBy(playerAName);
        match.pointWonBy(playerAName);
        match.pointWonBy(playerAName);
        match.pointWonBy(playerAName);
        match.pointWonBy(playerBName);
        match.pointWonBy(playerBName);
        match.pointWonBy(playerBName);
        match.pointWonBy(playerBName);
        match.pointWonBy(playerBName);

        match.pointWonBy(playerAName);

        assertThat(match.score(), equalTo("7-6, 0-0"));  // assumption that you don't need to show tie breaker stats?
    }

    @Test
    public void shouldNotCompletedMatchWhenTieBreakerPointsDifferenceIsOne() {
        winSetPointFor(playerAName);
        winSetPointFor(playerAName);
        winSetPointFor(playerAName);
        winSetPointFor(playerAName);
        winSetPointFor(playerAName);
        winSetPointFor(playerBName);
        winSetPointFor(playerBName);
        winSetPointFor(playerBName);
        winSetPointFor(playerBName);
        winSetPointFor(playerBName);
        winSetPointFor(playerAName);
        winSetPointFor(playerBName);

        // tie breaker points
        match.pointWonBy(playerAName);
        match.pointWonBy(playerAName);
        match.pointWonBy(playerAName);
        match.pointWonBy(playerAName);
        match.pointWonBy(playerAName);
        match.pointWonBy(playerAName);
        match.pointWonBy(playerBName);
        match.pointWonBy(playerBName);
        match.pointWonBy(playerBName);
        match.pointWonBy(playerBName);
        match.pointWonBy(playerBName);
        match.pointWonBy(playerBName);
        match.pointWonBy(playerAName); // 7-6 tie breaker

        assertThat(match.score(), equalTo("6-6(7-6), 0-0"));
    }


    private void winSetPointFor(String playerName) {
        match.pointWonBy(playerName);
        match.pointWonBy(playerName);
        match.pointWonBy(playerName);
        match.pointWonBy(playerName);
    }

}