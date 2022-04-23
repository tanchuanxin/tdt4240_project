package com.tnig.game.model.models.blocks;

import com.tnig.game.model.models.interfaces.Model;
import com.tnig.game.model.models.interfaces.ModelType;
import com.tnig.game.model.physics_engine.Engine;

public class BlockFactory {
    public static Model createModel(Engine engine,
                                    float x, float y, float width, float height, float rotation,
                                    ModelType modelType){
        BlockType type = (BlockType) modelType;
        switch (type){
            case NORMAL_BLOCK:
                return new NormalBlock(engine, x, y, width, height, rotation, type);
            case MOCK:
                throw new IllegalArgumentException("Not created model for Mock");
            default:
                throw new IllegalArgumentException("Not created model for type yet: " + type);
        }
    }
}
