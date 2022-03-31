package com.tnig.game.controller.game_objects;

import com.tnig.game.model.models.Model;
import com.tnig.game.model.models.obstacles.ObstacleType;
import com.tnig.game.view.View;

public class ObstacleController extends ObjectController{

    private final ObstacleType type;

    public ObstacleController(ObstacleType type) {
        this.type = type;
    }

    @Override
    public Model createModel() {
        return null;
    }

    @Override
    public View createView() {
        return null;
    }


}
