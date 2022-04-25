package com.tnig.game.view.model_views;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Interface for views, providing a reference for drawing animations to the screen.
 */
public interface View {
    /**
     * Update function to track the frame of animation of a view according to time.
     *
     * @param delta a float representing the interval in seconds from the last frame to the current one.
     */
    void update(float delta);

    /**
     * Render function to draw a sprite to the screen.
     *
     * @param batch a SpriteBatch object that batches sprites to draw to the screen.
     */
    void render(SpriteBatch batch);
}
