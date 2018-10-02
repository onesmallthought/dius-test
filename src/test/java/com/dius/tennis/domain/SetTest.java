package com.dius.tennis.domain;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

import static com.dius.tennis.domain.SetStatus.REGULAR_PROGRESS;
import static com.dius.tennis.domain.SetStatus.TIE_BREAKER;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class SetTest {

    private Player playerA;
    private Player playerB;

    @Before
    public void init() {
        this.playerA = new Player("player a");
        this.playerB = new Player("player b");
    }

    @Test
    public void shouldIncrementScoreAndHaveRegularProgressWhenNewSet() {
        Set set = new Set(playerA, playerB);

        assertThat(set.incrementSetScore(playerA), equalTo(REGULAR_PROGRESS));
        assertThat(set.getSetScore(), equalTo("1-0"));
    }

    @Test
    public void shouldIncrementScoreAndHaveRegularProgressWhenMidSet() {
        Set set = new Set(playerA, playerB);

        set.incrementSetScore(playerA);
        set.incrementSetScore(playerA);
        set.incrementSetScore(playerA);
        set.incrementSetScore(playerB);
        set.incrementSetScore(playerB);

        assertThat(set.getSetScore(), equalTo("3-2"));
    }

    @Test
    public void shouldNotIncrementScoreWhenTieBreaker() {
        Set set = new Set(playerA, playerB);

        set.incrementSetScore(playerA);
        set.incrementSetScore(playerA);
        set.incrementSetScore(playerA);
        set.incrementSetScore(playerA);
        set.incrementSetScore(playerA);
        set.incrementSetScore(playerA); // 6
        set.incrementSetScore(playerB);
        set.incrementSetScore(playerB);
        set.incrementSetScore(playerB);
        set.incrementSetScore(playerB);
        set.incrementSetScore(playerB);
        set.incrementSetScore(playerB); // 6

        assertThat(set.getSetScore(), equalTo("6-6"));
        assertThat(set.incrementSetScore(playerA), equalTo(TIE_BREAKER));
        assertThat(set.getSetScore(), equalTo("6-6"));
    }

}