package com.tnig.game.desktop;

import com.tnig.game.model.networking.NetworkService;
import com.tnig.game.model.networking.PlayerData;

import java.util.ArrayList;

public class DesktopFirebaseInterface implements NetworkService {
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
    public ArrayList<PlayerData> getHighScores(Integer level) {
        return null;
    }
}
