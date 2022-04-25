package com.tnig.game.controller.game_objects.dynamic_objects;

import com.tnig.game.controller.game_objects.Controller;
import com.tnig.game.view.model_views.View;

/**
 * Interface for the controllers which contains animated views
 */
public interface AnimatedController extends Controller {
    View getView();
}
