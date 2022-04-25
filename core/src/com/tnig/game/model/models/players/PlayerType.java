package com.tnig.game.model.models.players;

import com.tnig.game.model.models.enums.ObjectType;
import com.tnig.game.model.models.interfaces.ModelType;

public enum PlayerType implements ModelType {
    NORMAL_PLAYER, MOCK_PLAYER;

    @Override
    public ObjectType getObjectType() {
        return ObjectType.PLAYER;
    }
}
