package com.tnig.game.view.screens;

import static com.tnig.game.utilities.Constants.PPM;
import static com.tnig.game.utilities.Constants.VIEWPORT_HEIGHT;
import static com.tnig.game.utilities.Constants.VIEWPORT_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sun.org.apache.bcel.internal.Const;
import com.tnig.game.controller.InputController;
import com.tnig.game.controller.game.GameInitializer;
import com.tnig.game.controller.game.NormalGame;
import com.tnig.game.controller.game_objects.dynamic_objects.AnimatedController;
import com.tnig.game.controller.managers.EventManager;
import com.tnig.game.controller.managers.GameManager;
import com.tnig.game.controller.managers.ScreenManager;
import com.tnig.game.controller.map.GameMap;
import com.tnig.game.model.physics_engine.Engine;
import com.tnig.game.model.physics_engine.GameWorld;
import com.tnig.game.utilities.AssetLoader;
import com.tnig.game.utilities.Constants;

import java.util.List;


public class GameScreen extends AbstractScreen {
    private final ScreenManager screenManager;
    private final Engine engine;
    private final SpriteBatch batch;
    private final GameManager gameManager;
    private final OrthographicCamera gameCamera;
    private final OrthogonalTiledMapRenderer mapRenderer;
    private final Viewport viewport;
    private final TiledMap tiledMap;
    private final float mapWidth;
    private final float mapHeight;
    private final float zoom = 2f;

    public GameScreen(ScreenManager screenManager,
                      EventManager eventManager,
                      OrthographicCamera camera,
                      AssetLoader assetLoader,
                      GameMap map,
                      int numberOfPlayers) {
        super(camera, assetLoader);
        this.screenManager = screenManager;
        this.batch = new SpriteBatch();
        this.tiledMap = map.getTiledMap();

        // Set up game camera and viewport
        this.gameCamera = new OrthographicCamera();

        // Scale tile map to fit screen
        mapRenderer = new OrthogonalTiledMapRenderer(tiledMap, 1 / PPM);

        // Get tile map properties
        int mapTileWidth = tiledMap.getProperties().get("tilewidth", Integer.class);
        int mapTileHeight = tiledMap.getProperties().get("tileheight", Integer.class);

        this.mapWidth = (tiledMap.getProperties().get("width", Integer.class) * mapTileWidth) / PPM;
        this.mapHeight = (tiledMap.getProperties().get("height", Integer.class) * mapTileHeight) / PPM;

        // Set camera to starting position and zoom in to correct size
        gameCamera.setToOrtho(false, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        viewport = new FitViewport(mapWidth / zoom, mapHeight / zoom, gameCamera);
        gameCamera.position.set(viewport.getWorldWidth()/2, viewport.getWorldHeight()/2, 0);

        // Initialize game world and map to see what camera sees only
        engine = new GameWorld(gameCamera);
        mapRenderer.setView(gameCamera);

        GameInitializer initializer = new NormalGame();
        this.gameManager = initializer.initGame(eventManager, engine, assetLoader, map, numberOfPlayers);

        Gdx.input.setInputProcessor(new InputController(eventManager));
    }

    @Override
    public void show() {

    }


    private void update(float delta) {
        // Update game
        engine.update(delta);
        gameManager.update(delta);

        // Update camera
        gameCamera.update(); // Update our camera every frame
        gameCamera.position.x = gameManager.getPlayerPosX();

        checkCameraBounds(); // Make sure camera doesn't leave the screen

        // Make map and spritebatch only draw what the camera can see
        mapRenderer.setView(gameCamera);
        batch.setProjectionMatrix(gameCamera.combined);

        Gdx.app.log("viewport vwidth: ", String.valueOf(VIEWPORT_WIDTH));
        Gdx.app.log("viewport vheight: ", String.valueOf(VIEWPORT_HEIGHT));
        Gdx.app.log("viewport world width: ", String.valueOf(viewport.getWorldWidth()));
        Gdx.app.log("viewport world height: ", String.valueOf(viewport.getWorldHeight()));

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(delta);

        // Render game
        mapRenderer.render();
        batch.begin();
        renderAnimatedViews();
        batch.end();

        if (gameManager.gameFinished()) {
            // TODO: Push event game finished
        }


    }

    @Override
    public void resize(int width, int height) {
        this.viewport.update(width, height);
    }

    @Override
    public void hide() {
        // Dispose this screen and its scenes
        dispose();
    }

    @Override
    public void dispose() {
        engine.dispose();
        gameManager.dispose();
        batch.dispose();
    }

    /**
     * Sets bounds on the camera so it never leaves the screen
     */
    private void checkCameraBounds() {
        float mapLeftBound = 0;
        float mapRightBound = mapWidth;
        float mapBtmBound = 0;
        float mapTopBound = mapHeight;

        // The camera dimensions, halved
        float cameraHalfWidth = gameCamera.viewportWidth * .5f;
        float cameraHalfHeight = gameCamera.viewportHeight * .5f;

        // Move camera after player as normal
        float cameraLeft = gameCamera.position.x - cameraHalfWidth;
        float cameraRight = gameCamera.position.x + cameraHalfWidth;
        float cameraBottom = gameCamera.position.y - cameraHalfHeight;
        float cameraTop = gameCamera.position.y + cameraHalfHeight;

        // Check bounds on left right
        if (VIEWPORT_WIDTH < gameCamera.viewportWidth) {
            gameCamera.position.x = mapRightBound / 2;
        } else {
            if (cameraLeft <= mapLeftBound) {
                gameCamera.position.x = mapLeftBound + cameraHalfWidth;
            } else if (cameraRight >= mapRightBound) {
                gameCamera.position.x = mapRightBound - cameraHalfWidth;
            }
        }

        // Check bounds on top down
        if (VIEWPORT_HEIGHT < gameCamera.viewportHeight) {
            gameCamera.position.y = mapTopBound / 2;
        } else {
            if (cameraBottom <= mapBtmBound) {
                gameCamera.position.y = mapBtmBound + cameraHalfHeight;
            } else if (cameraTop >= mapTopBound) {
                gameCamera.position.y = mapTopBound - cameraHalfHeight;
            }
        }
    }

    /**
     * Gets all the animated controllers from the gamemanager and renders all the
     * Animated views in the game
     */
    private void renderAnimatedViews() {
        List<AnimatedController> controllers = gameManager.getAnimatedControllers();

        for (AnimatedController controller : controllers) {
            controller.getView().render(batch);
        }
    }


}
