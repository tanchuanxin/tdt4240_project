package com.tnig.game.model.models;

import com.tnig.game.model.physics_engine.Engine;
import com.tnig.game.model.physics_engine.GameWorld;

public class PlayerModel extends AbstractModel {

    public PlayerModel(Engine engine, float x, float y, float width, float height, boolean isStatic, boolean isSensor, ObjectType type) {
        super(engine, x, y, width, height, isStatic, isSensor, type);
    }

    @Override
    public void handleBeginContact(ContactObject object) {

    }
}
