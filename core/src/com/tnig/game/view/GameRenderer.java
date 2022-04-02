package com.tnig.game.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * This class encapsulates the LibGDX Spritebatch module
 */
public class GameRenderer {

    private final SpriteBatch batch;

    public GameRenderer(SpriteBatch batch) {
        this.batch = batch;
    }

    public void render(AnimatedView view){
        view.render(batch);
    }
}
