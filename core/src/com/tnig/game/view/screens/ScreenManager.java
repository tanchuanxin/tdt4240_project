package com.tnig.game.view.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.tnig.game.utillities.AssetLoader;
import com.tnig.game.utillities.Constants;

public class ScreenManager {

    private Game game;
    private final OrthographicCamera camera = new OrthographicCamera();
    private final AssetLoader assetLoader = new AssetLoader();
    private static final ScreenManager INSTANCE = new ScreenManager();

    public static ScreenManager getInstance() {
        return INSTANCE;
    }

    private ScreenManager() {
        camera.setToOrtho(false, Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
    }

    // This is called by Game from inside the "create()" method.
    public void initialize(Game game) {
        this.game = game;
    }

    // Switch screens
    public void setLoadingScreen() {
        game.setScreen(new LoadingScreen(camera, assetLoader));
    }

    public void setMainMenuScreen() {
        game.setScreen(new MainMenuScreen(camera, assetLoader));
    }

    public void setGameScreen() {
        game.setScreen(new GameScreen(camera, assetLoader));
    }
}
