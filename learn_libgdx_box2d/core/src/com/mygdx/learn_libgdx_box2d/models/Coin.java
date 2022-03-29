package com.mygdx.learn_libgdx_box2d.models;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.learn_libgdx_box2d.JumpJellyJump;
import com.mygdx.learn_libgdx_box2d.views.HudView;
import com.mygdx.learn_libgdx_box2d.controllers.PlayController;

public class Coin extends InteractiveTileObject {
    private Sound sound;

    public Coin(PlayController playController, Rectangle bounds) {
        super(playController, bounds, JumpJellyJump.COIN_BIT);
        fixture.setUserData(this);
        fixture.setSensor(true);
        setCategoryFilter(JumpJellyJump.COIN_BIT);
    }

    @Override
    public void onHit() {
        setCategoryFilter(JumpJellyJump.DESTROYED_BIT);
        getCell().setTile(null);
        HudView.addScore(1000);
        sound = JumpJellyJump.assetManager.get("audio/sounds/coin.wav", Sound.class);
        sound.setVolume(sound.play(), JumpJellyJump.getVolumeLevel());
    }
}
