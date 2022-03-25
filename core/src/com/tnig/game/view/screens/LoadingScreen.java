package com.tnig.game.view.screens;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tnig.game.utillities.AssetLoader;


public class LoadingScreen extends AbstractScreen {
    private Stage stage;

    public LoadingScreen(OrthographicCamera camera, AssetLoader assetLoader) {
        super(camera, assetLoader);
        stage = new Stage(new ScreenViewport());

        // Load loading screen assets synchronously
        assetLoader.getManager().load(assetLoader.FONT_SOURCE_SANS_PRO_REGULAR);
        assetLoader.getManager().load(assetLoader.IMG_SPLASH_SCREEN_BG);
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
            // Go to main menu screen
            ScreenManager.getInstance().setMainMenuScreen();

            // Unload splash screen assets as we won't use them again
            assetLoader.getManager().unload(assetLoader.IMG_SPLASH_SCREEN_BG.fileName);
        }

        // Display loading information
        float progress = assetLoader.getManager().getProgress();
    }


    @Override
    public void dispose() {
    }
}
