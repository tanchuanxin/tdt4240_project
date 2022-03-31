package com.tnig.game.controller.game_objects;

import com.tnig.game.model.models.Model;
import com.tnig.game.model.models.obstacles.ObstacleFactory;
import com.tnig.game.model.models.obstacles.ObstacleType;
import com.tnig.game.model.physics_engine.Engine;
import com.tnig.game.view.View;

public class ObstacleController extends ObjectController{

    private final ObstacleType type;

    public ObstacleController(Engine engine, float width, float height, ObstacleType type) {
        this.type = type;
        Model model = createModel(engine, width, height);
        View view = createView();
        initController(model, view);
    }


    @Override
    protected Model createModel(Engine engine, float width, float height) {
        return ObstacleFactory.createObstacle(engine, width, height, type);
    }

    @Override
    public View createView() {
        //TODO: IMPLEMENT
        return null;
    }


}
