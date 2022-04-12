package com.tnig.game.view.guis;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tnig.game.controller.game.GameInitializer;
import com.tnig.game.controller.game.NormalGame;
import com.tnig.game.controller.game_maps.GameMap;
import com.tnig.game.controller.managers.GameManager;
import com.tnig.game.controller.screens.AbstractScreen;
import com.tnig.game.model.physics_engine.Engine;
import com.tnig.game.model.physics_engine.GameWorld;
import com.tnig.game.utilities.AssetLoader;
import com.tnig.game.view.GameRenderer;

public class GameScreenGUI extends AbstractScreen {
    private final Engine engine;
    private final SpriteBatch batch;
    private GameMap map;
    private final GameManager gameManager;
    private final GameRenderer gameRenderer;

    public GameScreenGUI(OrthographicCamera camera, AssetLoader assetLoader, GameMap map) {
        super(camera, assetLoader);
        this.map = map; // TODO: create map classes


        engine = new GameWorld();

        //TODO: Could use strategy pattern here or take in as parameter to change gamemodes at runtime
        //TODO: Probably strategy pattern would be more scalable? Interface for GameMode
        GameInitializer initializer = new NormalGame();
        gameManager = initializer.initGame(engine);

        batch = new SpriteBatch();
        gameRenderer = new GameRenderer(batch, gameManager);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        // Render game
        batch.begin();
        // TODO: IMPLEMENT
        gameRenderer.renderAnimatedViews();
        batch.end();

        // Update game
        engine.update(delta);
        gameManager.update(delta);

        // TODO: Listen to event instead
        if (gameManager.gameFinished()){


        }

        //stage.act();
        //stage.draw();
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
