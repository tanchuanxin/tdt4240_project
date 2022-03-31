package com.tnig.game.model.physics_engine.BodyBuilder;

import com.badlogic.gdx.physics.box2d.Body;
import com.tnig.game.model.models.GameObject;
import com.tnig.game.model.models.ObjectType;
import com.tnig.game.model.physics_engine.Engine;

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
     * @param object The object which should contain the body
     */
    public Body createBody(Engine engine, GameObject object){
        ObjectType type = object.getType();

        switch (type){
            case PLAYER:
                return PlayerBody.getInstance().createBody(engine, object);
            case OBSTACLE:
                return ObstacleBody.getInstance().createBody(engine, object);
            default:
                throw new IllegalArgumentException("Type doesnt exist:" + type);


        }
    }
}
