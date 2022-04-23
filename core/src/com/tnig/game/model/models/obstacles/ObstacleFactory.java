package com.tnig.game.model.models.obstacles;

import com.tnig.game.controller.managers.EventManager;
import com.tnig.game.model.models.interfaces.Model;
import com.tnig.game.model.models.interfaces.ModelType;
import com.tnig.game.model.physics_engine.Engine;

public class ObstacleFactory {

    public static Model createModel(EventManager eventManager, Engine engine,
                                    float x, float y, float width, float height, float rotation,
                                    ModelType modelType){
        ObstacleType type = (ObstacleType) modelType;
        switch (type){
            case SPIKE:
                return new Spike(eventManager, engine, x, y, width, height, rotation, type);
            case MOCK_TYPE:
                throw new IllegalArgumentException("Not created model for Mock");
            default:
                throw new IllegalArgumentException("Not created model for type yet: " + type);
        }
    }
}
