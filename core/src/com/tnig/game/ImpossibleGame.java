package com.tnig.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.tnig.game.view.screens.ScreenManager;

public class ImpossibleGame extends Game {
	public ImpossibleGame() {
	}

	@Override
	public void create () {

		ScreenManager.getInstance().initialize(this);

		ScreenManager.getInstance().setLoadingScreen();
	}

	@Override
	public void dispose() {
		super.dispose();
	}


}
