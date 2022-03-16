package com.tnig.game.view;

import com.tnig.game.view.IView;

/**
 * Interface for animated views, providing a reference for drawing animations to the screen. Extends IView.
 */
public interface IAnimatedView extends IView {
    /**
     * Update function to track the frame of animation of a view according to time.
     *
     * @param deltaTime a float representing the interval in seconds from the last frame to the current one.
     */
    void update(float deltaTime);
}
