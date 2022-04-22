package com.tnig.game.model.models.coins;

import com.tnig.game.model.models.interfaces.ModelType;
import com.tnig.game.model.models.ObjectType;

public enum CoinType implements ModelType {
    NORMAL_COIN,
    MOCK
    ;

    @Override
    public ObjectType getObjectType() {
        return ObjectType.COIN;
    }
}
