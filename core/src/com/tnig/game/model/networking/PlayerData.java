package com.tnig.game.model.networking;

import java.util.LinkedHashMap;
import java.util.Map;

public class PlayerData {

    private String name;
    private int score;
    public static Map<String, Map<String, Integer>> scores = new LinkedHashMap<>();

    public PlayerData() {
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
}