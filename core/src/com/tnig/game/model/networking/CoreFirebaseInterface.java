package com.tnig.game.model.networking;

import com.tnig.game.model.models.players.Player;

import java.util.ArrayList;

public class CoreFirebaseInterface  implements NetworkService {
    @Override
    public void pushHighscore(PlayerData data) {

    }

    @Override
    public void updateHighscore() {

    }

    @Override
    public ArrayList<Integer> getLevels() {
        return null;
    }

    @Override
    public ArrayList<ArrayList<String>> getHighScore(Integer level) {
        return null;
    }
}
