package com.tnig.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.tnig.game.controller.managers.ScreenManager;
import com.tnig.game.model.networking.Network;
import com.tnig.game.utilities.AssetLoader;
import com.tnig.game.utilities.Constants;
import com.tnig.game.view.screens.ScreenName;

public class ImpossibleGame extends Game {
    private final OrthographicCamera camera;
    private final AssetLoader assetLoader;
    private Network network;

    public ImpossibleGame(Network network) {
        this.camera = new OrthographicCamera();
        this.assetLoader = new AssetLoader();
        this.network = network;
    }

    @Override
    public void create() {
        // Initialize screen manager and set it to loading screen while we load assets
        camera.setToOrtho(false);
        ScreenManager screenManager = new ScreenManager(this, camera, assetLoader, network);

        network.someFunction();
        network.updateHighscore();
        // Fire off event to load assets and display loading screen
        screenManager.setScreen(ScreenName.LOADING);
    }

    @Override
    public void dispose() {
        super.dispose();
    }


}
