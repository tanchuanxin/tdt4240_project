package com.tnig.game.controller.managers;

import com.tnig.game.controller.events.Event;
import com.tnig.game.controller.events.EventListener;
import com.tnig.game.controller.events.EventName;
import com.tnig.game.controller.game.GameInitializer;
import com.tnig.game.controller.game_objects.Controller;
import com.tnig.game.controller.game_objects.dynamic_objects.AnimatedController;
import com.tnig.game.controller.map.GameMap;
import com.tnig.game.model.models.Model;
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
    private final GameMap map;
    private State STATE;

    public enum State{
        PLAYING,
        NEW_GAME,
        GAME_OVER
    }

    public GameManager(EventManager eventManager,
                       Engine engine,
                       GameInitializer initializer,
                       GameMap map,
                       int numberOfPlayers) {
        this.eventManager = eventManager;
        this.engine = engine;
        this.numberOfPlayers = numberOfPlayers;
        this.map = map;
        animatedControllers = initializer.getAnimatedControllers();
        controllers = initializer.getControllers();
        player = initializer.getPlayer();

        eventManager.subscribe(EventName.PLAYER_DEAD, this);
        eventManager.subscribe(EventName.DISPOSE_OBJECT, this);
    }

    /**
     * Updates all controllers in the world and removes those that are disposable.
     * @param delta timestep
     */
    public void update(float delta){
        // Uses iterator2 instead of for loop so it is possible to remove elements from the list
        // While iterating


        Iterator<Controller> iterator = controllers.iterator();
        while(iterator.hasNext()){
            Controller controller = iterator.next();
            if (controller.isDisposable()){
                iterator.remove();
                engine.disposeModel(controller.getModel());
            }
            else {
                controller.update(delta);
            }
        }

        // Update animatied controllers
        Iterator<AnimatedController> iterator2 = animatedControllers.iterator();
        while(iterator2.hasNext()){
            AnimatedController controller = iterator2.next();
            if (controller.isDisposable()){
                iterator2.remove();
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
                STATE = State.NEW_GAME;
                break;
            case DISPOSE_OBJECT:
                Model model = (Model) event.data.get("object");
                map.disposeTile((int) model.getX(),(int) model.getY());
        }
    }
}
