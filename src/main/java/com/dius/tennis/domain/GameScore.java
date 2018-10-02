package com.dius.tennis.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static com.dius.tennis.domain.GamePoint.*;
import static com.dius.tennis.domain.GameStatus.COMPLETE;
import static com.dius.tennis.domain.GameStatus.PLAYING;

public class GameScore {

    private static final Map<GamePoint, Function<GamePoint, GamePoint>> POINT_WON =
            new HashMap<GamePoint, Function<GamePoint, GamePoint>>() {{
        put(LOVE, (opponentScore) -> FIFTEEN);
        put(FIFTEEN, (opponentScore) -> THIRTY);
        put(THIRTY, (opponentScore) -> FORTY);
        put(FORTY, (opponentScore) -> opponentScore == FORTY ? ADVANTAGE : opponentScore == ADVANTAGE ? FORTY : LOVE);
        put(ADVANTAGE, (opponentScore) -> LOVE);
    }};

    private static final Map<GamePoint, Function<GamePoint, GamePoint>> POINT_LOST =
            new HashMap<GamePoint, Function<GamePoint, GamePoint>>() {{
        put(LOVE, (opponentScore) -> LOVE);
        put(FIFTEEN, (opponentScore) -> opponentScore == FORTY ? LOVE : FIFTEEN);
        put(THIRTY, (opponentScore) -> opponentScore == FORTY ? LOVE : THIRTY);
        put(FORTY, (opponentScore) -> opponentScore == ADVANTAGE ? LOVE : FORTY);
        put(ADVANTAGE, (opponentScore) -> FORTY);
    }};

    private final Player playerA;
    private final Player playerB;
    private GamePoint playerAPoint = LOVE;
    private GamePoint playerBPoint = LOVE;

    public GameScore(Player playerA, Player playerB) {
        this.playerA = playerA;
        this.playerB = playerB;
    }

    public String gameScoreStatus() {
        if (playerAPoint == FORTY && playerBPoint == FORTY) {
            return "Deuce";
        }
        if (playerAPoint == ADVANTAGE) {
            return ADVANTAGE.pointName() + " " + playerA.name;
        }
        if (playerBPoint == ADVANTAGE) {
            return ADVANTAGE.pointName() + " " + playerB.name;
        }
        return playerAPoint.pointName() + "-" + playerBPoint.pointName();
    }

    public GameStatus gamePointWon(Player byPlayer) {
        if (!byPlayer.name.equalsIgnoreCase(playerA.name) && !byPlayer.name.equalsIgnoreCase(playerB.name)) {
            throw new IllegalArgumentException("By player with name '" + byPlayer.name + "' not found in game");
        }
        if (byPlayer.name.equalsIgnoreCase(playerA.name)) {
            GamePoint newGamePointA = POINT_WON.get(playerAPoint).apply(playerBPoint);
            this.playerBPoint = POINT_LOST.get(playerBPoint).apply(playerAPoint);
            this.playerAPoint = newGamePointA;
        }
        if (byPlayer.name.equalsIgnoreCase(playerB.name)) {
            GamePoint newGamePointB = POINT_WON.get(playerBPoint).apply(playerAPoint);
            this.playerAPoint = POINT_LOST.get(playerAPoint).apply(playerBPoint);
            this.playerBPoint = newGamePointB;
        }
        if (this.playerAPoint == LOVE && this.playerBPoint == LOVE) {
            return COMPLETE;
        }
        return PLAYING;
    }

}
