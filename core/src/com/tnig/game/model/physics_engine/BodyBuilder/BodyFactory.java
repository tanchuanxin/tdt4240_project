package com.tnig.game.model.physics_engine.BodyBuilder;

import com.badlogic.gdx.physics.box2d.Body;
import com.tnig.game.model.models.Model;
import com.tnig.game.model.models.ModelType;
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
     * @param model The model which should contain the body
     */
    public Body createBody(Engine engine, Model model){
        ModelType type = model.getType();

        switch (type){
            case PLAYER:
                return PlayerBody.getInstance().createBody(engine, model);
            case OBSTACLE:
                return ObstacleBody.getInstance().createBody(engine, model);
            default:
                throw new IllegalArgumentException("Type doesnt exist:" + type);


        }
    }
}
