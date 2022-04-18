package com.tnig.game.model.models.players;

import com.tnig.game.model.models.Model;
import com.tnig.game.model.models.ModelType;
import com.tnig.game.model.physics_engine.Engine;

public class PlayerFactory {
    public static Model createModel(Engine engine, float x, float y, float width, float height, ModelType modelType){
        PlayerType type = (PlayerType) modelType;
        switch (type){
            case NORMALPLAYER:
                return new NormalPlayer(engine, x, y, width, height);
            case MOCK:
                throw new IllegalArgumentException("Not created model for Mock");
            default:
                throw new IllegalArgumentException("Not created model for type yet: " + type);
        }
    }
}
