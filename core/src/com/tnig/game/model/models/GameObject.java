package com.tnig.game.model.models;

public interface GameObject {
    float getWidth();
    float getHeight();
    boolean isStatic();
    boolean isSensor();
    ObjectType getType();
}
