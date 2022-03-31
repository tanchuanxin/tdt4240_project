package com.tnig.game.model.physics_engine.BodyBuilder;

import static com.tnig.game.utillities.Constants.PPM;


import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.tnig.game.model.models.GameObject;
import com.tnig.game.model.physics_engine.Engine;

/**
 * A template class for creating different types of bodies in the box2D world
 */
public abstract class BodyBuilder {

    // Template methods

    /**
     * Gets the shape of the specific body
     * @param object The object to create the shape from
     * @return A Box2D shape
     */
    protected abstract Shape getShape(GameObject object);

    /**
     * Makes the necessary changes to the body definition to that specific body
     * @param bodyDef The body definition
     */
    protected abstract void addToBodyDef(BodyDef bodyDef);

    /**
     * Makes the necessary changes to the fixture definition to that specific body
     * @param fixtureDef The fixture definition
     */
    protected abstract void addToFixtureDef(FixtureDef fixtureDef);

    /**
     * Template method for creating a Box2D body
     * @param engine The physics engine which contains the world
     * @param x The x-coordinate where the body should appear
     * @param y The y-coordinate where the body should appear
     * @param object The object which will contain the Box2D body
     * @return a Box2D body
     */
    protected Body createBody(Engine engine, float x, float y, GameObject object) {
        World world = engine.getWorld();
        Shape shape = getShape(object);
        boolean isStatic = object.isStatic();

        // Defines a Box2D body
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = isStatic ? BodyDef.BodyType.StaticBody : BodyDef.BodyType.DynamicBody;
        bodyDef.fixedRotation = true;

        // World units = meters, from world to screen -> Divide by Pixel Per Meter
        bodyDef.position.set(x / PPM, y / PPM);

        addToBodyDef(bodyDef);
        //Puts the body in the Box2D world
        Body body = world.createBody(bodyDef);

        // A body is composed of fixtures => defines a fixture to put in the body
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.isSensor = object.isSensor();

        fixtureDef.density = 1;
        fixtureDef.friction = 0;
        //fixtureDef.restitution = 0.01f;
        addToFixtureDef(fixtureDef);

        // Sets the model as userdata for the contactlistener
        body.createFixture(fixtureDef).setUserData(object);
        shape.dispose();

        return body;
    }
}
