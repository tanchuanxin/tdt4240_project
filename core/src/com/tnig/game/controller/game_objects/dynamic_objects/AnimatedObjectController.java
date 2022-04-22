package com.tnig.game.controller.game_objects.dynamic_objects;

import com.tnig.game.controller.managers.EventManager;
import com.tnig.game.model.models.interfaces.Model;
import com.tnig.game.model.models.ModelFactory;
import com.tnig.game.model.models.interfaces.ModelType;
import com.tnig.game.model.physics_engine.Engine;
import com.tnig.game.utilities.AssetLoader;
import com.tnig.game.view.model_views.AnimatedView;
import com.tnig.game.view.model_views.ViewFactory;

public class AnimatedObjectController implements AnimatedController {

    private final Model model;
    private final AnimatedView view;


    public AnimatedObjectController(EventManager eventManager,
                                    Engine engine,
                                    AssetLoader assetLoader,
                                    float x, float y, float width, float height,
                                    ModelType type) {
        model = ModelFactory.createModel(eventManager, engine, x, y, width, height, type);
        view = ViewFactory.createView(model, assetLoader);
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
