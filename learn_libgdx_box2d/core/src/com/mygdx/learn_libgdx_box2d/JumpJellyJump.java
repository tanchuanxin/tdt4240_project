package com.mygdx.learn_libgdx_box2d;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.learn_libgdx_box2d.controllers.MenuController;

public class JumpJellyJump extends Game {
	public static final int V_WIDTH = 400;
	public static final int V_HEIGHT = 208;
	public static final float PPM = 100; // pixels per meter to resolve box2d scaling

	// for collision bitwise calculations
	public static final short NOTHING_BIT = 0;
	public static final short DEFAULT_BIT = 1;
	public static final short JELLY_BIT = 2;
	public static final short FLAG_BIT = 4;
	public static final short BLOCK_BIT = 8;
	public static final short COIN_BIT = 16;
	public static final short DESTROYED_BIT = 32;
	public static final short ENEMY_BIT = 64;

	public static float VOLUME_LEVEL = 0.1f;

	public SpriteBatch batch;

	// TODO - change implementation to non-static way
	public static AssetManager assetManager;

	public static float scale(float meters) {
		return meters/PPM;
	}

	public static float scale_reverse(float meters) {
		return meters*PPM;
	}

	public static float getVolumeLevel() {
		return VOLUME_LEVEL;
	}

	public static void setVolumeLevel(float volumeLevel) {
		VOLUME_LEVEL = volumeLevel;

		if (VOLUME_LEVEL <= 0f) {
			VOLUME_LEVEL = 0f;
		} else if (VOLUME_LEVEL >= 1f) {
			VOLUME_LEVEL = 1f;
		}
	}

	@Override
	public void create () {
		// one sprite batch for the whole game because it is memory intensive
		batch = new SpriteBatch();

		// load audio assets
		assetManager = new AssetManager();
		assetManager.load("audio/music/fun.mp3", Music.class);
		assetManager.load("audio/music/adventure.mp3", Music.class);
		assetManager.load("audio/sounds/bump.wav", Sound.class);
		assetManager.load("audio/sounds/coin.wav", Sound.class);
		assetManager.load("audio/sounds/die.mp3", Sound.class);
		assetManager.load("audio/sounds/jump.ogg", Sound.class);
		assetManager.load("audio/sounds/punch.mp3", Sound.class);
		assetManager.load("audio/sounds/win.mp3", Sound.class);


		assetManager.finishLoading();
		setScreen(new MenuController(this));
	}

	@Override
	public void render () {
		// delegate render method to whatever screen is active
		super.render();
		assetManager.update();
	}
	
	@Override
	public void dispose () {
		super.dispose();
		assetManager.dispose();
		batch.dispose();
	}
}
