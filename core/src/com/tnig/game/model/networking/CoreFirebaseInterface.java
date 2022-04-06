package com.tnig.game.model.networking;

import java.util.ArrayList;

public class CoreFirebaseInterface  implements Network{

    @Override
    public void someFunction() {
        System.out.println("Just some core function.");
    }

    @Override
    public void pushHighscore(int level, PlayerData firebasePlayer) {

    }

    @Override
    public void updateHighscore() {

    }

    @Override
    public ArrayList getHighScore(Integer level) {
        return null;
    }
}
