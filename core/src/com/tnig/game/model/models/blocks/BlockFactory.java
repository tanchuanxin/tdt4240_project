package com.tnig.game.model.models.blocks;

import com.tnig.game.model.models.Model;
import com.tnig.game.model.models.ModelType;
import com.tnig.game.model.models.obstacles.ObstacleType;
import com.tnig.game.model.models.obstacles.Spike;
import com.tnig.game.model.physics_engine.Engine;

public class BlockFactory {
    public static Model createModel(Engine engine, String layer, float x, float y, float width, float height, ModelType modelType){
        BlockType type = (BlockType) modelType;
        switch (type){
            case NORMAL_BLOCK:
                return new NormalBlock(engine, layer, x, y, width, height);
            case MOCK:
                throw new IllegalArgumentException("Not created model for Mock");
            default:
                throw new IllegalArgumentException("Not created model for type yet: " + type);
        }
    }
}
