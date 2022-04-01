package com.tnig.game.controller.GameInitializers;

import com.tnig.game.controller.Managers.GameManager;
import com.tnig.game.model.physics_engine.Engine;
import com.tnig.game.view.GameRenderer;

/**
 * Initializes the game and returns a GameManager for that game
 */
public interface GameInitializer {

    GameManager initGame(Engine engine, GameRenderer gameRenderer);

}
