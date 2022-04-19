package com.tnig.game.controller.game;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.tnig.game.controller.managers.EventManager;
import com.tnig.game.controller.managers.GameManager;
import com.tnig.game.controller.map.GameMap;
import com.tnig.game.model.physics_engine.Engine;
import com.tnig.game.utilities.AssetLoader;

/**
 * Initializes the game and returns a GameManager for that game
 */
public interface GameInitializer {

    GameManager initGame(EventManager eventManager, Engine engine, AssetLoader assetLoader, GameMap map, int players);

}
