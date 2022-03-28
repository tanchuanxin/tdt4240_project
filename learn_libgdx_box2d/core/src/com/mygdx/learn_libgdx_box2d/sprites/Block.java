package com.mygdx.learn_libgdx_box2d.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.learn_libgdx_box2d.LearnLibgdxBox2d;
import com.mygdx.learn_libgdx_box2d.screens.PlayScreen;

public class Block extends InteractiveTileObject {
    private Sound sound;

    public Block(PlayScreen playScreen, Rectangle bounds) {
        super(playScreen, bounds, LearnLibgdxBox2d.BLOCK_BIT);
        fixture.setUserData(this);
        setCategoryFilter(LearnLibgdxBox2d.BLOCK_BIT);
    }

    @Override
    public void onHit() {
        sound = LearnLibgdxBox2d.assetManager.get("audio/sounds/bump.wav", Sound.class);
        sound.setVolume(sound.play(), 0.1f);
    }
}
