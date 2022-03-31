package com.tnig.game.model.models.obstacles;

import com.tnig.game.model.models.AbstractModel;
import com.tnig.game.model.models.ContactObject;
import com.tnig.game.model.models.ObjectType;
import com.tnig.game.model.physics_engine.Engine;

public class Static_Triangle extends AbstractModel {

    private static final boolean isStatic = true;
    private static final boolean isSensor = false;
    private static final ObjectType type = ObjectType.OBSTACLE;

    protected Static_Triangle(Engine engine, float width, float height) {
        super(engine, width, height, isStatic, isSensor, type);
    }

    @Override
    public void handleBeginContact(ContactObject object) {

    }
}
