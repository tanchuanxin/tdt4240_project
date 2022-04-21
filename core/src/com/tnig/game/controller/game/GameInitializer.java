package com.tnig.game.controller.game;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.tnig.game.controller.game_objects.Controller;
import com.tnig.game.controller.game_objects.dynamic_objects.AnimatedController;
import com.tnig.game.controller.managers.EventManager;
import com.tnig.game.controller.managers.GameManager;
import com.tnig.game.controller.map.GameMap;
import com.tnig.game.model.physics_engine.Engine;
import com.tnig.game.utilities.AssetLoader;

import java.util.List;

/**
 * Initializes the game and returns a GameManager for that game
 */
public interface GameInitializer {

    List<AnimatedController> getAnimatedControllers();
    List<Controller> getControllers();
    AnimatedController getPlayer();
}
