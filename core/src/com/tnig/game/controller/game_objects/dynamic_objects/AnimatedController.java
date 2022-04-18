package com.tnig.game.controller.game_objects.dynamic_objects;

import com.tnig.game.controller.game_objects.Controller;
import com.tnig.game.view.AnimatedView;

/**
 * Interface for the controllers which contains animated views
 */
public interface AnimatedController extends Controller {


    AnimatedView getView();

}
