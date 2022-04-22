package com.tnig.game.view.screens;

import static com.tnig.game.utilities.Constants.PPM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.tnig.game.controller.InputController;
import com.tnig.game.controller.events.Event;
import com.tnig.game.controller.events.EventListener;
import com.tnig.game.controller.events.EventName;
import com.tnig.game.controller.events.game_events.Jump;
import com.tnig.game.controller.events.game_events.MoveLeft;
import com.tnig.game.controller.events.game_events.MoveRight;
import com.tnig.game.controller.events.game_events.StopPlayer;
import com.tnig.game.controller.events.screen_events.InitGameEvent;
import com.tnig.game.controller.events.screen_events.QuitGameEvent;
import com.tnig.game.controller.game_objects.dynamic_objects.AnimatedController;
import com.tnig.game.controller.managers.EventManager;
import com.tnig.game.controller.managers.GameManager;
import com.tnig.game.controller.managers.ScreenManager;
import com.tnig.game.controller.map.GameMap;
import com.tnig.game.utilities.AssetLoader;
import com.tnig.game.view.ui_components.ButtonFactory;

import java.util.List;


public class GameScreen extends AbstractScreen implements EventListener {
    private final ScreenManager screenManager;
    private final SpriteBatch batch;
    private final GameManager gameManager;
    private final OrthogonalTiledMapRenderer mapRenderer;
    private final FillViewport viewport;
    private boolean paused = false;
    private final Stage stage;

    public GameScreen(ScreenManager screenManager,
                      final EventManager eventManager,
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

        eventManager.subscribe(EventName.NEW_GAME, this);
        eventManager.subscribe(EventName.GAME_OVER, this);

        // Create GUI for game
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        ButtonFactory buttonFactory = new ButtonFactory(eventManager, screenManager, assetLoader);

        Button jumpBtn = buttonFactory.createCustomEventButton(new Jump(), new Button(assetLoader.get(AssetLoader.SKIN_PIXTHULHU_UI), "arcade"), true);

        Table tableRight = new Table();
        tableRight.setPosition(Gdx.graphics.getWidth() - 70, 70, Align.right);
        tableRight.add(jumpBtn);
        stage.addActor(tableRight);

        Table tableLeft = new Table();
        final Touchpad touchpad = new Touchpad(0.1f, assetLoader.get(AssetLoader.SKIN_PIXTHULHU_UI));
        touchpad.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                Gdx.app.log("Touchpad: ", String.valueOf(touchpad.getKnobPercentX()));
                // Check move left or right
                if (touchpad.getKnobPercentX() > 0.1) {
                    eventManager.pushEvent(new MoveRight());
                }
                else if (touchpad.getKnobPercentX() < -0.1) {
                    eventManager.pushEvent(new MoveLeft());
                }
                else {
                    eventManager.pushEvent(new StopPlayer());
                }
            }
        });
        touchpad.setSize(30f, 30f);
        tableLeft.setPosition(90, 90);
        tableLeft.add(touchpad);
        stage.addActor(tableLeft);
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

        stage.act(delta);

        if (!paused){
            update(delta);

            // Render game
            mapRenderer.render();
            batch.begin();
            renderAnimatedViews();
            batch.end();

        }

        stage.draw();
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
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void hide() {
        // Dispose this screen and its scenes
        dispose();
    }

    @Override
    public void dispose() {
        stage.dispose();
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
