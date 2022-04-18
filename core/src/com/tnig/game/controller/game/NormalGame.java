package com.tnig.game.controller.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.tnig.game.controller.game_objects.Controller;
import com.tnig.game.controller.game_objects.dynamic_objects.PlayerController;
import com.tnig.game.controller.managers.GameManager;
import com.tnig.game.controller.game_objects.dynamic_objects.AnimatedController;
import com.tnig.game.controller.game_objects.static_objects.ObstacleController;
import com.tnig.game.model.models.obstacles.ObstacleFactory;
import com.tnig.game.model.models.obstacles.ObstacleType;
import com.tnig.game.model.physics_engine.Engine;
import com.tnig.game.utilities.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * This class initializes all the objects in the game and stores them in a list to pass to the
 * GameManager
 */
//TODO: FINISH CLASS
public class NormalGame implements GameInitializer {
    private final List<AnimatedController> animatedControllers = new ArrayList<>();
    private final List<Controller> controllers = new ArrayList<>();


    @Override
    public GameManager initGame(final Engine engine, TiledMap map, int players) {
        Gdx.app.log("GameManager", "init Game");
        initObstacleControllers(engine, map);
        initBlockControllers(engine, map);
        initPlayerControllers(engine, map);
        return new GameManager(engine, animatedControllers, controllers, map, players);

    }


    private void initObstacleControllers(Engine engine, TiledMap map){

        for (RectangleMapObject object : map.getLayers().get(Constants.obstacleLayer).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = object.getRectangle();

        }
        // TODO: IMPLEMENT
        // Mockobject
        Gdx.app.log("GameManager", "init obstacles");
        Controller obstacle =
                new ObstacleController(engine, 100f, 100f, 30f, 30f, ObstacleType.STATIC_TRIANGLE);

        controllers.add(obstacle);

    }


    private void initPlayerControllers(Engine engine, TiledMap map){
        for (RectangleMapObject object : map.getLayers().get(Constants.playerLayer).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = object.getRectangle();
            AnimatedController controller = new PlayerController(engine, rect.x, rect.y, rect.width, rect.height);
            animatedControllers.add(controller);

        }
    }


    private void initBlockControllers(Engine engine, TiledMap map){
        for (RectangleMapObject object : map.getLayers().get(Constants.blockLayer).getObjects().getByType(RectangleMapObject.class)) {
            // grab rectangles corresponding to object
            Rectangle rect = object.getRectangle();

            // create the tile
            new ObstacleFactory();
        }


    }






}

