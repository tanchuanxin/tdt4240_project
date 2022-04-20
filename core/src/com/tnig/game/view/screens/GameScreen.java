package com.tnig.game.view.screens;

import static com.tnig.game.utilities.Constants.PPM;
import static com.tnig.game.utilities.Constants.VIEWPORT_HEIGHT;
import static com.tnig.game.utilities.Constants.VIEWPORT_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.tnig.game.controller.game.GameInitializer;
import com.tnig.game.controller.game.NormalGame;
import com.tnig.game.controller.managers.EventManager;
import com.tnig.game.controller.managers.GameManager;
import com.tnig.game.controller.managers.ScreenManager;
import com.tnig.game.controller.map.GameMap;
import com.tnig.game.model.physics_engine.Engine;
import com.tnig.game.model.physics_engine.GameWorld;
import com.tnig.game.utilities.AssetLoader;
import com.tnig.game.view.GameRenderer;


public class GameScreen extends AbstractScreen {
    private final ScreenManager screenManager;
    private final Engine engine;
    private final SpriteBatch batch;
    private final GameManager gameManager;
    private final GameRenderer gameRenderer;
    private final OrthographicCamera gameCamera;
    private final OrthogonalTiledMapRenderer mapRenderer;
    //private final Viewport viewport;


    public GameScreen(ScreenManager screenManager,
                      EventManager eventManager,
                      OrthographicCamera camera,
                      AssetLoader assetLoader,
                      GameMap map,
                      int numberOfPlayers) {
        super(camera, assetLoader);
        this.screenManager = screenManager;
        this.batch = new SpriteBatch();
        // Set up game camera and viewport
        // TODO: Fix the hardcoding
        this.gameCamera = new OrthographicCamera(VIEWPORT_WIDTH/2.7f, VIEWPORT_HEIGHT/2.7f);
        System.out.println(map.getMapHeight());
        System.out.println(map.getMapWidth());
        gameCamera.translate(0, map.getMapHeight() / 2f);
        //viewport = new FitViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT, gameCamera);
        engine = new GameWorld(gameCamera);

        mapRenderer = new OrthogonalTiledMapRenderer(map.getTiledMap(), 1/PPM);



        GameInitializer initializer = new NormalGame();
        this.gameManager = initializer.initGame(eventManager, engine, assetLoader, map, numberOfPlayers);

        this.gameRenderer = new GameRenderer(batch, gameManager);


        // Create viewport
        //viewport = new FitViewport(mapWidth, mapHeight, this.gameCamera);



    }

    private void update(float delta){
        gameCamera.position.x = gameManager.getPlayerPosX();
        mapRenderer.setView(gameCamera);
        gameCamera.update(); //update our camera every frame
        batch.setProjectionMatrix(gameCamera.combined); //say the batch to only draw what we see in our camera


        // Update game
        engine.update(delta);
        gameManager.update(delta);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(delta);

        // Render game
        mapRenderer.render();
        batch.begin();
        gameRenderer.render();
        batch.end();

        if (gameManager.gameFinished()){
            // TODO: Push event game finished
        }


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
        engine.dispose();
        gameManager.dispose();
        batch.dispose();
    }


}
