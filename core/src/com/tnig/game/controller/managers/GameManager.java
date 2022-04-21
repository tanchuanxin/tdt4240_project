package com.tnig.game.controller.managers;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.tnig.game.controller.events.Event;
import com.tnig.game.controller.events.EventListener;
import com.tnig.game.controller.events.EventName;
import com.tnig.game.controller.game.GameInitializer;
import com.tnig.game.controller.game_objects.Controller;
import com.tnig.game.controller.game_objects.dynamic_objects.AnimatedController;
import com.tnig.game.model.physics_engine.Engine;

import java.util.Iterator;
import java.util.List;

/**
 * Contains a list of all the controllers in the game, and contains the logic for updating and
 * removing controllers, while rendering the game.
 */
public class GameManager implements EventListener {

    private List<AnimatedController> animatedControllers;
    private List<Controller> controllers;
    private Engine engine;
    private final EventManager eventManager;
    private final int numberOfPlayers;
    private final AnimatedController player;
    private State STATE;

    public enum State{
        PLAYING,
        NEW_GAME,
        GAME_OVER
    }

    public GameManager(EventManager eventManager,
                       Engine engine,
                       GameInitializer initializer,
                       int numberOfPlayers) {
        this.eventManager = eventManager;
        this.engine = engine;
        this.numberOfPlayers = numberOfPlayers;
        animatedControllers = initializer.getAnimatedControllers();
        controllers = initializer.getControllers();
        player = initializer.getPlayer();

        eventManager.subscribe(EventName.PLAYER_DEAD, this);
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





    public void dispose(){
        animatedControllers = null;
        controllers = null;
        engine = null;

    }

    public List<AnimatedController> getAnimatedControllers() {
        return animatedControllers;
    }

    public float getPlayerPosX() {
        return player.getModel().getX();
    }

    public float getPlayerPosY() {
        return player.getModel().getY();
    }

    public List<Controller> getControllers() {
        return controllers;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    @Override
    public void receiveEvent(Event event) {
        switch (event.name){
            case PLAYER_DEAD:
        }
    }
}
