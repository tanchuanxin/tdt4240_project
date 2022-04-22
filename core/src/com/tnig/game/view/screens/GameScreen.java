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
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
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
    private final FillViewport viewport;
    private final TiledMap tiledMap;
    private final float mapWidthNumTiles;
    private final float mapHeightNumTiles;
    private final float mapWidthInPixels;
    private final float mapHeightInPixels;
    private final float mapWidthInUnits;
    private final float mapHeightInUnits;
    private final Box2DDebugRenderer b2dr = new Box2DDebugRenderer();

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
        this.mapWidthNumTiles = tiledMap.getProperties().get("width", Integer.class);
        this.mapHeightNumTiles = tiledMap.getProperties().get("height", Integer.class);
        this.mapWidthInPixels = mapWidthNumTiles * mapTileWidth;
        this.mapHeightInPixels = mapHeightNumTiles * mapTileHeight;
        this.mapWidthInUnits = mapWidthInPixels / PPM;
        this.mapHeightInUnits = mapHeightInPixels / PPM;

        // Create viewport
        viewport = new FillViewport(mapWidthInUnits, mapHeightInUnits, gameCamera);
        viewport.apply();

        // Initialize game world and map to see what camera sees only
        this.engine = new GameWorld();
        mapRenderer.setView((OrthographicCamera) viewport.getCamera());

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
        viewport.getCamera().update(); // Update our camera every frame
        viewport.getCamera().position.set(gameManager.getPlayerPosX(), gameManager.getPlayerPosY(), 0);
        checkCameraBounds(); // Make sure camera doesn't leave the screen

        // Make map and spritebatch only draw what the camera can see
        mapRenderer.setView((OrthographicCamera) viewport.getCamera());
        batch.setProjectionMatrix(viewport.getCamera().combined);
    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Render game
        mapRenderer.render();
        batch.begin();
        renderAnimatedViews();
        batch.end();

        // Render debugging
        b2dr.render(engine.getWorld(), viewport.getCamera().combined);

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
//        float mapLeftBound = 0;
//        float mapRightBound = mapWidthInUnits;
//        float mapBtmBound = 0;
//        float mapTopBound = mapHeightInUnits;
//
//        Gdx.app.log("Map right bound: ", String.valueOf(mapRightBound));
//        Gdx.app.log("Map left bound: ", String.valueOf(mapLeftBound));
//
//        OrthographicCamera cam = (OrthographicCamera) viewport.getCamera();
//
//        Gdx.app.log("Camera position: ", String.valueOf(cam.position));
//
//        // Check camera bounds
//        float cameraLeft = cam.position.x - cam.viewportWidth / 2;
//        float cameraRight = cam.position.x + cam.viewportWidth / 2;
//        float cameraBtm = cam.position.y - cam.viewportHeight / 2;
//        float cameraTop = cam.position.y + cam.viewportHeight / 2;
//
//        // Check bounds on left right
//        if (cameraLeft <= mapLeftBound) {
//            cam.position.x = mapLeftBound + cam.viewportWidth / 2;
//        } else if (cameraRight >= mapRightBound) {
//            cam.position.x = mapRightBound - cam.viewportWidth / 2;
//        }
//
//        // Check bounds on top down
//        if (cameraBtm <= mapBtmBound) {
//            cam.position.y = mapBtmBound + cam.viewportHeight / 2;
//        } else if (cameraTop >= mapTopBound) {
//            cam.position.y = mapTopBound - cam.viewportHeight / 2;
//        }
//
//        Gdx.app.log("Camera position fixed: ", String.valueOf(cam.position));
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
