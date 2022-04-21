package com.tnig.game.model.models.obstacles;

import com.tnig.game.model.models.Model;
import com.tnig.game.model.models.ModelType;
import com.tnig.game.model.physics_engine.Engine;

public class ObstacleFactory {

    public static Model createModel(Engine engine, String layer,
                                    float x, float y, float width, float height,
                                    ModelType modelType){
        ObstacleType type = (ObstacleType) modelType;
        switch (type){
            case SPIKE:
                return new Spike(engine, layer, x, y, width, height);
            case MOCK_TYPE:
                throw new IllegalArgumentException("Not created model for Mock");
            default:
                throw new IllegalArgumentException("Not created model for type yet: " + type);
        }
    }
}
