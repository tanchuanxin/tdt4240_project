package com.tnig.game.controller.game_objects;

import com.tnig.game.model.models.Model;
import com.tnig.game.view.View;

public abstract class ObjectController implements Controller {

    private Model model;
    private View view;

    public ObjectController() {
        model = createModel();
        view = createView();
    }

    @Override
    public Model getModel() {
        return model;
    }


}
