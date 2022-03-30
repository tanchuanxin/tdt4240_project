package com.tnig.game.model.physics_engine;

import static com.tnig.game.utillities.Constants.PPM;
import static com.tnig.game.utillities.Constants.VIEWPORT_HEIGHT;
import static com.tnig.game.utillities.Constants.VIEWPORT_WIDTH;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class GameWorld implements Engine{

    private final World world;
    private final Box2DDebugRenderer b2dr;
    private final OrthographicCamera b2drCam;

    public GameWorld() {
        // Initialize Box2D World. Set Gravity 0 and 'not simulate inactive objects' true
        world = new World(new Vector2(0,0), true);
        world.setContactListener(new WorldContactListener());
        b2dr = new Box2DDebugRenderer();
        // TODO: Same camera for b2dr and Game? Maybe have camera as parameter?
        b2drCam = new OrthographicCamera(VIEWPORT_WIDTH / PPM, VIEWPORT_HEIGHT / PPM);
        // TODO: Find out where to put b2dr cam
        //b2drCam.position.set();
        b2drCam.update();
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public boolean gameFinished() {
        return false;
    }

    @Override
    public void dispose() {
        world.dispose();

    }

    @Override
    public World getWorld() {
        return world;
    }
}
