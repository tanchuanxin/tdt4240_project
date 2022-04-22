package com.tnig.game.model.physics_engine.bodies.obstacles;

import static com.tnig.game.utilities.Constants.PPM;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.tnig.game.model.models.GameObject;
import com.tnig.game.model.physics_engine.bodies.BodyBuilder;

public class StaticTriangleBody extends BodyBuilder {

    // Singleton pattern
    private static final StaticTriangleBody INSTANCE = new StaticTriangleBody();

    public static StaticTriangleBody getInstance() {
        return INSTANCE;
    }
    private StaticTriangleBody() {
    }

    @Override
    protected Shape getShape(GameObject object) {
        //TODO: Implement Triangle shape
        final PolygonShape shape = new PolygonShape();
        shape.setAsBox(object.getWidth() / 2, object.getHeight() / 2);
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
