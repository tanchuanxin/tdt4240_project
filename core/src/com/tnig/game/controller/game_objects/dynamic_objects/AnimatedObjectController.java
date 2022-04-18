package com.tnig.game.controller.game_objects.dynamic_objects;

import com.tnig.game.model.models.Model;
import com.tnig.game.model.physics_engine.Engine;
import com.tnig.game.view.AnimatedView;

public abstract class AnimatedObjectController implements AnimatedController {

    protected Model model;
    private AnimatedView view;


    // Factory methods
    protected abstract Model createModel(Engine engine, float x, float y, float width, float height);
    protected abstract AnimatedView createView(Model model);

    protected void initController(Model model, AnimatedView view){
        this.model = model;
        this.view = view;
    }


    public boolean isDisposable(){
        return model.isDisposable();
    }

    @Override
    public Model getModel() {
        return model;
    }


    @Override
    public void update(float delta) {
        model.update(delta);
        view.update(delta);
    }

    @Override
    public AnimatedView getView() {
        return view;
    }
}
