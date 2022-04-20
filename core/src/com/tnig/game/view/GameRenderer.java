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
    private final TiledMapRenderer mapRenderer;
    private final AssetLoader assetLoader;
    private final OrthographicCamera camera;
    private final Viewport viewport;

    public GameRenderer(SpriteBatch batch,
                        OrthographicCamera camera,
                        FitViewport viewport,
                        GameManager gameManager,
                        GameMap map,
                        AssetLoader assetLoader) {
        this.batch = batch;
        this.camera = camera;
        this.viewport = viewport;
//      viewport = new FitViewport(VIEWPORT_WIDTH/PPM, VIEWPORT_HEIGHT/PPM, camera);
        this.gameManager = gameManager;
        this.mapRenderer = new OrthogonalTiledMapRenderer(map.getTiledMap());
        mapRenderer.setView(camera);
        this.assetLoader = assetLoader;
    }


    public void render(){
        camera.update(); //update our camera every frame
        batch.setProjectionMatrix(camera.combined); //say the batch to only draw what we see in our camera

        renderMap();
        renderAnimatedViews();
    }

    public void resize(int width, int height){
        viewport.update(width, height);
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
