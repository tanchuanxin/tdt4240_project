package com.tnig.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.tnig.game.view.screens.ScreenManager;

public class ImpossibleGame extends Game {
	public ImpossibleGame() {
	}

	@Override
	public void create () {
		// Initialize screen manager and set it to loading screen while we load assets
		ScreenManager.getInstance().initialize(this);
		ScreenManager.getInstance().setLoadingScreen();
	}

	@Override
	public void dispose() {
		super.dispose();
	}


}
