package com.tnig.game.model.models;

import com.tnig.game.controller.managers.EventManager;
import com.tnig.game.model.models.enums.ObjectType;
import com.tnig.game.model.models.blocks.BlockFactory;
import com.tnig.game.model.models.coins.CoinFactory;
import com.tnig.game.model.models.interfaces.Model;
import com.tnig.game.model.models.interfaces.ModelType;
import com.tnig.game.model.models.obstacles.ObstacleFactory;
import com.tnig.game.model.models.players.PlayerFactory;
import com.tnig.game.model.models.sensors.SensorFactory;
import com.tnig.game.model.physics_engine.Engine;
import com.tnig.game.utilities.AssetLoader;

public class ModelFactory {

    public static Model createModel(EventManager eventManager,
                                    Engine engine, AssetLoader assetLoader,
                                    float x, float y, float width, float height,
                                    ObjectProperties properties, ModelType modelType){
        ObjectType type = modelType.getObjectType();
        switch (type){
            case OBSTACLE:
                return ObstacleFactory.createModel(eventManager, engine, assetLoader, x, y, width, height, properties, modelType);
            case PLAYER:
                return PlayerFactory.createModel(eventManager, engine, assetLoader, x, y, width, height, properties, modelType);
            case BLOCK:
                return BlockFactory.createModel(engine, assetLoader, x, y, width, height, properties, modelType);
            case COIN:
                return CoinFactory.createModel(eventManager, engine, assetLoader, x, y, width, height, properties, modelType);
            case SENSOR:
                return SensorFactory.createModel(eventManager, engine, assetLoader, x, y, width, height, properties, modelType);
            default:
                throw new IllegalArgumentException("Not created model for type yet: " + type);
        }
    }
}
