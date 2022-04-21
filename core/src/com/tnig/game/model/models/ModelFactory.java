package com.tnig.game.model.models;

import com.tnig.game.controller.managers.EventManager;
import com.tnig.game.model.models.blocks.BlockFactory;
import com.tnig.game.model.models.coins.CoinFactory;
import com.tnig.game.model.models.obstacles.ObstacleFactory;
import com.tnig.game.model.models.players.PlayerFactory;
import com.tnig.game.model.models.sensors.SensorFactory;
import com.tnig.game.model.physics_engine.Engine;

public class ModelFactory {

    public static Model createModel(EventManager eventManager,
                                    Engine engine,
                                    float x, float y, float width, float height,
                                    ModelType modelType){
        ObjectType type = modelType.getObjectType();
        switch (type){
            case OBSTACLE:
                return ObstacleFactory.createModel(engine, x, y, width, height, modelType);
            case PLAYER:
                return PlayerFactory.createModel(eventManager, engine, x, y, width, height, modelType);
            case BLOCK:
                return BlockFactory.createModel(engine, x, y, width, height, modelType);
            case COIN:
                return CoinFactory.createModel(eventManager, engine, x, y, width, height, modelType);
            case SENSOR:
                return SensorFactory.createModel(eventManager, engine, x, y, width, height, modelType);
            default:
                throw new IllegalArgumentException("Not created model for type yet: " + type);
        }
    }
}
