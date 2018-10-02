package com.dius.tennis.domain;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class PlayerSetScoreTest {

    private PlayerSetScore playerASetState;
    private PlayerSetScore playerBSetState;

    @Before
    public void init() {
        playerASetState = new PlayerSetScore();
        playerBSetState = new PlayerSetScore();
    }

    @Test
    public void shouldIncrementSetScoreWhenZeroZero() {
        playerASetState.incrementScore(0);

        assertThat(playerASetState.getScore(), equalTo(1));
    }

    @Test
    public void shouldNotIncrementSetScoreWhenSetIsComplete() {
        playerASetState.incrementScore(playerBSetState.getScore());
        playerASetState.incrementScore(playerBSetState.getScore());
        playerASetState.incrementScore(playerBSetState.getScore());
        playerASetState.incrementScore(playerBSetState.getScore());
        playerASetState.incrementScore(playerBSetState.getScore());
        playerASetState.incrementScore(playerBSetState.getScore());

        assertThat(playerASetState.incrementScore(playerBSetState.getScore()), equalTo(6));
        assertThat(playerASetState.incrementScore(playerBSetState.getScore()), equalTo(6));
    }

    @Test
    public void shouldNotIncrementSetScoreWhenSetIsInTieBreaker() {
        playerASetState.incrementScore(playerBSetState.getScore());
        playerASetState.incrementScore(playerBSetState.getScore());
        playerASetState.incrementScore(playerBSetState.getScore());
        playerASetState.incrementScore(playerBSetState.getScore());
        playerASetState.incrementScore(playerBSetState.getScore());
        playerASetState.incrementScore(playerBSetState.getScore());
        playerBSetState.incrementScore(playerASetState.getScore());
        playerBSetState.incrementScore(playerASetState.getScore());
        playerBSetState.incrementScore(playerASetState.getScore());
        playerBSetState.incrementScore(playerASetState.getScore());
        playerBSetState.incrementScore(playerASetState.getScore());
        playerBSetState.incrementScore(playerASetState.getScore());

        assertThat(playerASetState.incrementScore(playerBSetState.getScore()), equalTo(6));
        assertThat(playerBSetState.incrementScore(playerASetState.getScore()), equalTo(6));
    }

    @Test
    public void shouldIncrementSetScoreToSevenWhenOpponentHasSetScoreFive() {
        playerASetState.incrementScore(playerBSetState.getScore());
        playerASetState.incrementScore(playerBSetState.getScore());
        playerASetState.incrementScore(playerBSetState.getScore());
        playerASetState.incrementScore(playerBSetState.getScore());
        playerASetState.incrementScore(playerBSetState.getScore());
        playerASetState.incrementScore(playerBSetState.getScore());
        playerBSetState.incrementScore(playerASetState.getScore());
        playerBSetState.incrementScore(playerASetState.getScore());
        playerBSetState.incrementScore(playerASetState.getScore());
        playerBSetState.incrementScore(playerASetState.getScore());
        playerBSetState.incrementScore(playerASetState.getScore());

        assertThat(playerASetState.incrementScore(playerBSetState.getScore()), equalTo(7));
    }
}