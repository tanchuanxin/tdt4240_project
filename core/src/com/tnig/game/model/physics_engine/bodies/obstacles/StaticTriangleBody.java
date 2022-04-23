package com.tnig.game.model.physics_engine.bodies.obstacles;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.tnig.game.model.physics_engine.bodies.BodyBuilder;

public class StaticTriangleBody extends BodyBuilder {

    // Singleton pattern
    private static final StaticTriangleBody INSTANCE = new StaticTriangleBody();

    public static StaticTriangleBody getInstance() {
        return INSTANCE;
    }
    private StaticTriangleBody() {
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
