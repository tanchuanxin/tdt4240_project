package com.tnig.game.view.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
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
    //private final  FitViewport viewport;


    public GameScreen(ScreenManager screenManager,
                      EventManager eventManager,
                      OrthographicCamera camera,
                      AssetLoader assetLoader,
                      GameMap map,
                      int numberOfPlayers) {
        super(camera, assetLoader);
        this.screenManager = screenManager;
        // Set up game camera and viewport
        this.gameCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        int mapWidth = map.getMapWidth();
        int mapHeight = map.getMapHeight();

        camera.position.set(mapWidth/20f, mapHeight/2f, 0);
        camera.update();

        engine = new GameWorld(map);

        GameInitializer initializer = new NormalGame();
        this.gameManager = initializer.initGame(eventManager, engine, assetLoader, map, numberOfPlayers);

        this.batch = new SpriteBatch();


        mapRenderer = new OrthogonalTiledMapRenderer(map.getTiledMap());

        // Create viewport
        //viewport = new FitViewport(mapWidth, mapHeight, this.gameCamera);

        this.gameRenderer = new GameRenderer(batch, gameManager);


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mapRenderer.setView(camera);
        camera.update(); //update our camera every frame
        batch.setProjectionMatrix(camera.combined); //say the batch to only draw what we see in our camera


        // Update game
        engine.update(delta);
        gameManager.update(delta);

        // Render game
        batch.begin();
        gameRenderer.render();
        mapRenderer.render();
        batch.end();

        if (gameManager.gameFinished()){
            // TODO: Push event game finished
        }


    }

    @Override
    public void resize(int width, int height) {
        //viewport.update(width, height);
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
