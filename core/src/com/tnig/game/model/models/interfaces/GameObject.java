package com.tnig.game.model.models.interfaces;

import com.tnig.game.model.models.ObjectProperties;
import com.tnig.game.model.models.ObjectShape;

public interface GameObject {
    float getWidth();
    float getHeight();
    boolean isStatic();
    boolean isSensor();
    ObjectProperties getProperties();
    ModelType getType();
    ObjectShape GetShapeType();

}
