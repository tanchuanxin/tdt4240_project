package com.tnig.game.controller.game_objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tnig.game.model.models.Model;
import com.tnig.game.model.physics_engine.Engine;
import com.tnig.game.view.AnimatedView;
import com.tnig.game.view.View;

/**
 * Interface for the controllers which contains animated views
 */
public interface AnimatedController {

    Model getModel();
    AnimatedView getView();
    boolean isDisposable();
    void update(float delta);
}
