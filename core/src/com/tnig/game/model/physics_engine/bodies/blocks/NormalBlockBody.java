package com.tnig.game.model.physics_engine.bodies.blocks;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.tnig.game.model.physics_engine.bodies.BodyBuilder;

public class NormalBlockBody extends BodyBuilder {

    // Singleton pattern
    private static final NormalBlockBody INSTANCE = new NormalBlockBody();

    public static NormalBlockBody getInstance() {
        return INSTANCE;
    }
    private NormalBlockBody() {
    }



    //@Override
    protected void addToBodyDef(BodyDef bodyDef) {
        // TODO: set bodydef
    }

    //@Override
    protected void addToFixtureDef(FixtureDef fixtureDef) {
        // TODO: Set filtering

    }
}
