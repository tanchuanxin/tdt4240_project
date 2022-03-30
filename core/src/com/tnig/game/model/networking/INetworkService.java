package com.tnig.game.model.networking;


public interface INetworkService {
    void pushHighscore(int level, PlayerData playerData);

    void SetOnValueChangedListener(PlayerData playerData);
}
