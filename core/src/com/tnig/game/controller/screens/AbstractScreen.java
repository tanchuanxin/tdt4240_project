package com.tnig.game.controller.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.tnig.game.utilities.AssetLoader;
import com.tnig.game.view.guis.GUI;

/**
 * Abstract class that provides a convenient implementation of Screen.
 * Includes generalized dependencies for a Screen.
 */
public abstract class AbstractScreen implements Screen {
    protected final OrthographicCamera camera;
    protected final AssetLoader assetLoader;

    public AbstractScreen(OrthographicCamera camera, AssetLoader assetLoader) {
        this.camera = camera;
        this.assetLoader = assetLoader;
    }

    @Override
    public void render(float delta) {
        // Clears screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }
}
