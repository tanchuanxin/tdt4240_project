package com.tnig.game.view;

import static com.tnig.game.utilities.Constants.PPM;
import static com.tnig.game.utilities.Constants.VIEWPORT_HEIGHT;
import static com.tnig.game.utilities.Constants.VIEWPORT_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.tnig.game.controller.game_objects.dynamic_objects.AnimatedController;
import com.tnig.game.controller.managers.GameManager;
import com.tnig.game.controller.map.GameMap;
import com.tnig.game.utilities.AssetLoader;

import java.util.List;

/**
 * This class encapsulates the LibGDX Spritebatch module
 */
public class GameRenderer {

    private final SpriteBatch batch;
    private final GameManager gameManager;

    public GameRenderer(SpriteBatch batch,
                        GameManager gameManager) {
        this.batch = batch;
        this.gameManager = gameManager;

    }


    public void render(){
        renderAnimatedViews();
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

}
