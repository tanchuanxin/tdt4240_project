package com.tnig.game.model.physics_engine;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.tnig.game.model.models.interfaces.Model;

/**
 * This class encapsulates the Box2D world, decoupling it from the rest of the project
 */
public class GameWorld implements Engine{
    private World world;
    private final Viewport viewport;
    private final int gravity = -10;

    public GameWorld(Viewport viewport) {
        // Initialize Box2D World
        initNewWorld();

        // Set up game camera and viewport
        this.viewport = viewport;
    }

    @Override
    public void update(float delta) {
        world.step(delta, 6, 2);
    }

    @Override
    public void initNewWorld() {
        world = new World(new Vector2(0, gravity), true);
        world.setContactListener(new WorldContactListener());
    }

    @Override
    public void dispose() {
        world.dispose();
    }

    @Override
    public void disposeModel(Model model) {
        world.destroyBody(model.getBody());
    }

    @Override
    public World getWorld() {
        return world;
    }
}
