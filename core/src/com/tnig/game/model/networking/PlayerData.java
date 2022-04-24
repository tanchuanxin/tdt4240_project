package com.tnig.game.model.networking;

import com.tnig.game.model.GameState;

import java.util.LinkedHashMap;
import java.util.Map;

public class PlayerData {

    private String name;
    private int score;
    private int mapNumber;
    public static Map<String, Map<String, Integer>> scores = new LinkedHashMap<>();

    public PlayerData() {

    }

    public PlayerData(GameState gameState, String name) {
        this.name = name;
        this.score = gameState.getScore();
        this.mapNumber = gameState.getMapNumber();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public int getMapNumber() {
        return mapNumber;
    }
}