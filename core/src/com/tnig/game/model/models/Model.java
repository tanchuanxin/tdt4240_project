package com.tnig.game.model.models;

import com.badlogic.gdx.physics.box2d.Body;

public interface Model {
    void update(float delta);
    boolean isDisposable();
    Body getBody();
}
