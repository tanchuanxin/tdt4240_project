package com.mygdx.learn_libgdx_box2d.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.learn_libgdx_box2d.LearnLibgdxBox2d;
import com.mygdx.learn_libgdx_box2d.scenes.Hud;
import com.mygdx.learn_libgdx_box2d.screens.PlayScreen;

public class Flag extends InteractiveTileObject {
    public Flag(PlayScreen playScreen, Rectangle bounds) {
        super(playScreen, bounds, LearnLibgdxBox2d.FLAG_BIT);
        fixture.setUserData(this);
        fixture.setSensor(true);
        setCategoryFilter(LearnLibgdxBox2d.FLAG_BIT);
    }

    @Override
    public void onHit() {
        Gdx.app.log("win", "win");
    }
}
