package com.tnig.game.model.models.sensors;

import com.tnig.game.model.models.AbstractModel;
import com.tnig.game.model.models.ObjectProperties;
import com.tnig.game.model.models.enums.BodyType;
import com.tnig.game.model.models.interfaces.ModelType;
import com.tnig.game.model.models.enums.ObjectShape;
import com.tnig.game.model.physics_engine.Engine;

public abstract class Sensor extends AbstractModel {
    private static final boolean isSensor = true;
    private static final ObjectShape shape = ObjectShape.BOX;

    protected Sensor(Engine engine, BodyType bodyType,
                     float x, float y, float width, float height,
                     ObjectProperties properties, ModelType type) {
        super(engine, x, y, width, height, properties, bodyType, isSensor, type);
    }

    @Override
    public ObjectShape GetShapeType() {
        return shape;
    }
}
