package com.tnig.game.controller.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.tnig.game.controller.game_objects.Controller;
import com.tnig.game.controller.game_objects.dynamic_objects.AnimatedObjectController;
import com.tnig.game.controller.game_objects.static_objects.StaticObjectController;
import com.tnig.game.controller.managers.EventManager;
import com.tnig.game.controller.managers.GameManager;
import com.tnig.game.controller.game_objects.dynamic_objects.AnimatedController;
import com.tnig.game.controller.map.GameMap;
import com.tnig.game.model.models.ModelType;
import com.tnig.game.model.models.blocks.BlockType;
import com.tnig.game.model.models.obstacles.ObstacleType;
import com.tnig.game.model.models.players.PlayerType;
import com.tnig.game.model.physics_engine.Engine;
import com.tnig.game.utilities.AssetLoader;
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
    public GameManager initGame(EventManager eventManager, Engine engine, AssetLoader assetLoader, GameMap map, int players) {
        Gdx.app.log("GameManager", "init Game");
        TiledMap tiledMap = map.getTiledMap();
        // Static objects
        initStaticControllers(tiledMap, engine, Constants.spikeLayer, ObstacleType.SPIKE);
        initStaticControllers(tiledMap, engine, Constants.blockLayer, BlockType.NORMAL_BLOCK);

        // Animated objects
        initAnimatedControllers(tiledMap, engine, assetLoader, Constants.playerLayer, PlayerType.NORMALPLAYER);
        // TODO: ADD OTHER ANIMATED OBJECTS HERE

        return new GameManager(eventManager, engine, animatedControllers, controllers, tiledMap, players);

    }

    private void initStaticControllers(TiledMap map, Engine engine, String layer, ModelType modelType){
        for (RectangleMapObject object : map.getLayers()
                .get(layer).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = object.getRectangle();
            Controller controller = new StaticObjectController(
                    engine, rect.x + rect.width / 2, rect.y + rect.height / 2, rect.width, rect.height, modelType);

            controllers.add(controller);
        }
    }

    private void initAnimatedControllers(
            TiledMap map, Engine engine, AssetLoader assetLoader, String layer, ModelType modelType){

        // Initialize map animated objects
        for (RectangleMapObject object : map.getLayers()
                .get(layer).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = object.getRectangle();
            AnimatedController animatedController = new AnimatedObjectController(
                    engine, assetLoader, rect.x, rect.y, rect.width, rect.height, modelType);

            animatedControllers.add(animatedController);
        }
    }








}

