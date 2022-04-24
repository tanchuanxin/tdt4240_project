package com.tnig.game.model.models.blocks;

import com.tnig.game.model.models.AbstractModel;
import com.tnig.game.model.models.ObjectProperties;
import com.tnig.game.model.models.interfaces.ContactObject;
import com.tnig.game.model.models.interfaces.ModelType;
import com.tnig.game.model.models.ObjectShape;
import com.tnig.game.model.physics_engine.Engine;

public class NormalBlock extends AbstractModel {
    private static final boolean isStatic = true;
    private static final boolean isSensor = false;

    private static final ObjectShape shape = ObjectShape.BOX;

    protected NormalBlock(Engine engine,
                          float x, float y, float width, float height,
                          ObjectProperties properties, ModelType type) {
        super(engine, x, y, width, height, properties, isStatic, isSensor, type);
    }

    @Override
    public void handleBeginContact(ContactObject object) {

    }

    @Override
    public ObjectShape GetShapeType() {
        return shape;
    }
}
