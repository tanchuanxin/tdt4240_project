package com.tnig.game.model.physics_engine;

import static com.tnig.game.utillities.Constants.PPM;
import static com.tnig.game.utillities.Constants.VIEWPORT_HEIGHT;
import static com.tnig.game.utillities.Constants.VIEWPORT_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.tnig.game.model.models.Model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This class encapsulates the Box2D world, decoupling it from the rest of the project
 */
public class GameWorld implements Engine{

    private final World world;
    private final Box2DDebugRenderer b2dr;
    private final OrthographicCamera b2drCam;

    public GameWorld() {
        // Initialize Box2D World
        world = new World(new Vector2(0,-10), true);
        world.setContactListener(new WorldContactListener());
        b2dr = new Box2DDebugRenderer();

        // Use this camera for debugging in the desktop version
        b2drCam = new OrthographicCamera(VIEWPORT_WIDTH / PPM, VIEWPORT_HEIGHT / PPM);
        // TODO: Find out where to put b2dr cam
        b2drCam.position.set(Gdx.graphics.getWidth()/ 2f / PPM, Gdx.graphics.getHeight() / 2f / PPM, 0);
        b2drCam.update();
    }

    @Override
    public void update(float delta) {
        world.step(delta, 6, 2);
        b2dr.render(world, b2drCam.combined);
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
