package com.tnig.game.model.physics_engine.BodyBuilder;

import com.tnig.game.model.models.Model;
import com.tnig.game.model.models.ModelType;
import com.tnig.game.model.physics_engine.Engine;

public class BodyFactory {

    // Singleton pattern
    private static final BodyFactory INSTANCE = new BodyFactory();

    public static BodyFactory getInstance() {
        return INSTANCE;
    }
    private BodyFactory() {
    }

    public void createBody(Engine engine, Model model){
        ModelType type = model.getType();

        switch (type){
            case PLAYER:
                PlayerBody.getInstance().createBody(engine, model);
            case OBSTACLE:
                ObstacleBody.getInstance().createBody(engine, model);
            default:
                throw new IllegalArgumentException("Type doesnt exist:" + type);


        }
    }
}
