package com.tnig.game.model.physics_engine.bodies.players;


import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.tnig.game.model.physics_engine.bodies.BodyBuilder;

public class PlayerBody extends BodyBuilder {

    // Singleton pattern
    private static final PlayerBody INSTANCE = new PlayerBody();

    public static PlayerBody getInstance() {
        return INSTANCE;
    }
    private PlayerBody() {
    }


    @Override
    protected void addToBodyDef(BodyDef bodyDef) {
        // TODO: set bodydef

    }

    @Override
    protected void addToFixtureDef(FixtureDef fixtureDef) {
        // TODO: Set filtering

    }


}
