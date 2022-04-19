package com.tnig.game.view;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.tnig.game.controller.game_objects.dynamic_objects.AnimatedController;
import com.tnig.game.controller.managers.GameManager;
import com.tnig.game.utilities.AssetLoader;

import java.util.List;

/**
 * This class encapsulates the LibGDX Spritebatch module
 */
public class GameRenderer {

    private final SpriteBatch batch;
    private final GameManager gameManager;
    private final TiledMapRenderer mapRenderer;
    private final AssetLoader assetLoader;

    public GameRenderer(SpriteBatch batch,
                        OrthographicCamera camera,
                        GameManager gameManager,
                        TiledMap map,
                        AssetLoader assetLoader) {
        this.batch = batch;
        this.gameManager = gameManager;
        mapRenderer = new OrthogonalTiledMapRenderer(map);
        mapRenderer.setView(camera);
        this.assetLoader = assetLoader;
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
            controller.getView().render(batch, assetLoader);
        }
    }

    private void renderMap(){
        mapRenderer.render();
    }
}
