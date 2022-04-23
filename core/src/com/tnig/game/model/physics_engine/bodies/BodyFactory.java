package com.tnig.game.model.physics_engine.bodies;

import com.badlogic.gdx.physics.box2d.Body;
import com.tnig.game.model.models.interfaces.GameObject;
import com.tnig.game.model.models.ObjectType;
import com.tnig.game.model.physics_engine.bodies.blocks.NormalBlockBody;
import com.tnig.game.model.physics_engine.bodies.obstacles.StaticTriangleBody;
import com.tnig.game.model.physics_engine.Engine;
import com.tnig.game.model.physics_engine.bodies.players.PlayerBody;

/**
 * A factory inspired class which creates and sets the model's body to its type.
 */
public class BodyFactory {

    // Singleton pattern
    private static final BodyFactory INSTANCE = new BodyFactory();

    public static BodyFactory getInstance() {
        return INSTANCE;
    }
    private BodyFactory() {
    }

    /**
     * Makes a body for the model instance and set it using the model.setBody() method
     * @param engine The physics engine used
     * @param x The x-coordinate where the body should appear
     * @param y The y-coordinate where the body should appear
     * @param object The object which should contain the body
     */
    public Body createBody(Engine engine, float x, float y, GameObject object){
        ObjectType type = object.getType().getObjectType();

        switch (type){
            case PLAYER:
                return PlayerBody.getInstance().createBody(engine, x, y, object);
            case OBSTACLE:
                return StaticTriangleBody.getInstance().createBody(engine, x, y, object);
            case BLOCK:
                return NormalBlockBody.getInstance().createBody(engine, x, y, object);
            case COIN:
                return NormalBlockBody.getInstance().createBody(engine, x, y, object);
            case SENSOR:
                return NormalBlockBody.getInstance().createBody(engine, x, y, object);
            default:
                throw new IllegalArgumentException("Type doesnt exist: " + type);


        }
    }
}
