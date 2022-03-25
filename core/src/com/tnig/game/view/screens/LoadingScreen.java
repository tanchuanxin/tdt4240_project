package com.tnig.game.view.screens;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.tnig.game.utillities.AssetLoader;


public class LoadingScreen extends AbstractScreen {
    public LoadingScreen(OrthographicCamera camera, AssetLoader assetLoader) {
        super(camera, assetLoader);

        // Load loading screen assets synchronously
        assetLoader.getManager().load(assetLoader.BAD_LOGIC_SPLASH);
        assetLoader.getManager().finishLoading();

        // Load all assets asynchronously
        assetLoader.loadAll();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        // If we are done loading, go to main menu screen.
        if(assetLoader.getManager().update()) {
            // Unload splash screen assets as we won't use them again
            assetLoader.getManager().unload(assetLoader.BAD_LOGIC_SPLASH.fileName);

            // Go to main menu screen
            ScreenManager.getInstance().setMainMenuScreen();
        }

        // Display loading information
        float progress = assetLoader.getManager().getProgress();
    }


    @Override
    public void dispose() {
    }
}
