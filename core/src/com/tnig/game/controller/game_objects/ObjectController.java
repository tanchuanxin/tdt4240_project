package com.tnig.game.controller.game_objects;

import com.tnig.game.model.models.Model;
import com.tnig.game.model.physics_engine.Engine;
import com.tnig.game.view.View;

public abstract class ObjectController implements Controller {

    protected Model model;
    private View view;


    // Factory methods
    protected abstract Model createModel(Engine engine, float width, float height);
    protected abstract View createView();

    public ObjectController() {

    }

    protected void initController(Model model, View view){
        this.model = model;
        this.view = view;
    }



    @Override
    public Model getModel() {
        return model;
    }


}
