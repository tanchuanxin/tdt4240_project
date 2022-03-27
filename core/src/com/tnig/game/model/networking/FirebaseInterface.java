package com.tnig.game.model.networking;

import java.util.ArrayList;

public interface FirebaseInterface {
    void pushHighscore(int level, FirebasePlayer firebasePlayer);

    void SetOnValueChangedListener(FirebasePlayer firebasePlayer);
}
