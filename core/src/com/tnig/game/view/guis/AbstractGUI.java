package com.tnig.game.view.guis;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tnig.game.utilities.AssetLoader;

// Convenience implementation of a stage for custom GUIs
public class AbstractGUI implements GUI {
    protected final Stage stage;
    protected final OrthographicCamera camera;
    protected final AssetLoader assetLoader;

    public AbstractGUI(OrthographicCamera camera, AssetLoader assetLoader) {
        this.camera = camera;
        this.assetLoader = assetLoader;

        // Initialize stage for UI drawing
        stage = new Stage(new ScreenViewport(camera));

        // Set input processor to be this current stage
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
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
