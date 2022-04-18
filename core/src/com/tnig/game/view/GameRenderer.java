package com.tnig.game.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.tnig.game.controller.game_objects.dynamic_objects.AnimatedController;
import com.tnig.game.controller.managers.GameManager;

import java.util.List;

/**
 * This class encapsulates the LibGDX Spritebatch module
 */
public class GameRenderer {

    private final SpriteBatch batch;
    private final GameManager gameManager;
    private final TiledMapRenderer mapRenderer;

    public GameRenderer(SpriteBatch batch, GameManager gameManager, TiledMap map) {
        this.batch = batch;
        this.gameManager = gameManager;
        mapRenderer = new OrthogonalTiledMapRenderer(map);
    }


    public void render(){
        renderAnimatedViews();
        renderMap();
    }

    /**
     * Gets all the animated controllers from the gamemanager and renders all the
     * Animated views in the game
     */
    private void renderAnimatedViews() {
        List<AnimatedController> controllers = gameManager.getAnimatedControllers();

        for (AnimatedController controller: controllers) {
            controller.getView().render(batch);
        }
    }

    private void renderMap(){
        mapRenderer.render();
    }
}
