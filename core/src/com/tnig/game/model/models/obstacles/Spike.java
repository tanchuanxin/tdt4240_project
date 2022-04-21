package com.tnig.game.model.models.obstacles;

import com.tnig.game.model.models.AbstractModel;
import com.tnig.game.model.models.ContactObject;
import com.tnig.game.model.models.ModelType;
import com.tnig.game.model.models.ObjectShape;
import com.tnig.game.model.physics_engine.Engine;

public class Spike extends AbstractModel {

    private static final boolean isStatic = true;
    private static final boolean isSensor = false;
    private static final ObjectShape shape = ObjectShape.BOX;

    protected Spike(Engine engine, float x, float y, float width, float height, ModelType type) {
        super(engine, x, y, width, height, isStatic, isSensor, type);
    }

    @Override
    public void handleBeginContact(ContactObject object) {

    }


    @Override
    public ObjectShape GetShape() {
        return shape;
    }
}
