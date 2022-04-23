package com.tnig.game.model.models.blocks;

import com.tnig.game.model.models.interfaces.ModelType;
import com.tnig.game.model.models.ObjectType;

public enum BlockType implements ModelType {
    NORMAL_BLOCK, INVISIBLE_BLOCK;

    @Override
    public ObjectType getObjectType() {
        return ObjectType.BLOCK;
    }
}
