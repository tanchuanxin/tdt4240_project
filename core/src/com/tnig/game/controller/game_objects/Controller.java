package com.tnig.game.controller.game_objects;

import com.tnig.game.model.models.Model;

public interface Controller {
    Model getModel();
    boolean isDisposable();
    void update(float delta);

}
