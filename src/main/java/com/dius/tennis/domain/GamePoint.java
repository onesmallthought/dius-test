package com.dius.tennis.domain;

public enum GamePoint {

    LOVE("0"),
    FIFTEEN("15"),
    THIRTY("30"),
    FORTY("40"),
    ADVANTAGE("Advantage");

    private String pointName;

    GamePoint(String pointName) {
        this.pointName = pointName;
    }

    public String pointName() {
        return this.pointName;
    }

}
