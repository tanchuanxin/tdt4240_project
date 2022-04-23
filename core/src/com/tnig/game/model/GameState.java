package com.tnig.game.model;

public class GameState {
    private final int score;
    private final int mapNumber;

    public GameState(int score, int mapNumber) {
        this.score = score;
        this.mapNumber = mapNumber;
    }

    public int getScore() {
        return score;
    }

    public int getMapNumber() {
        return mapNumber;
    }
}
