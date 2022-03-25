package com.tnig.game.view.screens;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.tnig.game.utillities.AssetLoader;

public class MainMenuScreen extends AbstractScreen {
    public MainMenuScreen(OrthographicCamera camera, AssetLoader assetLoader) {
        super(camera, assetLoader);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if (assetLoader.getManager().update()) {
            // We are done loading, go to main menu screen.
        }

        // display loading information
        float progress = assetLoader.getManager().getProgress();
    }

    @Override
    public void dispose() {
    }
}
