package com.tnig.game.controller.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.tnig.game.controller.game_objects.Controller;
import com.tnig.game.controller.game_objects.dynamic_objects.AnimatedController;
import com.tnig.game.model.physics_engine.Engine;

import java.util.Iterator;
import java.util.List;

/**
 * Contains a list of all the controllers in the game, and contains the logic for updating and
 * removing controllers, while rendering the game.
 */
public class GameManager {

    private List<AnimatedController> animatedControllers;
    private List<Controller> controllers;
    private Engine engine;
    private TiledMap map;
    private final EventManager eventManager;
    private final int numberOfPlayers;
    private final AnimatedController player;

    public GameManager(EventManager eventManager,
                       Engine engine,
                       List<AnimatedController> animatedControllers,
                       List<Controller> controllers,
                       AnimatedController player,
                       TiledMap map,
                       int numberOfPlayers) {
        this.eventManager = eventManager;
        this.engine = engine;
        this.animatedControllers = animatedControllers;
        this.controllers = controllers;
        this.player = player;
        this.map = map;
        this.numberOfPlayers = numberOfPlayers;
    }

    /**
     * Updates all controllers in the world and removes those that are disposable.
     * @param delta timestep
     */
    public void update(float delta){
        // Uses iterator instead of for loop so it is possible to remove elements from the list
        // While iterating
        Iterator<AnimatedController> iterator = animatedControllers.iterator();
        while(iterator.hasNext()){
            AnimatedController controller = iterator.next();
            if (controller.isDisposable()){
                iterator.remove();
                engine.disposeModel(controller.getModel());
            }
            else {
                controller.update(delta);
            }
        }
    }



    /**
     * Checks if the game is finished
     * @return true if finished, false if not
     */
    public boolean gameFinished(){
        // TODO: IMPLEMENT
        return false;
    }

    public void dispose(){
        animatedControllers = null;
        controllers = null;
        engine = null;
        map = null;

    }

    public List<AnimatedController> getAnimatedControllers() {
        return animatedControllers;
    }

    public float getPlayerPosX() {
        Gdx.app.log("Player pos X:", String.valueOf(player.getModel().getX()));
        return player.getModel().getX();
    }

    public float getPlayerPosY() {
        Gdx.app.log("Player pos Y:", String.valueOf(player.getModel().getY()));
        return player.getModel().getY();
    }

    public List<Controller> getControllers() {
        return controllers;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }
}
