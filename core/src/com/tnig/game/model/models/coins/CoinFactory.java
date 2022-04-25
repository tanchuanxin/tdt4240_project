package com.tnig.game.model.models.coins;

import com.tnig.game.controller.managers.EventManager;
import com.tnig.game.model.models.ObjectProperties;
import com.tnig.game.model.models.interfaces.Model;
import com.tnig.game.model.models.interfaces.ModelType;
import com.tnig.game.model.physics_engine.Engine;
import com.tnig.game.utilities.AssetLoader;
import com.tnig.game.utilities.Constants;

public class CoinFactory {
    public static Model createModel(EventManager eventManager, Engine engine, AssetLoader assetLoader,
                                    float x, float y, float width, float height,
                                    ObjectProperties properties, ModelType modelType){
        CoinType type = (CoinType) modelType;
        switch (type){
            case NORMAL_COIN:
                return new Coin(eventManager, engine, assetLoader, x, y, width, height, properties, type, Constants.normalCoinValue);
            case MOCK_COIN:
                throw new IllegalArgumentException("Not created model for mock coin");
            default:
                throw new IllegalArgumentException("Not created model for type yet: " + type);
        }
    }
}
