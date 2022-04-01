package com.tnig.game;

import com.badlogic.gdx.Game;
import com.tnig.game.controller.Managers.ScreenManager;
import com.tnig.game.view.screens.Screen;

public class ImpossibleGame extends Game {
    public ImpossibleGame() {
    }

    @Override
    public void create() {
        // Initialize screen manager and set it to loading screen while we load assets
        ScreenManager.getInstance().initialize(this);
        ScreenManager.getInstance().setScreen(Screen.LOADING);
    }

    @Override
    public void dispose() {
        super.dispose();
    }


}
