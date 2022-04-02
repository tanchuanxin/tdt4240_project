package com.tnig.game;

import static com.tnig.game.utillities.Constants.FPS;
import static com.tnig.game.utillities.Constants.VIEWPORT_HEIGHT;
import static com.tnig.game.utillities.Constants.VIEWPORT_WIDTH;

import android.os.Bundle;
import android.util.Log;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.tnig.game.model.networking.FirebasePlayer;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new ImpossibleGame(), config);

		//Koden under er for testing av databasen.
		AndroidFirebaseInterface fbi = new AndroidFirebaseInterface();
		FirebasePlayer p1 = new FirebasePlayer();
		p1.setName("p1");
		p1.setScore(133000);
		fbi.pushHighscore(1,p1);
		FirebasePlayer p2 = new FirebasePlayer();
		p2.setName("p2");
		p2.setScore(10030);
		fbi.pushHighscore(2,p2);
		fbi.updateHighscore();
		Log.d("run", "hello " + fbi.getHighScore(2));
	}
}
