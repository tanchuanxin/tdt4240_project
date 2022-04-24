package com.tnig.game.model.models.obstacles;

import com.tnig.game.controller.managers.EventManager;
import com.tnig.game.model.models.ObjectProperties;
import com.tnig.game.model.models.interfaces.Model;
import com.tnig.game.model.models.interfaces.ModelType;
import com.tnig.game.model.physics_engine.Engine;

public class ObstacleFactory {

    public static Model createModel(EventManager eventManager, Engine engine,
                                    float x, float y, float width, float height,
                                    ObjectProperties properties, ModelType modelType){
        ObstacleType type = (ObstacleType) modelType;
        switch (type){
            case SPIKE:
                return new Spike(eventManager, engine, x, y, width, height, properties, type);
            case FIREBALL:
                return new FireBall(engine, x, y, width, height, properties, type);
            default:
                throw new IllegalArgumentException("Not created model for type yet: " + type);
        }
    }
}
