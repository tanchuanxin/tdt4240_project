package com.tnig.game.desktop;

import com.tnig.game.model.networking.NetworkService;
import com.tnig.game.model.networking.PlayerData;

import java.util.ArrayList;

public class DesktopFirebaseInterface implements NetworkService {
    @Override
    public void pushHighscore(int level, PlayerData firebasePlayer) {

    }

    @Override
    public void updateHighscore() {

    }

    @Override
    public ArrayList<Integer> getLevels() {
        return null;
    }

    @Override
    public ArrayList getHighScore(Integer level) {
        return null;
    }
}
