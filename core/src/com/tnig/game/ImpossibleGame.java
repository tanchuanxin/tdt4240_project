package com.tnig.game;

import com.badlogic.gdx.Game;
import com.tnig.game.controller.managers.ScreenManager;
import com.tnig.game.controller.screens.ScreenName;

public class ImpossibleGame extends Game {
    public ImpossibleGame() {
    }

    @Override
    public void create() {
        // Initialize screen manager and set it to loading screen while we load assets
        ScreenManager.getInstance().initialize(this);
        ScreenManager.getInstance().setScreen(ScreenName.LOADING);
    }

    @Override
    public void dispose() {
        super.dispose();
    }


}
