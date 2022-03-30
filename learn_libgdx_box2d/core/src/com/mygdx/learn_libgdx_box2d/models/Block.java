package com.mygdx.learn_libgdx_box2d.models;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.learn_libgdx_box2d.JumpJellyJump;
import com.mygdx.learn_libgdx_box2d.controllers.PlayController;

public class Block extends InteractiveTileObject {
    public Block(PlayController playController, Rectangle bounds) {
        super(playController, bounds, JumpJellyJump.BLOCK_BIT);
        fixture.setUserData(this);
        setCategoryFilter(JumpJellyJump.BLOCK_BIT);
    }

    @Override
    public void onHit() {
    }
}
