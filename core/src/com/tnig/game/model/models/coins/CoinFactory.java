package com.tnig.game.model.models.coins;

import com.tnig.game.controller.managers.EventManager;
import com.tnig.game.model.models.interfaces.Model;
import com.tnig.game.model.models.interfaces.ModelType;
import com.tnig.game.model.physics_engine.Engine;

public class CoinFactory {
    public static Model createModel(EventManager eventManager, Engine engine,
                                    float x, float y, float width, float height, ModelType modelType){
        CoinType type = (CoinType) modelType;
        switch (type){
            case NORMAL_COIN:
                return new Coin(eventManager, engine, x, y, width, height, type, 10000);
            case MOCK:
                throw new IllegalArgumentException("Not created model for Mock");
            default:
                throw new IllegalArgumentException("Not created model for type yet: " + type);
        }
    }
}
