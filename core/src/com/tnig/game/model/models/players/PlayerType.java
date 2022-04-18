package com.tnig.game.model.models.players;

import com.tnig.game.model.models.ObjectType;
import com.tnig.game.model.models.ModelType;

public enum PlayerType implements ModelType {
    NORMALPLAYER, MOCK;

    @Override
    public ObjectType getObjectType() {
        return ObjectType.PLAYER;
    }
}
