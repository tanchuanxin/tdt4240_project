package com.tnig.game.model.models;

public interface GameObject {
    float getWidth();
    float getHeight();
    boolean isStatic();
    boolean isSensor();
    ModelType getType();

}
