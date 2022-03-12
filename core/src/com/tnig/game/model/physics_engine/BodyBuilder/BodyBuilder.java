package com.tnig.game.model.physics_engine.BodyBuilder;

import static com.tnig.game.utillities.Constants.PPM;


import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.tnig.game.model.physics_engine.GameObject;

/**
 * A template class for creating different types of bodies in the box2D world
 */
public abstract class BodyBuilder {

    // Template methods

    /**
     * Checks whether the body is a sensor or not.
     * A sensor doesnt have a physical body but will engage the contact listener if touched by
     * another body
     * @return True if sensor, false if not
     */
    protected abstract boolean isSensor();

    /**
     * Checks whether the body is static or dynamic
     * @return True if static, false if dynamic
     */
    protected abstract boolean isStatic();

    /**
     * Gets the shape of the specific body
     * @return A Box2D shape
     */
    protected abstract Shape getShape();

    /**
     * Makes the necessary changes to the body definition to that specific body
     * @param bodyDef The body definition
     */
    protected abstract void setBodyDef(BodyDef bodyDef);

    /**
     * Makes the necessary changes to the fixture definition to that specific body
     * @param fixtureDef The fixture definition
     */
    protected abstract void setFixtureDef(FixtureDef fixtureDef);

    /**
     *
     * @param world The Box2D world
     * @param gameObject The gameobject which contains the Box2D body
     * @return a Box2D body
     */
    protected Body createBody(World world, GameObject gameObject) {
        // TODO: Find out if method should be void and instead use a gameObject.setBody(body) method at the end
        float x = (float) gameObject.getPosition().getX();
        float y = (float) gameObject.getPosition().getY();
        Shape shape = getShape();
        boolean isStatic = isStatic();
        boolean isSensor = isSensor();

        // Defines a Box2D body
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = isStatic ? BodyDef.BodyType.StaticBody : BodyDef.BodyType.DynamicBody;
        bodyDef.fixedRotation = true;

        // World units = meters, from world to screen -> Divide by Pixel Per Meter
        bodyDef.position.set(x / PPM, y / PPM);

        setBodyDef(bodyDef);
        //Puts the body in the Box2D world
        Body body = world.createBody(bodyDef);

        // A body is composed of fixtures => defines a fixture to put in the body
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.isSensor = isSensor;

        fixtureDef.density = 1;
        fixtureDef.friction = 0;
        //fixtureDef.restitution = 0.01f;
        setFixtureDef(fixtureDef);

        // Sets the gameobjects contactobject as userdata for the contactlistener
        body.createFixture(fixtureDef).setUserData(gameObject.getContactObject());
        shape.dispose();

        return body;
    }
}
