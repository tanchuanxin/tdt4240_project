package com.tnig.game.view.screens;

import static com.tnig.game.utilities.Constants.PPM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
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
import com.tnig.game.utilities.Constants;

import java.util.List;


public class GameScreen extends AbstractScreen implements EventListener {
    private final ScreenManager screenManager;
    private final SpriteBatch batch;
    private final GameManager gameManager;
    private final OrthogonalTiledMapRenderer mapRenderer;
    private final FillViewport viewport;
    private final Box2DDebugRenderer b2dr;
    private final GameMap map;

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

        this.map = map;

//        this.gameCamera = new OrthographicCamera();
//        this.viewport = new FillViewport(map.getMapWidthInUnits(), map.getMapHeightInUnits());
//        viewport.apply(true);

        // Create viewport
        viewport = new FillViewport(map.getMapWidthInUnits(), map.getMapHeightInUnits());
        viewport.apply(true);

        mapRenderer = new OrthogonalTiledMapRenderer(map.getTiledMap(), 1/PPM);
        mapRenderer.setView((OrthographicCamera) viewport.getCamera());

        gameManager = new GameManager(eventManager, assetLoader, map, viewport, numberOfPlayers);

        eventManager.subscribe(EventName.NEW_GAME, this);
        eventManager.subscribe(EventName.GAME_OVER, this);

        // Create GUI for game
        stage = new Stage(viewport);
//
//
//        // TODO: Switch this line when ready to test on mobile
        Gdx.input.setInputProcessor(new InputController(eventManager));
//        Gdx.input.setInputProcessor(stage);
//
//        ButtonFactory buttonFactory = new ButtonFactory(eventManager, screenManager, assetLoader);
//
//        Button jumpBtn = buttonFactory.createCustomEventButton(new Jump(), new Button(assetLoader.get(AssetLoader.SKIN_PIXTHULHU_UI), "arcade"), true);
//
//        Table tableRight = new Table();
//        tableRight.setPosition(Gdx.graphics.getWidth() - 70, 70, Align.right);
//        tableRight.add(jumpBtn);
//        stage.addActor(tableRight);
//
//        Table tableLeft = new Table();
//        final Touchpad touchpad = new Touchpad(0.1f, assetLoader.get(AssetLoader.SKIN_PIXTHULHU_UI));
//        touchpad.addListener(new ChangeListener() {
//            @Override
//            public void changed(ChangeEvent changeEvent, Actor actor) {
//                // Check move left or right
//                if (touchpad.getKnobPercentX() > 0.1) {
//                    eventManager.pushEvent(new MoveRight());
//                }
//                else if (touchpad.getKnobPercentX() < -0.1) {
//                    eventManager.pushEvent(new MoveLeft());
//                }
//                else {
//                    eventManager.pushEvent(new StopPlayer());
//                }
//            }
//        });
//        touchpad.setSize(30f, 30f);
//        tableLeft.setPosition(90, 90);
//        tableLeft.add(touchpad);
//        stage.addActor(tableLeft);

        // Create debug renderer
        b2dr = new Box2DDebugRenderer();
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
//        viewport.getCamera().position.set(gameManager.getPlayerPosX(), gameManager.getPlayerPosY(), 0);
        viewport.getCamera().position.set(gameManager.getPlayerPosX(), viewport.getWorldHeight()/2, 0);

        Gdx.app.log("gameManager.getPlayerPosX(): ", String.valueOf(gameManager.getPlayerPosX()));
        Gdx.app.log("gameManager.getPlayerPosY(): ", String.valueOf(gameManager.getPlayerPosY()));

        // Update game
        gameManager.update(delta);

        checkCameraBounds(); // Make sure camera doesn't leave the screen
        viewport.getCamera().update();

        // Make map and spritebatch only draw what the camera can see
        mapRenderer.setView((OrthographicCamera) viewport.getCamera());
        batch.setProjectionMatrix(viewport.getCamera().combined);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

//        stage.act(delta);

