package com.tnig.game.model.networking;


import java.util.ArrayList;

public interface NetworkService {
    void pushHighscore(PlayerData data);

    void updateHighscore();

    public ArrayList<Integer> getLevels();

    public ArrayList<PlayerData> getHighScores(Integer level);
}
