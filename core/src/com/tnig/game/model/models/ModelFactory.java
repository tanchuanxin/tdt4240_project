package com.tnig.game.model.models;

import com.tnig.game.model.models.obstacles.ObstacleFactory;
import com.tnig.game.model.models.players.PlayerFactory;
import com.tnig.game.model.physics_engine.Engine;

public class ModelFactory {

    public static Model createModel(Engine engine, float x, float y, float width, float height, ModelType modelType){
        ObjectType type = modelType.getObjectType();
        switch (type){
            case OBSTACLE:
                return ObstacleFactory.createModel(engine, x, y, width, height, modelType);
            case PLAYER:
                return PlayerFactory.createModel(engine, x, y, width, height, modelType);
            case BLOCK:
                throw new IllegalArgumentException("Not created model for BLOCK");
            default:
                throw new IllegalArgumentException("Not created model for type yet: " + type);
        }
    }
}
