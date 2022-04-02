package com.tnig.game.controller.game_objects;

import com.tnig.game.model.models.Model;
import com.tnig.game.model.models.obstacles.ObstacleFactory;
import com.tnig.game.model.models.obstacles.ObstacleType;
import com.tnig.game.model.physics_engine.Engine;
import com.tnig.game.view.AnimatedView;
import com.tnig.game.view.views.obstacles.ObstacleViewFactory;

public class ObstacleController extends ObjectController{

    private final ObstacleType type;

    public ObstacleController(Engine engine, float x, float y, float width, float height, ObstacleType type) {
        this.type = type;
        Model model = createModel(engine, x, y, width, height);
        AnimatedView view = createView(model);
        initController(model, view);
    }


    @Override
    protected Model createModel(Engine engine, float x, float y, float width, float height) {
        return ObstacleFactory.createObstacle(engine, x, y, width, height, type);
    }

    @Override
    public AnimatedView createView(Model model) {
        return ObstacleViewFactory.getInstance().createView(model);
    }


}
