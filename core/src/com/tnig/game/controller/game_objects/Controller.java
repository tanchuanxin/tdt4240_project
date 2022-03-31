package com.tnig.game.controller.game_objects;

import com.tnig.game.model.models.Model;
import com.tnig.game.view.View;

/**
 * Interface for the controller classes. Contains factory methods for object creations.
 */
public interface Controller {

    Model getModel();

    // Factory methods
    Model createModel();
    View createView();
}
