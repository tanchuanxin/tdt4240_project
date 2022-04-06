package com.tnig.game.model.networking;


import java.util.ArrayList;

public interface Network {
    void someFunction();

    void pushHighscore(int level, PlayerData firebasePlayer);

    void updateHighscore();

    public ArrayList getHighScore(Integer level);
}
