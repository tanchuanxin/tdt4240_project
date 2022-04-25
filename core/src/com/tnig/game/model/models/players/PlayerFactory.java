package com.tnig.game.model.models.players;

import com.tnig.game.controller.managers.EventManager;
import com.tnig.game.model.models.ObjectProperties;
import com.tnig.game.model.models.interfaces.Model;
import com.tnig.game.model.models.interfaces.ModelType;
import com.tnig.game.model.physics_engine.Engine;
import com.tnig.game.utilities.AssetLoader;

public class PlayerFactory {
    public static Model createModel(EventManager eventManager,
                                    Engine engine, AssetLoader assetLoader,
                                    float x, float y, float width, float height,
                                    ObjectProperties properties, ModelType modelType){
        PlayerType type = (PlayerType) modelType;
        switch (type){
            case NORMAL_PLAYER:
                return new NormalPlayer(eventManager, engine, assetLoader, x, y, width, height, properties, type);
            case MOCK_PLAYER:
                throw new IllegalArgumentException("Not created model for Mock");
            default:
                throw new IllegalArgumentException("Not created model for type yet: " + type);
        }
    }
}
