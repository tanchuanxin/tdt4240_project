package com.tnig.game.view.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.tnig.game.controller.game.GameInitializer;
import com.tnig.game.controller.game.NormalGame;
import com.tnig.game.controller.managers.EventManager;
import com.tnig.game.controller.managers.GameManager;
import com.tnig.game.controller.managers.ScreenManager;
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


    public GameScreen(ScreenManager screenManager,
                      EventManager eventManager,
                      OrthographicCamera camera,
                      AssetLoader assetLoader,
                      TiledMap map,
                      int players) {
        super(camera, assetLoader);
        this.screenManager = screenManager;
        engine = new GameWorld();

        //TODO: Could use strategy pattern here or take in as parameter to change gamemodes at runtime
        //TODO: Probably strategy pattern would be more scalable? Interface for GameMode
        GameInitializer initializer = new NormalGame();
        gameManager = initializer.initGame(eventManager, engine, assetLoader, map, players);

        batch = new SpriteBatch();
        gameRenderer = new GameRenderer(batch, gameManager, map, assetLoader);


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        // Render game
        batch.begin();
        gameRenderer.render();
        batch.end();

        // Update game
        engine.update(delta);
        gameManager.update(delta);


        if (gameManager.gameFinished()){
            // TODO: Push event game finished
        }


    }

    @Override
    public void resize(int width, int height) {
        //stage.getViewport().update(width, height, true);
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
