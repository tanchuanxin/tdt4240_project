package com.mygdx.learn_libgdx_box2d.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.learn_libgdx_box2d.LearnLibgdxBox2d;
import com.mygdx.learn_libgdx_box2d.scenes.Hud;
import com.mygdx.learn_libgdx_box2d.screens.PlayScreen;

public class Coin extends InteractiveTileObject {
    private Sound sound;

    public Coin(PlayScreen playScreen, Rectangle bounds) {
        super(playScreen, bounds, LearnLibgdxBox2d.COIN_BIT);
        fixture.setUserData(this);
        fixture.setSensor(true);
        setCategoryFilter(LearnLibgdxBox2d.COIN_BIT);
    }

    @Override
    public void onHit() {
        setCategoryFilter(LearnLibgdxBox2d.DESTROYED_BIT);
        getCell().setTile(null);
        Hud.addScore(1000);
        sound = LearnLibgdxBox2d.assetManager.get("audio/sounds/coin.wav", Sound.class);
        sound.setVolume(sound.play(), 0.1f);
    }
}
