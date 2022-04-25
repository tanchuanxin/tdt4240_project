package com.tnig.game.controller.game_objects.dynamic_objects;

import com.tnig.game.controller.managers.EventManager;
import com.tnig.game.model.models.ObjectProperties;
import com.tnig.game.model.models.interfaces.Model;
import com.tnig.game.model.models.ModelFactory;
import com.tnig.game.model.models.interfaces.ModelType;
import com.tnig.game.model.physics_engine.Engine;
import com.tnig.game.utilities.AssetLoader;
import com.tnig.game.view.model_views.View;
import com.tnig.game.view.model_views.ViewFactory;

public class AnimatedObjectController implements AnimatedController {
    private final Model model;
    private final View view;

    public AnimatedObjectController(EventManager eventManager,
                                    Engine engine,
                                    AssetLoader assetLoader,
                                    float x, float y, float width, float height,
                                    ObjectProperties properties, ModelType type) {
        model = ModelFactory.createModel(eventManager, engine, assetLoader, x, y, width, height, properties, type);
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
    public View getView() {
        return view;
    }
}
