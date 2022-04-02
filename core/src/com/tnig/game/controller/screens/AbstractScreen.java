package com.tnig.game.controller.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.tnig.game.utilities.AssetLoader;

/**
 * Abstract class that provides a convenient implementation of ScreenName.
 * Includes generalized dependencies for a ScreenName.
 */
public abstract class AbstractScreen implements Screen {
    protected final OrthographicCamera camera;
    protected final AssetLoader assetLoader;

    public AbstractScreen(OrthographicCamera camera, AssetLoader assetLoader) {
        this.camera = camera;
        this.assetLoader = assetLoader;
        this.init();
    }

    // Initializes the screen
    public void init() {

    }

    @Override
    public void render(float delta) {
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
