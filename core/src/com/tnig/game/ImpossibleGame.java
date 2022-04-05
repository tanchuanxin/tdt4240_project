package com.tnig.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.tnig.game.controller.managers.ScreenManager;
import com.tnig.game.controller.screens.ScreenName;
import com.tnig.game.utilities.AssetLoader;
import com.tnig.game.utilities.Constants;
import com.tnig.game.utilities.events.EventManager;
import com.tnig.game.utilities.events.InitAppEvent;

public class ImpossibleGame extends Game {
    private final EventManager eventManager;
    private final OrthographicCamera camera;
    private final AssetLoader assetLoader;

    public ImpossibleGame() {
        this.eventManager = new EventManager();
        this.camera = new OrthographicCamera();
        this.assetLoader = new AssetLoader();
    }

    @Override
    public void create() {
        // Initialize screen manager and set it to loading screen while we load assets
        camera.setToOrtho(false, Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
        ScreenManager screenManager = new ScreenManager(this, eventManager, camera, assetLoader);

        // Fire off event to load assets and display loading screen
        eventManager.pushEvent(new InitAppEvent());
    }

    @Override
    public void dispose() {
        super.dispose();
    }


}
