package com.tnig.game.model.models.blocks;

import com.tnig.game.model.models.ObjectProperties;
import com.tnig.game.model.models.interfaces.Model;
import com.tnig.game.model.models.interfaces.ModelType;
import com.tnig.game.model.physics_engine.Engine;

public class BlockFactory {
    public static Model createModel(Engine engine,
                                    float x, float y, float width, float height,
                                    ObjectProperties properties, ModelType modelType){
        BlockType type = (BlockType) modelType;
        switch (type){
            case NORMAL_BLOCK:
                return new NormalBlock(engine, x, y, width, height, properties, type);
            case INVISIBLE_BLOCK:
                throw new IllegalArgumentException("Not created model for type invisible block yet");
            default:
                throw new IllegalArgumentException("Not created model for type yet: " + type);
        }
    }
}
