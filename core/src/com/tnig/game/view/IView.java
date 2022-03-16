package com.tnig.game.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Interface for non-animated views, providing a base reference for drawing sprites to the screen.
 */
public interface IView {
    void render(SpriteBatch batch);
}
