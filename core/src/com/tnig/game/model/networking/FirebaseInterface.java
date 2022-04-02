package com.tnig.game.model.networking;

import java.util.ArrayList;
import java.util.Map;

public interface FirebaseInterface {
    void pushHighscore(int level, FirebasePlayer firebasePlayer);

    void updateHighscore();

    public ArrayList getHighScore(int level);
}
