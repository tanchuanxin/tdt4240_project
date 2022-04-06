package com.tnig.game;

import android.os.Bundle;
import android.util.Log;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.tnig.game.model.networking.PlayerData;
import com.tnig.game.model.networking.Network;


public class AndroidLauncher extends AndroidApplication {

	AndroidFirebaseInterface network;
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		network = new AndroidFirebaseInterface();
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new ImpossibleGame(network), config);

		/*    Code to create database content for testing.
		AndroidFirebaseInterface f = new AndroidFirebaseInterface();
		PlayerData p = new PlayerData();
		for (int i = 1; i < 4; i++){
			p.setName("p" + i);
			p.setScore((i+1)*12);
			f.pushHighscore(i,p);
		}
		 */

	}
}
