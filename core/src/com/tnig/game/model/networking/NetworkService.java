package com.tnig.game.model.networking;

import java.util.ArrayList;

public interface NetworkService {
    void pushHighscore(PlayerData data);

    void updateHighscore();

    ArrayList<Integer> getLevels();

    ArrayList<PlayerData> getHighScores(Integer level);
}
