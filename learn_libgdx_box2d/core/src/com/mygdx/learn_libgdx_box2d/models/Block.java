package com.mygdx.learn_libgdx_box2d.models;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.learn_libgdx_box2d.JumpJellyJump;
import com.mygdx.learn_libgdx_box2d.controllers.PlayController;

public class Block extends InteractiveTileObject {
    private Sound sound;

    public Block(PlayController playController, Rectangle bounds) {
        super(playController, bounds, JumpJellyJump.BLOCK_BIT);
        fixture.setUserData(this);
        setCategoryFilter(JumpJellyJump.BLOCK_BIT);
    }

    @Override
    public void onHit() {
        sound = JumpJellyJump.assetManager.get("audio/sounds/bump.wav", Sound.class);
        sound.setVolume(sound.play(), JumpJellyJump.getVolumeLevel());
    }
}
