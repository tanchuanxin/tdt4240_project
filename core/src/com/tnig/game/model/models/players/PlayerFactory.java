package com.tnig.game.model.models.players;

import com.tnig.game.controller.managers.EventManager;
import com.tnig.game.model.models.ObjectProperties;
import com.tnig.game.model.models.interfaces.Model;
import com.tnig.game.model.models.interfaces.ModelType;
import com.tnig.game.model.physics_engine.Engine;

public class PlayerFactory {
    public static Model createModel(EventManager eventManager,
                                    Engine engine,
                                    float x, float y, float width, float height,
                                    ObjectProperties properties, ModelType modelType){
        PlayerType type = (PlayerType) modelType;
        switch (type){
            case NORMALPLAYER:
                return new NormalPlayer(eventManager, engine, x, y, width, height, properties, type);
            case MOCK:
                throw new IllegalArgumentException("Not created model for Mock");
            default:
                throw new IllegalArgumentException("Not created model for type yet: " + type);
        }
    }
}
