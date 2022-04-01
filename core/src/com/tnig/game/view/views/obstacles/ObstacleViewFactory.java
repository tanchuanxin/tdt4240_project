package com.tnig.game.view.views.obstacles;

import com.tnig.game.model.models.Model;
import com.tnig.game.model.models.ObjectType;
import com.tnig.game.model.models.obstacles.Obstacle;
import com.tnig.game.model.models.obstacles.ObstacleType;
import com.tnig.game.view.AnimatedView;
import com.tnig.game.view.views.AnimatedViewFactory;

public class ObstacleViewFactory implements AnimatedViewFactory {

    // Singleton pattern
    private static final ObstacleViewFactory INSTANCE = new ObstacleViewFactory();

    public static ObstacleViewFactory getInstance() {
        return INSTANCE;
    }
    private ObstacleViewFactory() {
    }



    @Override
    public AnimatedView createView(Model model) {
        if (model.getType() != ObjectType.OBSTACLE){
            throw new IllegalArgumentException("Model should be of type OBSTACLE but was: " + model.getType());
        }

        Obstacle obstacle = (Obstacle) model;

        ObstacleType type = obstacle.getObstacleType();
        switch (type){
            case STATIC_TRIANGLE:
                return new StaticTriangleView(model);
            case MOCK_TYPE:
                throw new IllegalArgumentException("Havent implemented type yet");
            default:
                throw new IllegalArgumentException("Havent implemented type yet: " + type);
        }

    }
}
