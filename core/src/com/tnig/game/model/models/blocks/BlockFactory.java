package com.tnig.game.model.models.blocks;

import com.tnig.game.model.models.ObjectProperties;
import com.tnig.game.model.models.interfaces.Model;
import com.tnig.game.model.models.interfaces.ModelType;
import com.tnig.game.model.physics_engine.Engine;
import com.tnig.game.utilities.AssetLoader;

/**
 * creates blocks on the map
 */
public class BlockFactory {
    public static Model createModel(Engine engine, AssetLoader assetLoader,
                                    float x, float y, float width, float height,
                                    ObjectProperties properties, ModelType modelType){
        BlockType type = (BlockType) modelType;
        switch (type){
            case NORMAL_BLOCK:
                return new NormalBlock(engine, assetLoader, x, y, width, height, properties, type);
            case MOCK_BLOCK:
                throw new IllegalArgumentException("Not created model for type mock block yet");
            default:
                throw new IllegalArgumentException("Not created model for type yet: " + type);
        }
    }
}
