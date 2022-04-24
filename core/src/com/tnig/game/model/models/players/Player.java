package com.tnig.game.model.models.players;

public interface Player {

    int getScore();
    float getAttackTimeout();
    float getWinTimeout();
    PlayerState getState();
    PlayerDirection getDirection();
}
