package com.tnig.game.controller.game;

import com.badlogic.gdx.Gdx;
import com.tnig.game.controller.managers.GameManager;
import com.tnig.game.controller.game_objects.AnimatedController;
import com.tnig.game.controller.game_objects.ObstacleController;
import com.tnig.game.model.models.obstacles.ObstacleType;
import com.tnig.game.model.physics_engine.Engine;
import com.tnig.game.view.GameRenderer;

import java.util.ArrayList;
import java.util.List;

/**
 * This class initializes all the objects in the game and stores them in a list
 */
//TODO: FINISH CLASS
public class NormalGame implements GameInitializer {
    private final List<AnimatedController> controllers = new ArrayList<>();

    @Override
    public GameManager initGame(final Engine engine, GameRenderer gameRenderer) {
        Gdx.app.log("GameManager", "init Game");
        initObstacleControllers(engine);
        initBlockControllers(engine);
        initPlayerControllers(engine);
        return new GameManager(engine, controllers, gameRenderer);

    }


    private void initObstacleControllers(Engine engine){
        // TODO: IMPLEMENT
        // Mockobject
        Gdx.app.log("GameManager", "init obstacles");
        AnimatedController obstacle =
                new ObstacleController(engine, 100f, 100f, 30f, 30f, ObstacleType.STATIC_TRIANGLE);

        controllers.add(obstacle);

    }


    private void initPlayerControllers(Engine engine){
        // TODO: IMPLEMENT
    }


    private void initBlockControllers(Engine engine){
        // TODO: IMPLEMENT
    }




}
