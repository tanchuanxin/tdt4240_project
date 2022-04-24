package com.tnig.game.model.models.players;

import com.tnig.game.model.models.enums.Direction;

public interface Player {

    int getScore();
    float getAttackTimeout();
    float getWinTimeout();
    PlayerState getState();
    Direction getDirection();
}
