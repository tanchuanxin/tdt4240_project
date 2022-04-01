package com.tnig.game.controller.managers;

import com.tnig.game.controller.game_objects.AnimatedController;
import com.tnig.game.model.physics_engine.Engine;
import com.tnig.game.view.GameRenderer;

import java.util.Iterator;
import java.util.List;

/**
 * Contains a list of all the controllers in the game, and contains the logic for updating and
 * removing controllers, while rendering the game.
 */
public class GameManager {

    private List<AnimatedController> controllers;
    private Engine engine;
    private GameRenderer gameRenderer;

    public GameManager(final Engine engine, final List<AnimatedController> controllers, GameRenderer gameRenderer) {
        this.engine = engine;
        this.controllers = controllers;
        this.gameRenderer = gameRenderer;
    }

    /**
     * Updates all controllers in the world and removes those that are disposable.
     * @param delta timestep
     */
    public void update(float delta){
        // Uses iterator instead of for loop so it is possible to remove elements from the list
        // While iterating
        Iterator<AnimatedController> iterator = controllers.iterator();
        while(iterator.hasNext()){
            AnimatedController controller = iterator.next();
            if (controller.isDisposable()){
                iterator.remove();
                engine.disposeModel(controller.getModel());

            }
            else{
                controller.update(delta);
            }
        }
    }

    /**
     * Renders all the Animated views in the game
     */
    public void renderAnimatedViews() {
        for (AnimatedController controller: controllers) {
            gameRenderer.render(controller.getView());
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
        controllers = null;
        engine = null;
    }

}
