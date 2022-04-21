package com.tnig.game.model.models.coins;

import com.tnig.game.model.models.Model;
import com.tnig.game.model.models.ModelType;
import com.tnig.game.model.physics_engine.Engine;

public class CoinFactory {
    public static Model createModel(Engine engine, float x, float y, float width, float height, ModelType modelType){
        CoinType type = (CoinType) modelType;
        switch (type){
            case NORMAL_COIN:
                return new Coin(engine, x, y, width, height, 10000);
            case MOCK:
                throw new IllegalArgumentException("Not created model for Mock");
            default:
                throw new IllegalArgumentException("Not created model for type yet: " + type);
        }
    }
}
