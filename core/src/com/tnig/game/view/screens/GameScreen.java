package com.tnig.game.view.screens;

import static com.tnig.game.utilities.Constants.PPM;
import static com.tnig.game.utilities.Constants.VIEWPORT_HEIGHT;
import static com.tnig.game.utilities.Constants.VIEWPORT_WIDTH;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tnig.game.controller.InputController;
import com.tnig.game.controller.events.Event;
import com.tnig.game.controller.events.EventListener;
import com.tnig.game.controller.events.EventName;
import com.tnig.game.controller.events.game_events.Attack;
import com.tnig.game.controller.events.game_events.Jump;
import com.tnig.game.controller.events.game_events.MoveLeft;
import com.tnig.game.controller.events.game_events.MoveRight;
import com.tnig.game.controller.events.game_events.StopPlayer;
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
    private OrthogonalTiledMapRenderer mapRenderer;
    private final OrthographicCamera cam;
    private final FitViewport viewport;
    private final Box2DDebugRenderer b2dr;
    private GameMap map;
    private Stage stage;
    private Button attackBtn;
    private final InputMultiplexer inputMultiplexer;
    private final Music gameMusic;
    private int playerNum;
    private Game game;
    private Stage readyStage;
    private boolean showReady = false;

    private boolean paused = false;
    private boolean gameOver = false;

    public GameScreen(Game game,
                      ScreenManager screenManager,
                      final EventManager eventManager,
                      OrthographicCamera camera,
                      AssetLoader assetLoader,
                      int mapNumber,
                      int numberOfPlayers) {
        super(camera, assetLoader);
        this.screenManager = screenManager;
        this.batch = new SpriteBatch();
        GameMap map = new GameMap(mapNumber);

        Gdx.graphics.setContinuousRendering(false);
        Gdx.graphics.requestRendering();

        this.game = game;
        this.playerNum = 0;

        this.map = map;

        this.inputMultiplexer = new InputMultiplexer();

        // Create viewport
        this.cam = new OrthographicCamera();
        this.viewport = new FitViewport(VIEWPORT_WIDTH/PPM, VIEWPORT_HEIGHT/PPM, cam);

        this.mapRenderer = new OrthogonalTiledMapRenderer(map.getTiledMap(), 1 / PPM);
        //        this.mapRenderer.setView((OrthographicCamera) viewport.getCamera());

        this.cam.position.set(viewport.getWorldWidth()/2, viewport.getWorldHeight()/2, 0);


        this.gameManager = new GameManager(eventManager, assetLoader, map, viewport, numberOfPlayers);

        // Subscribe to events
        eventManager.subscribe(EventName.NEW_GAME, this);
        eventManager.subscribe(EventName.GAME_OVER, this);
        eventManager.subscribe(EventName.PAUSE, this);

        // Get input processors
        this.stage = new Stage();
        stage.setViewport(viewport);
        this.inputMultiplexer.addProcessor(new InputController(eventManager));
        this.inputMultiplexer.addProcessor(stage);
        Gdx.input.setInputProcessor(this.inputMultiplexer);

        // Create GUI for game

        ButtonFactory buttonFactory = new ButtonFactory(eventManager, screenManager, assetLoader);

        createGUI(stage, buttonFactory, eventManager);

        // Create debug renderer
        b2dr = new Box2DDebugRenderer();

        // Play background music
        this.gameMusic = assetLoader.get(AssetLoader.MUSIC_ADVENTURE);
        this.gameMusic.setLooping(true);
        this.gameMusic.play();

        // Show get ready screen
        getReady();
    }

    @Override
    public void receiveEvent(Event event) {
        switch (event.name) {
            case NEW_GAME:
                gameManager.newGame();
                // refresh map
                this.map = gameManager.getMap();
                this.mapRenderer = new OrthogonalTiledMapRenderer(map.getTiledMap(), 1 / PPM);
                this.mapRenderer.setView((OrthographicCamera) viewport.getCamera());
                getReady();
                break;
            case GAME_OVER:
                gameOver = true;
                break;
            case PAUSE:
                break;
        }
    }

    // Shows a get ready
    private void getReady() {
        Skin skin = assetLoader.get(AssetLoader.SKIN_PIXTHULHU_UI);
        readyStage = new Stage(new ScreenViewport());

        Table readyTable = new Table();
        readyTable.align(Align.center).setFillParent(true);
        readyTable.row().padBottom(20f);

        playerNum++;
        Label readyLabel = new Label("Ready Player " + String.valueOf(playerNum) + "?", skin);
        readyTable.add(readyLabel).fillX();

        readyTable.row();
        Button readyButton = new Button(new Label("Go!", skin), skin);
        readyButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent inputEvent, float x, float y, int pointer, int button) {
                showReady = false;
                game.resume();
            }
        });
        readyTable.add(readyButton).fillX();

        readyStage.addActor(readyTable);

        showReady = true;
    }

    private void update(float delta) {
        // Update camera
//        viewport.getCamera().update(); // Update our camera every frame
//        viewport.getCamera().position.set(gameManager.getPlayerPosX(), viewport.getWorldHeight() / 2, 0);

//        Gdx.app.log("gameManager.getPlayerPosX(): ", String.valueOf(gameManager.getPlayerPosX()));
//        Gdx.app.log("gameManager.getPlayerPosY(): ", String.valueOf(gameManager.getPlayerPosY()));

        // Update game
        gameManager.update(delta);


        cam.position.x = gameManager.getPlayerPosX();
        cam.position.y = gameManager.getPlayerPosY();
        checkCameraBounds(); // Make sure camera doesn't leave the screen
        cam.update();

        // Make map and spritebatch only draw what the camera can see
        mapRenderer.setView(cam);
        batch.setProjectionMatrix(cam.combined);

        // update score label
        Label scoreLabel = stage.getRoot().findActor("scoreLabel");
        scoreLabel.setText(String.valueOf(this.gameManager.getScore()));

        Table attackBtnTable = stage.getRoot().findActor("attackBtnTable");

        if (this.gameManager.getAttackTimeout() < 0) {
            if (attackBtnTable.findActor("attackBtn") == null) {
                attackBtnTable.add(attackBtn);
            }
        } else {
            if (attackBtnTable.findActor("attackBtn") != null) {
                attackBtnTable.removeActor(attackBtn);
            }
        }

    }

    @Override
    public void render(float delta) {
        Gdx.input.setInputProcessor(inputMultiplexer);

        stage.act(delta);

        if (!paused) {
            update(delta);

            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            Gdx.graphics.requestRendering();

            // Render game
            mapRenderer.render();
            b2dr.render(gameManager.getEngine().getWorld(), cam.combined);


            batch.setProjectionMatrix(cam.combined);
            batch.begin();
            renderAnimatedViews();
            batch.end();
        }


//        stage.getViewport().apply();
//        batch.setProjectionMatrix(stage.getCamera().combined);
        stage.draw();

        if (gameOver) {
            screenManager.setScreen(ScreenName.GAME_OVER);
        }

        if (showReady) {
            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            Gdx.input.setInputProcessor(readyStage);
            readyStage.getViewport().apply();
            readyStage.act(delta);
            readyStage.draw();
            game.pause();
        }
    }

    @Override
    public void pause() {
        paused = true;
    }

    @Override
    public void resume() {
        paused = false;
    }

    @Override
    public void hide() {
        gameMusic.stop();

        // Dispose this screen and its scenes
        dispose();
    }

    @Override
    public void dispose() {
        super.dispose();
        gameManager.dispose();
        batch.dispose();
        mapRenderer.dispose();
        readyStage.dispose();
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

    /**
     * Sets bounds on the camera so it never leaves the screen
     */
    private void checkCameraBounds() {
        float mapLeftBound = 0;
        float mapRightBound = this.map.getMapWidthInUnits();
        float mapBtmBound = 0;
        float mapTopBound = this.map.getMapHeightInUnits();

        float cameraHalfWidth = cam.viewportWidth * .5f;
        float cameraHalfHeight = cam.viewportHeight * .5f;

        float cameraLeft = cam.position.x - cameraHalfWidth;
        float cameraRight = cam.position.x + cameraHalfWidth;
        float cameraBtm = cam.position.y - cameraHalfHeight;
        float cameraTop = cam.position.y + cameraHalfHeight;

        Gdx.app.log("Camera position: ", String.valueOf(cam.position));

        Gdx.app.log("mapLeftBound: ", String.valueOf(mapLeftBound));
        Gdx.app.log("mapRightBound: ", String.valueOf(mapRightBound));
        Gdx.app.log("mapBtmBound: ", String.valueOf(mapBtmBound));
        Gdx.app.log("mapTopBound: ", String.valueOf(mapTopBound));

        Gdx.app.log("cameraHalfWidth: ", String.valueOf(cameraHalfWidth));
        Gdx.app.log("cameraHalfHeight: ", String.valueOf(cameraHalfHeight));

        Gdx.app.log("cameraLeft: ", String.valueOf(cameraLeft));
        Gdx.app.log("cameraRight: ", String.valueOf(cameraRight));
        Gdx.app.log("cameraBtm: ", String.valueOf(cameraBtm));
        Gdx.app.log("cameraTop: ", String.valueOf(cameraTop));

        Gdx.app.log("Gdx.graphics.getWidth(): ", String.valueOf(Gdx.graphics.getWidth()));
        Gdx.app.log("Gdx.graphics.getHeight(): ", String.valueOf(Gdx.graphics.getHeight()));

        // Check bounds on left right
        if (VIEWPORT_WIDTH < cam.viewportWidth) {
            cam.position.x = mapRightBound/2;
        } else {
            if (cameraLeft <= mapLeftBound) {
                cam.position.x = mapLeftBound + cameraHalfWidth;
            } else if (cameraRight >= mapRightBound) {
                cam.position.x = mapRightBound - cameraHalfWidth;
            }
        }

        // Check bounds on btm top
        if (VIEWPORT_HEIGHT < cam.viewportHeight) {
            cam.position.y = mapTopBound/2;
        } else {
            if (cameraBtm <= mapBtmBound) {
                cam.position.y = mapBtmBound + cameraHalfHeight;
            } else if (cameraTop >= mapTopBound) {
                cam.position.y = mapTopBound - cameraHalfHeight;
            }
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

    private void createGUI(Stage stage, ButtonFactory buttonFactory, final EventManager eventManager) {

        // Android specific GUI
        if (Gdx.app.getType() == Application.ApplicationType.Android) {
            Button jumpBtn = buttonFactory.createCustomEventButton(new Jump(), new Button(assetLoader.get(AssetLoader.SKIN_PIXTHULHU_UI), "arcade"), true);

            Table jumpButtonTable = new Table();
            jumpButtonTable.setDebug(true);
            jumpButtonTable.setSize(180f, 180f);
            jumpButtonTable.setPosition(stage.getViewport().getScreenWidth() - 140, 120, Align.center);
            jumpButtonTable.row();
            jumpButtonTable.add(jumpBtn).size(180f);
            jumpButtonTable.setName("jumpButtonTable");
            stage.addActor(jumpButtonTable);

            final Touchpad touchpad = new Touchpad(0.1f, assetLoader.get(AssetLoader.SKIN_PIXTHULHU_UI));
            touchpad.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent changeEvent, Actor actor) {
                    // Check move left or right
                    if (touchpad.getKnobPercentX() > 0.1) {
                        eventManager.pushEvent(new MoveRight());
                    } else if (touchpad.getKnobPercentX() < -0.1) {
                        eventManager.pushEvent(new MoveLeft());
                    } else {
                        eventManager.pushEvent(new StopPlayer(0));
                    }
                }
            });
            touchpad.setName("touchpad");

            Table touchpadTable = new Table();
            touchpadTable.setSize(350f, 350f);
            touchpadTable.setPosition(230, 210, Align.center);
            touchpadTable.row().fill();
            touchpadTable.add(touchpad).center().size(350f);
            touchpadTable.setName("touchpadTable");
            stage.addActor(touchpadTable);
        }

        this.attackBtn = buttonFactory.createCustomEventButton(new Attack(), new Button(assetLoader.get(AssetLoader.SKIN_PIXTHULHU_UI_2), "arcade"), true);
        this.attackBtn.setName("attackBtn");

        Table attackBtnTable = new Table();

        attackBtnTable.setSize(100f, 100f);
        attackBtnTable.row().fill();

        if (Gdx.app.getType() == Application.ApplicationType.Android) {
            attackBtnTable.setPosition(stage.getViewport().getScreenWidth() - 100, stage.getViewport().getScreenHeight() - 100, Align.center);
        } else {
            attackBtnTable.setPosition(stage.getViewport().getScreenWidth() - 80, stage.getViewport().getScreenHeight() - 80, Align.center);
        }
        attackBtnTable.add(attackBtn);


        attackBtnTable.setName("attackBtnTable");
        stage.addActor(attackBtnTable);

        Gdx.app.log("stage.getCamera().viewportWidth: ", String.valueOf(stage.getCamera().viewportWidth));
        Gdx.app.log("stage.getViewport().getScreenWidth(): ", String.valueOf(stage.getViewport().getScreenWidth()));
        Gdx.app.log("stage.getWidth(): ", String.valueOf(stage.getWidth()));
        Gdx.app.log("stage.getViewport().getWorldWidth(): ", String.valueOf(stage.getViewport().getWorldWidth()));
        // Gdx.app.log("Gdx.graphics.getWidth(): ", String.valueOf(Gdx.graphics.getWidth()));


        Label scoreLabelText = new Label("SCORE", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreLabelText.setPosition(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() - 35, Align.center);
        scoreLabelText.setFontScale(2);
        scoreLabelText.setName("scoreLabelText");
        stage.addActor(scoreLabelText);

        Label scoreLabel = new Label(String.valueOf(this.gameManager.getScore()), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreLabel.setPosition(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() - 70, Align.center);
        scoreLabel.setFontScale(2);
        scoreLabel.setName("scoreLabel");
        stage.addActor(scoreLabel);
    }


    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        stage.getViewport().update(width, height, true);
        readyStage.getViewport().update(width, height, true);

        Table attackBtnTable = stage.getRoot().findActor("attackBtnTable");
        attackBtnTable.setPosition(stage.getViewport().getScreenWidth() - 80, stage.getViewport().getScreenHeight() - 80, Align.center);

        if (Gdx.app.getType() == Application.ApplicationType.Android) {
            Table jumpButtonTable = stage.getRoot().findActor("jumpButtonTable");
            jumpButtonTable.setPosition(stage.getViewport().getScreenWidth() - 140, 120, Align.center);

            Table touchpadTable = stage.getRoot().findActor("touchpadTable");
            touchpadTable.setPosition(230, 210, Align.center);

            attackBtnTable.setPosition(stage.getViewport().getScreenWidth() - 100, stage.getViewport().getScreenHeight() - 100, Align.center);
        }
    }
}