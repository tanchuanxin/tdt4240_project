package com.tnig.game.controller.game_objects;

import com.tnig.game.model.models.Model;
import com.tnig.game.view.AnimatedView;

public interface Controller {
    Model getModel();
    boolean isDisposable();
    void update(float delta);

}
