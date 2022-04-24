package com.tnig.game.model.models.obstacles;

import com.tnig.game.model.models.interfaces.ModelType;
import com.tnig.game.model.models.enums.ObjectType;

public enum ObstacleType implements ModelType {
    SPIKE,
    FIREBALL,
    FIREBALL_ALTERNATING,
    MOCK;

    @Override
    public ObjectType getObjectType() {
        return ObjectType.OBSTACLE;
    }
}
