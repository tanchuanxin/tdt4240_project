package com.tnig.game.controller.game_initializers;

import com.tnig.game.controller.game_objects.Controller;
import com.tnig.game.controller.game_objects.dynamic_objects.AnimatedController;

import java.util.List;

/**
 * Initializes the game and returns a GameManager for that game
 */
public interface GameInitializer {
    List<AnimatedController> getAnimatedControllers();
    List<Controller> getControllers();
    AnimatedController getPlayer();
}
