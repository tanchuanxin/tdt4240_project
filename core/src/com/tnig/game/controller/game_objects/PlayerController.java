package com.tnig.game.controller.game_objects;

import com.tnig.game.model.models.Model;
import com.tnig.game.model.physics_engine.Engine;
import com.tnig.game.view.AnimatedView;
import com.tnig.game.view.views.player.PlayerViewFactory;

public class PlayerController  extends ObjectController {

    public PlayerController(Engine engine, float x, float y, float width, float height) {
        Model model = createModel(engine, x, y, width, height);
        AnimatedView view = createView(model);
        initController(model, view);
    }

    @Override
    protected Model createModel(Engine engine, float x, float y, float width, float height) {
        return null;
    }

    @Override
    protected AnimatedView createView(Model model) {
        return PlayerViewFactory.getInstance().createView(model);
    }
}
