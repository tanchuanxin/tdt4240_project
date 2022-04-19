package com.tnig.game.view.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tnig.game.utilities.AssetLoader;

/**
 * Abstract class that provides a convenient implementation of Screen.
 * Includes generalized dependencies for a Screen.
 */
public abstract class AbstractScreen extends ScreenAdapter {
    protected final OrthographicCamera camera;
    protected final AssetLoader assetLoader;
    protected final Stage stage;


    public AbstractScreen(OrthographicCamera camera, AssetLoader assetLoader) {
        this.camera = camera;
        this.assetLoader = assetLoader;

        // Initialize stage for UI drawing
        stage = new Stage(new ScreenViewport(camera));

        // Set input processor to be this current stage
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        // Clears screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
