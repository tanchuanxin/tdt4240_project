package com.tnig.game.controller.managers;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.tnig.game.controller.game_maps.GameMap;
import com.tnig.game.controller.game_maps.Level_1;
import com.tnig.game.controller.screens.ScreenName;
import com.tnig.game.utilities.AssetLoader;
import com.tnig.game.utilities.Constants;
import com.tnig.game.controller.screens.GameScreen;
import com.tnig.game.controller.screens.LeaderboardsScreen;
import com.tnig.game.controller.screens.AppLoadingScreen;
import com.tnig.game.controller.screens.MainMenuScreen;
import com.tnig.game.controller.screens.MapSelectScreen;
import com.tnig.game.view.guis.AppLoadingScreenGUI;
import com.tnig.game.view.guis.LeaderboardsScreenGUI;
import com.tnig.game.view.guis.MainMenuScreenGUI;
import com.tnig.game.view.guis.MapSelectScreenGUI;

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
    public void setScreen(ScreenName screenName) {
        switch (screenName) {
            case LOADING:
                game.setScreen(new AppLoadingScreen(camera, assetLoader, new AppLoadingScreenGUI(camera, assetLoader)));
                break;
            case MAIN_MENU:
                game.setScreen(new MainMenuScreen(camera, assetLoader, new MainMenuScreenGUI(camera, assetLoader)));
                break;
            case GAME:
                //TODO: need to fix so that any map can be selected
                GameMap map = new Level_1();
                game.setScreen(new GameScreen(camera, assetLoader, map));
                break;
            case MAP_SELECT:
                game.setScreen(new MapSelectScreen(camera, assetLoader, new MapSelectScreenGUI(camera, assetLoader)));
                break;
            case LEADERBOARDS:
                game.setScreen(new LeaderboardsScreen(camera, assetLoader, new LeaderboardsScreenGUI(camera, assetLoader)));
                break;
            case GAME_OVER:
                throw new IllegalArgumentException("Not implemented yet");
        }
    }
}