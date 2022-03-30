package com.tnig.game.model.physics_engine.BodyBuilder;

import static com.tnig.game.utillities.Constants.PPM;


import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.tnig.game.model.models.Model;

/**
 * A template class for creating different types of bodies in the box2D world
 */
public abstract class BodyBuilder {

    // Template methods

    /**
     * Gets the shape of the specific body
     * @param model The model to create the shape from
     * @return A Box2D shape
     */
    protected abstract Shape getShape(Model model);

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
     * @param world The Box2D world
     * @param model The model which contains the Box2D body
     * @return a Box2D body
     */
    protected Body createBody(World world, Model model) {
        float x = model.getX();
        float y = model.getY();
        Shape shape = getShape(model);
        boolean isStatic = model.isStatic();

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
        fixtureDef.isSensor = model.isSensor();

        fixtureDef.density = 1;
        fixtureDef.friction = 0;
        //fixtureDef.restitution = 0.01f;
        addToFixtureDef(fixtureDef);

        // Sets the model as userdata for the contactlistener
        body.createFixture(fixtureDef).setUserData(model);
        shape.dispose();

        return body;
    }
}