        if (!paused){
            update(delta);

            // Render game
            mapRenderer.render();
            batch.begin();
            renderAnimatedViews();
            batch.end();
            b2dr.render(gameManager.getEngine().getWorld(), viewport.getCamera().combined);
        }

//        stage.draw();
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
        viewport.update(width, height);
//        stage.getViewport().update(width, height, true);
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
        float mapLeftBound = 0;
        float mapRightBound = this.map.getMapWidthInUnits();
        float mapBtmBound = 0;
        float mapTopBound = this.map.getMapHeightInUnits();

        Gdx.app.log("mapLeftBound: ", String.valueOf(mapLeftBound));
        Gdx.app.log("mapRightBound: ", String.valueOf(mapRightBound));
        Gdx.app.log("mapBtmBound: ", String.valueOf(mapBtmBound));
        Gdx.app.log("mapTopBound: ", String.valueOf(mapTopBound));

        OrthographicCamera cam = (OrthographicCamera) viewport.getCamera();

        Gdx.app.log("Camera position: ", String.valueOf(cam.position));

        // Check camera bounds
        float cameraHalfWidth = viewport.getScreenWidth() / PPM / map.getTileWidth() * .5f;
        float cameraHalfHeight = viewport.getScreenHeight() / PPM / map.getTileHeight() * .5f;
        Gdx.app.log("cameraHalfWidth: ", String.valueOf(cameraHalfWidth));
        Gdx.app.log("cameraHalfHeight: ", String.valueOf(cameraHalfHeight));
        float cameraLeft = cam.position.x - cameraHalfWidth;
        float cameraRight = cam.position.x + cameraHalfWidth;
        float cameraBtm = cam.position.y - cameraHalfHeight;
        float cameraTop = cam.position.y + cameraHalfHeight;
        Gdx.app.log("cam.viewportWidth: ", String.valueOf(cam.viewportWidth));
        Gdx.app.log("cam.viewportHeight: ", String.valueOf(cam.viewportHeight));
        Gdx.app.log("viewport.getScreenWidth(): ", String.valueOf(viewport.getScreenWidth()));
        Gdx.app.log("viewport.getScreenHeight(): ", String.valueOf(viewport.getScreenHeight()));

        Gdx.app.log("cameraLeft: ", String.valueOf(cameraLeft));
        Gdx.app.log("cameraRight: ", String.valueOf(cameraRight));
        Gdx.app.log("cameraBtm: ", String.valueOf(cameraBtm));
        Gdx.app.log("cameraTop: ", String.valueOf(cameraTop));

        Gdx.app.log("Gdx.graphics.getWidth(): ", String.valueOf(Gdx.graphics.getWidth()));


        // Check bounds on left right
        if (cameraLeft <= mapLeftBound) {
            cam.position.x = mapLeftBound + cameraHalfWidth ;
        } else if (cameraRight >= mapRightBound) {
            cam.position.x = mapRightBound - cameraHalfWidth;
        }


//        viewport.setCamera(cam);


//        // Check bounds on btm top
//        if (cameraBtm <= mapBtmBound) {
//            cam.position.y = mapBtmBound + cameraHalfHeight ;
//        } else if (cameraTop >= mapRightBound) {
//            cam.position.y = mapTopBound - cameraHalfHeight;
//        }

        // Horizontal axis
//        if (Constants.VIEWPORT_WIDTH < cam.viewportWidth) {
//            cam.position.x = mapRightBound / 2;
//        } else {
//            if (cameraLeft <= mapLeftBound) {
//                cam.position.x = mapLeftBound + cameraHalfWidth;
//            } else if (cameraRight >= mapRightBound) {
//                cam.position.x = mapRightBound - cameraHalfWidth;
//            }
//        }
//
//        // Check bounds on top down
//        if (cameraBtm <= mapBtmBound) {
//            cam.position.y = mapBtmBound + cam.viewportHeight /2 ;
//        } else if (cameraTop >= mapTopBound) {
//            cam.position.y = mapTopBound - cam.viewportHeight /2 ;
//        }
//
        Gdx.app.log("Camera position fixed: ", String.valueOf(cam.position));
    }



}
