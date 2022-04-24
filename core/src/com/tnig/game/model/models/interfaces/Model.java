package com.tnig.game.model.models.interfaces;

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
    float[] getLinearVelocity();
}
