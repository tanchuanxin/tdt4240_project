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
    public void setScreen(Screen screen) {
        switch (screen) {
            case LOADING:
                game.setScreen(new LoadingScreen(camera, assetLoader));
                break;
            case MAIN_MENU:
                game.setScreen(new MainMenuScreen(camera, assetLoader));
                break;
            case GAME:
                game.setScreen(new GameScreen(camera, assetLoader));
                break;
            case MAP_SELECT:
                game.setScreen(new MapSelectScreen(camera, assetLoader));
                break;
            case HIGH_SCORES:
                game.setScreen(new HighScoresScreen(camera, assetLoader));
        }
    }
}
