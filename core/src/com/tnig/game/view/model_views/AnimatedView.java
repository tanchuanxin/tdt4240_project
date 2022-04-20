package com.tnig.game.view.model_views;


/**
 * Interface for animated views, providing a reference for drawing animations to the screen. Extends IView.
 */
public interface AnimatedView extends View {
    /**
     * Update function to track the frame of animation of a view according to time.
     *
     * @param delta a float representing the interval in seconds from the last frame to the current one.
     */
    void update(float delta);
}
