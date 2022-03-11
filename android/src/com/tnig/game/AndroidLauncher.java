package com.tnig.game;

import static com.tnig.game.utillities.Constants.FPS;
import static com.tnig.game.utillities.Constants.VIEWPORT_HEIGHT;
import static com.tnig.game.utillities.Constants.VIEWPORT_WIDTH;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new ImpossibleGame(), config);
	}
}
