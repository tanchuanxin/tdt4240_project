package com.tnig.game.controller.game_objects.static_objects;

import com.tnig.game.controller.game_objects.Controller;
import com.tnig.game.controller.managers.EventManager;
import com.tnig.game.model.models.interfaces.Model;
import com.tnig.game.model.models.ModelFactory;
import com.tnig.game.model.models.interfaces.ModelType;
import com.tnig.game.model.physics_engine.Engine;

public class StaticObjectController implements Controller {

    private final Model model;

    public StaticObjectController(EventManager eventManager, Engine engine,
                                  float x, float y, float width, float height, ModelType type) {
        model = ModelFactory.createModel(eventManager, engine, x, y, width, height, type);
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
