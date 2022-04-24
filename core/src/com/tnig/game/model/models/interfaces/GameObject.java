package com.tnig.game.model.models.interfaces;

import com.tnig.game.model.models.ObjectProperties;
import com.tnig.game.model.models.enums.BodyType;
import com.tnig.game.model.models.enums.ObjectShape;

public interface GameObject {
    float getWidth();
    float getHeight();
    BodyType getBodyType();
    boolean isSensor();
    ObjectProperties getProperties();
    ModelType getType();
    ObjectShape GetShapeType();

}
