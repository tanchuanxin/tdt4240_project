package com.tnig.game;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.tnig.game.model.networking.PlayerData;
import com.tnig.game.model.networking.INetworkService;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new ImpossibleGame(), config);

		//Koden under er for testing av databasen.
		INetworkService fbi = new AndroidFirebaseInterface();
		PlayerData p1 = new PlayerData();
		p1.setName("rubbldld");
		p1.setScore(133000);
		fbi.pushHighscore(1,p1);
		PlayerData p2 = new PlayerData();
		p2.setName("rubatub");
		p2.setScore(10030);
		fbi.pushHighscore(1,p2);
	}
}
