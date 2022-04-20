package com.tnig.game.model.physics_engine;

import static com.tnig.game.utilities.Constants.PPM;
import static com.tnig.game.utilities.Constants.VIEWPORT_HEIGHT;
import static com.tnig.game.utilities.Constants.VIEWPORT_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.tnig.game.controller.map.GameMap;
import com.tnig.game.model.models.Model;

/**
 * This class encapsulates the Box2D world, decoupling it from the rest of the project
 */
public class GameWorld implements Engine{

    private final World world;
    private final Box2DDebugRenderer b2dr;
    private final OrthographicCamera b2drCam;

    public GameWorld(GameMap map) {
        // Initialize Box2D World
        world = new World(new Vector2(0,-10), true);
        world.setContactListener(new WorldContactListener());
        b2dr = new Box2DDebugRenderer();

        // Use this camera for debugging in the desktop version
        // Set up game camera and viewport
        b2drCam = new OrthographicCamera(
                Gdx.graphics.getWidth() / PPM,
                Gdx.graphics.getHeight() / PPM);


        b2drCam.position.set(map.getMapWidth()/2f, map.getMapHeight()/2f, 0);
    }

    @Override
    public void update(float delta) {
        world.step(delta, 6, 2);
        b2dr.render(world, b2drCam.combined);
        b2drCam.update();
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
