package com.tnig.game.model.models.sensors;

import com.tnig.game.model.models.interfaces.ModelType;
import com.tnig.game.model.models.enums.ObjectType;

public enum SensorType implements ModelType {
    FINISH_LINE,
    DEATH_SENSOR
    ;

    @Override
    public ObjectType getObjectType() {
        return ObjectType.SENSOR;
    }
}
