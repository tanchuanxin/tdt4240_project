package com.tnig.game.controller.game_initializers;

import static com.tnig.game.utilities.Constants.PPM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.tnig.game.controller.game_objects.Controller;
import com.tnig.game.controller.game_objects.dynamic_objects.AnimatedObjectController;
import com.tnig.game.controller.game_objects.static_objects.StaticObjectController;
import com.tnig.game.controller.managers.EventManager;
import com.tnig.game.controller.game_objects.dynamic_objects.AnimatedController;
import com.tnig.game.controller.map.GameMap;
import com.tnig.game.model.models.interfaces.ModelType;
import com.tnig.game.model.models.ObjectType;
import com.tnig.game.model.models.blocks.BlockType;
import com.tnig.game.model.models.coins.CoinType;
import com.tnig.game.model.models.obstacles.ObstacleType;
import com.tnig.game.model.models.players.PlayerType;
import com.tnig.game.model.models.sensors.SensorType;
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
    private final EventManager eventManager;
    private final Engine engine;
    private final AssetLoader assetLoader;

    private final List<AnimatedController> animatedControllers = new ArrayList<>();
    private final List<Controller> controllers = new ArrayList<>();
    private AnimatedController player;

    public NormalGame(EventManager eventManager, Engine engine, AssetLoader assetLoader, GameMap map) {
        this.eventManager = eventManager;
        this.engine = engine;
        this.assetLoader = assetLoader;
        initGame(map);
    }

    private void initGame(GameMap map) {
        Gdx.app.log("GameManager", "init Game");
        TiledMap tiledMap = map.getTiledMap();
        // Static objects
        // Obstacles
        initStaticControllers(tiledMap, Constants.spikeLayer, ObstacleType.SPIKE);
        // Blocks
        initStaticControllers(tiledMap, Constants.blockLayer, BlockType.NORMAL_BLOCK);
        // Coins
        initStaticControllers(tiledMap, Constants.coinLayer, CoinType.NORMAL_COIN);
        // Sensors
        initStaticControllers(tiledMap, Constants.deathSensorLayer, SensorType.DEATH_SENSOR);
        initStaticControllers(tiledMap, Constants.finishLineLayer, SensorType.FINISH_LINE);

        // Animated objects
        initAnimatedControllers(tiledMap, Constants.playerLayer, PlayerType.NORMALPLAYER);
        // TODO: ADD OTHER ANIMATED OBJECTS HERE



    }

    @Override
    public List<AnimatedController> getAnimatedControllers() {
        return animatedControllers;
    }
    @Override
    public List<Controller> getControllers() {
        return controllers;
    }

    @Override
    public AnimatedController getPlayer() {
        return player;
    }

    private void initStaticControllers(TiledMap map, String layer, ModelType modelType){
        for (RectangleMapObject object : map.getLayers()
                .get(layer).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = object.getRectangle();
            Controller controller = new StaticObjectController(
                    eventManager, engine, (rect.x + (rect.width / 2)) / PPM, (rect.y + (rect.height / 2)) / PPM,
                    rect.width / PPM, rect.height / PPM, modelType);

            controllers.add(controller);
        }
    }

    private void initAnimatedControllers(TiledMap map, String layer, ModelType modelType){

        // Initialize map animated objects
        for (RectangleMapObject object : map.getLayers()
                .get(layer).getObjects().getByType(RectangleMapObject.class)) {

            AnimatedController animatedController;
            Rectangle rect = object.getRectangle();

            animatedController = new AnimatedObjectController(
                    eventManager, engine, assetLoader, (rect.x + (rect.width / 2)) / PPM, (rect.y + (rect.height / 2)) / PPM,
                    rect.width / PPM, rect.height / PPM, modelType);

            animatedControllers.add(animatedController);

            if (modelType.getObjectType().equals(ObjectType.PLAYER)){
                player = animatedController;
            }

        }
    }








}

