package com.tnig.game.model.networking;


import java.util.ArrayList;

public interface NetworkService {
    void someFunction();

    void pushHighscore(int level, PlayerData firebasePlayer);

    void updateHighscore();

    public ArrayList<Integer> getLevels();

    public ArrayList<ArrayList<String>> getHighScore(Integer level);
}
