package com.tnig.game.controller.screens;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.tnig.game.controller.managers.ScreenManager;
import com.tnig.game.utilities.AssetLoader;
import com.tnig.game.utilities.events.AppLoadingCompleteEvent;
import com.tnig.game.utilities.events.EventManager;
import com.tnig.game.view.guis.AppLoadingScreenGUI;
import com.tnig.game.view.guis.GUI;

public class AppLoadingScreen extends AbstractScreen {
    private AppLoadingScreenGUI appLoadingScreenGui;
    private EventManager eventManager;

    public AppLoadingScreen(OrthographicCamera camera, AssetLoader assetLoader, AppLoadingScreenGUI appLoadingScreenGui, EventManager eventManager) {
        super(camera, assetLoader);

        // Load in GUI for this screen
        this.appLoadingScreenGui = appLoadingScreenGui;
        this.eventManager = eventManager;

        // Load in all assets
        assetLoader.loadAll();
    }

    @Override
    public void render(float delta) {
        // If we are done loading, go to main menu screen.
        if (assetLoader.loadingComplete()) {
            eventManager.pushEvent(new AppLoadingCompleteEvent());
        }

        // Clear screen and draw GUI
        super.render(delta);
        appLoadingScreenGui.render(delta);
    }

    @Override
    public void resize(int width, int height) {
        appLoadingScreenGui.resize(width, height);
    }

    @Override
    public void hide() {
        // Dispose this screen and its scenes
        dispose();
    }

    @Override
    public void dispose() {
        // Unload splash screen assets as we won't use them again
        assetLoader.unload(assetLoader.IMG_SPLASH_SCREEN_BG);
        appLoadingScreenGui.dispose();
    }
}
