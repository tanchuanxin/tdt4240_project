package com.tnig.game.model.models.obstacles;

import com.tnig.game.model.models.interfaces.ModelType;
import com.tnig.game.model.models.ObjectType;

public enum ObstacleType implements ModelType {
    SPIKE, MOCK_TYPE;

    @Override
    public ObjectType getObjectType() {
        return ObjectType.OBSTACLE;
    }
}
