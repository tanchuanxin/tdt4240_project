package com.tnig.game.model.physics_engine;

public interface Engine {

    /**
     * Updates the engine with a time-step
     * @param delta time-step
     */
    void update(float delta);

    /**
     * Checks if the game is finished
     * @return true if finished, false if not
     */
    boolean gameFinished();

    /**
     * Disposes the engine when it is not needed anymore
     */
    void dispose();
}
