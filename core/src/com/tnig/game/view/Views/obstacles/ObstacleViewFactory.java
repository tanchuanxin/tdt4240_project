package com.tnig.game.view.Views.obstacles;

import com.tnig.game.model.models.Model;
import com.tnig.game.model.physics_engine.BodyBuilder.ObstacleBody;
import com.tnig.game.view.View;
import com.tnig.game.view.Views.ModelViewFactory;

public class ObstacleViewFactory implements ModelViewFactory {

    // Singleton pattern
    private static final ObstacleViewFactory INSTANCE = new ObstacleViewFactory();

    public static ObstacleViewFactory getInstance() {
        return INSTANCE;
    }
    private ObstacleViewFactory() {
    }



    @Override
    public View createView(Model model) {
        return null;
    }
}
