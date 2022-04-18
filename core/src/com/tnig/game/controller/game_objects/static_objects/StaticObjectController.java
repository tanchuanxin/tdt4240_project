package com.tnig.game.controller.game_objects.static_objects;

import com.tnig.game.controller.game_objects.Controller;
import com.tnig.game.model.models.Model;
import com.tnig.game.model.physics_engine.Engine;
import com.tnig.game.view.AnimatedView;

public abstract class StaticObjectController implements Controller {

    protected Model model;


    // Factory methods
    protected abstract Model createModel(Engine engine, float x, float y, float width, float height);


    protected void initController(Model model){
        this.model = model;
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
    }


}
