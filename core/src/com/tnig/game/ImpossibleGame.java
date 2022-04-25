package com.tnig.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.tnig.game.controller.managers.ScreenManager;
import com.tnig.game.model.networking.NetworkService;
import com.tnig.game.utilities.AssetLoader;
import com.tnig.game.view.screens.ScreenName;

public class ImpossibleGame extends Game {
    private final OrthographicCamera camera;
    private final AssetLoader assetLoader;
    private NetworkService networkService;

    public ImpossibleGame(NetworkService networkService) {
        this.camera = new OrthographicCamera();
        this.assetLoader = new AssetLoader();
        this.networkService = networkService;
    }

    @Override
    public void create() {
        Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());

        // Initialize screen manager and set it to loading screen while we load assets
        camera.setToOrtho(false);
        ScreenManager screenManager = new ScreenManager(this, camera, assetLoader, networkService);

        // Firebase, for high scores and leaderboards
        networkService.updateHighscore();

        // Fire off event to load assets and display loading screen
        screenManager.setScreen(ScreenName.LOADING);
    }

    @Override
    public void dispose() {
        super.dispose();
    }


}
