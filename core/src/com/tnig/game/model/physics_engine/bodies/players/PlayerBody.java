package com.tnig.game.model.physics_engine.bodies.players;

import static com.tnig.game.utilities.Constants.PPM;


import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.tnig.game.model.models.GameObject;
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
    protected Shape getShape(GameObject object) {
        final PolygonShape shape = new PolygonShape();
        shape.setAsBox(object.getWidth() / 2 / PPM, object.getHeight() / 2 / PPM);
        return shape;
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
