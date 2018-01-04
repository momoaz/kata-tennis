package com.azizi.mohamed.kata.tennis;

public enum GameScore {
    ZERO("0"), FIFTEEN("15"), THIRTY("30"), FOURTY("40"), DEUCE("DEUCE"), ADVANTAGE("ADV");

    private String score;

    GameScore(String score) {
        this.score = score;
    }

    public String getScore() {
        return score;
    }
}
