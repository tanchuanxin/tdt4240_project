package com.tnig.game.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.tnig.game.ImpossibleGame;

import static com.tnig.game.utilities.Constants.FPS;
import static com.tnig.game.utilities.Constants.VIEWPORT_HEIGHT;
import static com.tnig.game.utilities.Constants.VIEWPORT_WIDTH;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();

		config.setWindowedMode((int) VIEWPORT_WIDTH, (int) VIEWPORT_HEIGHT);
		config.setForegroundFPS(FPS);


		new Lwjgl3Application(new ImpossibleGame(), config);
	}
}
