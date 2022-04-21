package com.tnig.game.model.models;

import com.badlogic.gdx.physics.box2d.Body;

public interface Model {
    void update(float delta);
    boolean isDisposable();
    Body getBody();
    float getX();
    float getY();
    float getWidth();
    float getHeight();
    ModelType getType();
    String getLayer();

}
