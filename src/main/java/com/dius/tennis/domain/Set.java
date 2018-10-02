package com.dius.tennis.domain;

import java.util.LinkedHashMap;
import java.util.stream.Collectors;

import static com.dius.tennis.domain.SetStatus.COMPLETE;
import static com.dius.tennis.domain.SetStatus.TIE_BREAKER;

public class Set {

    private final LinkedHashMap<Player, PlayerSetScore> playerSetScores;

    private SetStatus setStatus = SetStatus.REGULAR_PROGRESS;

    public Set(Player playerA, Player playerB) {
        this.playerSetScores = new LinkedHashMap<>();
        this.playerSetScores.put(playerA, new PlayerSetScore());
        this.playerSetScores.put(playerB, new PlayerSetScore());
    }

    public SetStatus getSetStatus() {
        return setStatus;
    }

    public SetStatus incrementSetScore(Player player) {
        if (isTieBreaker()) {
            this.setStatus = TIE_BREAKER;
            return setStatus;
        }
        playerSetScores.get(player).incrementScore(playerSetScores.get(getOpponent(player)).getScore());
        if (isTieBreaker()) {
            this.setStatus = TIE_BREAKER;
            return setStatus;
        }
        if (isWinningScore()) {
            this.setStatus = COMPLETE;
            return setStatus;
        }
        return setStatus;
    }

    public String getSetScore() {
        return this.playerSetScores.values().stream()
                .map(score -> String.valueOf(score.getScore()))
                .collect(Collectors.joining("-"));
    }

    public Player getOpponent(Player player) {
        return playerSetScores.keySet().stream().filter(entry -> !player.equals(entry))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(""));
    }

    private boolean isTieBreaker() {
        return this.playerSetScores.values().stream().allMatch(setScore -> setScore.getScore() == 6);
    }

    private boolean isWinningScore() {
        return playerSetScores.values().stream().anyMatch(score -> score.getScore() == 7) ||
                (playerSetScores.values().stream().anyMatch(score -> score.getScore() == 6) &&
                playerSetScores.values().stream().anyMatch(score -> score.getScore() < 5));
    }
}
