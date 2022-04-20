package com.tnig.game.model.models;

public interface Movable {
    void applyForceToCenter(float forceX, float forceY);
    void setLinearVelocity(int xDir, int yDir);
}
