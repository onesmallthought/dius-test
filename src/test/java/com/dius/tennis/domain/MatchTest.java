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


}