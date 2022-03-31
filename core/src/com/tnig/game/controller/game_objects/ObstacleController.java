package com.tnig.game.controller.game_objects;

import com.tnig.game.model.models.Model;
import com.tnig.game.model.models.obstacles.ObstacleFactory;
import com.tnig.game.model.models.obstacles.ObstacleType;
import com.tnig.game.model.physics_engine.Engine;
import com.tnig.game.view.View;
import com.tnig.game.view.Views.ModelViewFactory;
import com.tnig.game.view.Views.obstacles.ObstacleViewFactory;

public class ObstacleController extends ObjectController{

    private final ObstacleType type;

    public ObstacleController(Engine engine, float width, float height, ObstacleType type) {
        this.type = type;
        Model model = createModel(engine, width, height);
        View view = createView(model);
        initController(model, view);
    }


    @Override
    protected Model createModel(Engine engine, float width, float height) {
        return ObstacleFactory.createObstacle(engine, width, height, type);
    }

    @Override
    public View createView(Model model) {
        return ObstacleViewFactory.getInstance().createView(model);
    }


}
