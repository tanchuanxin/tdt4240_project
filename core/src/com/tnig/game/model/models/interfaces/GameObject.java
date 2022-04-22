package com.tnig.game.model.models.interfaces;

import com.tnig.game.model.models.ObjectShape;

public interface GameObject {
    float getWidth();
    float getHeight();
    boolean isStatic();
    boolean isSensor();
    ModelType getEnum();
    ObjectShape GetShapeType();

}
