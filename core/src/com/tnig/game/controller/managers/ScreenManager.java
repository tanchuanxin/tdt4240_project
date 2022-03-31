package com.tnig.game.controller.managers;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.tnig.game.controller.GameMaps.GameMap;
import com.tnig.game.controller.GameMaps.Level_1;
import com.tnig.game.utillities.AssetLoader;
import com.tnig.game.utillities.Constants;
import com.tnig.game.view.screens.GameScreen;
import com.tnig.game.view.screens.HighScoresScreen;
import com.tnig.game.view.screens.LoadingScreen;
import com.tnig.game.view.screens.MainMenuScreen;
import com.tnig.game.view.screens.MapSelectScreen;
import com.tnig.game.view.screens.Screen;

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
                //TODO: need to fix so that any map can be selected
                GameMap map = new Level_1();
                game.setScreen(new GameScreen(camera, assetLoader, map));
                break;
            case MAP_SELECT:
                game.setScreen(new MapSelectScreen(camera, assetLoader));
                break;
            case HIGH_SCORES:
                game.setScreen(new HighScoresScreen(camera, assetLoader));
                break;
            case GAME_OVER:
                throw new IllegalArgumentException("Not implemented yet");

        }
    }
}
