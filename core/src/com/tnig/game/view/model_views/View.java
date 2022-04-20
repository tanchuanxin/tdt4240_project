package com.tnig.game.view.model_views;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tnig.game.utilities.AssetLoader;

/**
 * Interface for views, providing a base reference for drawing sprites to the screen.
 */
public interface View {
    /**
     * Render function to draw a sprite to the screen.
     *
     * @param batch a SpriteBatch object that batches sprites to draw to the screen.
     * @param assetLoader
     */
    void render(SpriteBatch batch, AssetLoader assetLoader);
}
