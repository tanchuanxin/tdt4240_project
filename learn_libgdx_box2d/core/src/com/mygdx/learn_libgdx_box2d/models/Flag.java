package com.mygdx.learn_libgdx_box2d.models;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.learn_libgdx_box2d.JumpJellyJump;
import com.mygdx.learn_libgdx_box2d.views.HudView;
import com.mygdx.learn_libgdx_box2d.controllers.PlayController;

public class Flag extends InteractiveTileObject {
    private Sound sound;

    public Flag(PlayController playController, Rectangle bounds) {
        super(playController, bounds, JumpJellyJump.FLAG_BIT);
        fixture.setUserData(this);
        fixture.setSensor(true);
        setCategoryFilter(JumpJellyJump.FLAG_BIT);
    }

    @Override
    public void onHit() {
        HudView.addScore(1000);
        sound = JumpJellyJump.assetManager.get("audio/sounds/win.mp3", Sound.class);
        sound.setVolume(sound.play(), JumpJellyJump.getVolumeLevel());
    }
}
