package com.tnig.game.model.physics_engine;

import com.badlogic.gdx.physics.box2d.World;
import com.tnig.game.model.models.interfaces.Model;

public interface Engine {

    /**
     * Updates the engine with a time-step
     * @param delta time-step
     */
    void update(float delta);

    /**
     * Initializes a new world
     */
    void initNewWorld();

    /**
     * Disposes the engine when it is not needed anymore
     */
    void dispose();

    /**
     * Removes a body connected to a model from the world
     * @param model
     */
    void disposeModel(Model model);

    /**
     * @return The World of the engine
     */
    World getWorld();
}
