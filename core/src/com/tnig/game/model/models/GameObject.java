package com.tnig.game.model.models;

import com.badlogic.gdx.physics.box2d.Body;

public interface GameObject {
    void update(float delta);
    boolean isDisposable();
    Body getBody();
}
