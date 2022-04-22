package com.tnig.game.view.screens;

import static com.tnig.game.utilities.Constants.PPM;
import static com.tnig.game.utilities.Constants.VIEWPORT_HEIGHT;
import static com.tnig.game.utilities.Constants.VIEWPORT_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.tnig.game.controller.InputController;
import com.tnig.game.controller.events.Event;
import com.tnig.game.controller.events.EventListener;
import com.tnig.game.controller.events.EventName;
import com.tnig.game.controller.game_initializers.GameInitializer;
import com.tnig.game.controller.game_initializers.NormalGame;
import com.tnig.game.controller.game_objects.dynamic_objects.AnimatedController;
import com.tnig.game.controller.managers.EventManager;
import com.tnig.game.controller.managers.GameManager;
import com.tnig.game.controller.managers.ScreenManager;
import com.tnig.game.controller.map.GameMap;
import com.tnig.game.model.physics_engine.Engine;
import com.tnig.game.model.physics_engine.GameWorld;
import com.tnig.game.utilities.AssetLoader;

import java.util.List;


public class GameScreen extends AbstractScreen implements EventListener {
    private final ScreenManager screenManager;
    private final SpriteBatch batch;
    private final GameManager gameManager;
    private final OrthographicCamera gameCamera;
    private final OrthogonalTiledMapRenderer mapRenderer;
    //private final Viewport viewport;
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
        // Set up game camera and viewport
        // TODO: Fix the hardcoding
        this.gameCamera = new OrthographicCamera(VIEWPORT_WIDTH/2f, VIEWPORT_HEIGHT/2f);
        gameCamera.translate(0, map.getMapHeight() / 2f);
        //viewport = new FitViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT, gameCamera);
        mapRenderer = new OrthogonalTiledMapRenderer(map.getTiledMap(), 1/PPM);
        gameManager = new GameManager(eventManager, assetLoader, map, gameCamera, numberOfPlayers);

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
        gameCamera.position.x = gameManager.getPlayerPosX();
        mapRenderer.setView(gameCamera);
        gameCamera.update(); //update our camera every frame
        batch.setProjectionMatrix(gameCamera.combined); //say the batch to only draw what we see in our camera


        // Update game
        gameManager.update(delta);
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
        //this.viewport.update(width, height);
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



}
