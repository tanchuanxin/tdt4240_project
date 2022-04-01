package com.tnig.game.model.networking;


public interface Network {
    void pushHighscore(int level, PlayerData playerData);

    void SetOnValueChangedListener(PlayerData playerData);
}
