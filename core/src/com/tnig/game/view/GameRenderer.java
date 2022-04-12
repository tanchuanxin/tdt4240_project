package com.tnig.game.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tnig.game.controller.game_objects.AnimatedController;
import com.tnig.game.controller.managers.GameManager;

import java.util.List;

/**
 * This class encapsulates the LibGDX Spritebatch module
 */
public class GameRenderer {

    private final SpriteBatch batch;
    private final GameManager gameManager;

    public GameRenderer(SpriteBatch batch, GameManager gameManager) {
        this.batch = batch;
        this.gameManager = gameManager;
    }


    /**
     * Gets all the animated controllers from the gamemanager and renders all the
     * Animated views in the game
     */
    public void renderAnimatedViews() {
        List<AnimatedController> controllers = gameManager.getControllers();

        for (AnimatedController controller: controllers) {
            controller.getView().render(batch);
        }
    }
}
