package com.tnig.game.view.screens;

import static com.tnig.game.utilities.Constants.PPM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.tnig.game.controller.InputController;
import com.tnig.game.controller.events.Event;
import com.tnig.game.controller.events.EventListener;
import com.tnig.game.controller.events.EventName;
import com.tnig.game.controller.game_objects.dynamic_objects.AnimatedController;
import com.tnig.game.controller.managers.EventManager;
import com.tnig.game.controller.managers.GameManager;
import com.tnig.game.controller.managers.ScreenManager;
import com.tnig.game.controller.map.GameMap;
import com.tnig.game.utilities.AssetLoader;

import java.util.List;


public class GameScreen extends AbstractScreen implements EventListener {
    private final ScreenManager screenManager;
    private final SpriteBatch batch;
    private final GameManager gameManager;
    private final OrthogonalTiledMapRenderer mapRenderer;
    private final FillViewport viewport;
    private boolean paused = false;


    public GameScreen(ScreenManager screenManager,
                      EventManager eventManager,
                      OrthographicCamera camera,
                      AssetLoader assetLoader,
                      int mapNumber,
                      int numberOfPlayers) {
        super(camera, assetLoader);
        this.screenManager = screenManager;
        this.batch = new SpriteBatch();
        GameMap map = new GameMap(mapNumber);


        // Create viewport
        viewport = new FillViewport(map.getWidthInUnits(), map.getHeightInUnits());
        viewport.apply(true);

        mapRenderer = new OrthogonalTiledMapRenderer(map.getTiledMap(), 1/PPM);
        mapRenderer.setView((OrthographicCamera) viewport.getCamera());

        gameManager = new GameManager(eventManager, assetLoader, map, viewport, numberOfPlayers);

        Gdx.input.setInputProcessor(new InputController(eventManager));

        eventManager.subscribe(EventName.NEW_GAME, this);
        eventManager.subscribe(EventName.GAME_OVER, this);


    }

    @Override
    public void receiveEvent(Event event) {
        switch (event.name){
            case NEW_GAME:
                gameManager.newGame();
                break;
            case GAME_OVER:
                break;
        }
    }

    @Override
    public void show () {

    }


    private void update(float delta){

        // Update camera
        viewport.getCamera().update(); // Update our camera every frame
        viewport.getCamera().position.set(gameManager.getPlayerPosX(), gameManager.getPlayerPosY(), 0);
        checkCameraBounds(); // Make sure camera doesn't leave the screen

        // Update game
        gameManager.update(delta);

        // Make map and spritebatch only draw what the camera can see
        mapRenderer.setView((OrthographicCamera) viewport.getCamera());
        batch.setProjectionMatrix(viewport.getCamera().combined);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        if (!paused){
            update(delta);

            // Render game
            mapRenderer.render();
            batch.begin();
            renderAnimatedViews();
            batch.end();

        }

    }

    @Override
    public void pause(){
        paused = true;
    }

    @Override
    public void resume(){
        paused = false;
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
        gameManager.dispose();
        batch.dispose();
        mapRenderer.dispose();
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



}
