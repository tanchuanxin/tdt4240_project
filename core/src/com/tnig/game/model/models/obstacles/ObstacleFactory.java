package com.tnig.game.model.models.obstacles;

import com.tnig.game.model.models.Model;
import com.tnig.game.model.physics_engine.Engine;

public class ObstacleFactory {

    public static Model createObstacle(Engine engine, float x, float y, float width, float height, ObstacleType type){
        switch (type){
            case STATIC_TRIANGLE:
                return new Static_Triangle(engine, x, y, width, height);
            case MOCK_TYPE:
                throw new IllegalArgumentException("Not created model for Mock");
            default:
                throw new IllegalArgumentException("Not created model for type yet: " + type);
        }
    }
}
