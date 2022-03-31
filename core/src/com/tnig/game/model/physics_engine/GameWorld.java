package com.tnig.game.model.physics_engine;

import static com.tnig.game.utillities.Constants.PPM;
import static com.tnig.game.utillities.Constants.VIEWPORT_HEIGHT;
import static com.tnig.game.utillities.Constants.VIEWPORT_WIDTH;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.tnig.game.model.models.Model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameWorld implements Engine{

    private final List<Model> models = new ArrayList<>();

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
        world.step(delta, 6, 2);

        updateGameObjects(delta);

    }

    /**
     * Updates all gameobjects in the world and removes those that are disposable.
     * @param delta timestep
     */
    private void updateGameObjects(float delta){
        // Uses iterator instead of for loop so it is possible to remove elements from the list
        // While iterating
        Iterator<Model> iterator = models.iterator();
        while(iterator.hasNext()){
            Model obj = iterator.next();
            if (obj.isDisposable()){
                world.destroyBody(obj.getBody());
                iterator.remove();
            }
            else{
                obj.update(delta);
            }
        }
    }

    @Override
    public boolean gameFinished() {
        //TODO: IMPLEMENT
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
