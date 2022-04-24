package com.tnig.game.view.model_views.obstacles;

import com.tnig.game.model.models.interfaces.Model;
import com.tnig.game.model.models.obstacles.ObstacleType;
import com.tnig.game.model.models.players.PlayerType;
import com.tnig.game.utilities.AssetLoader;
import com.tnig.game.view.model_views.AnimatedView;
import com.tnig.game.view.model_views.players.PlayerView;

public class ObstacleViewFactory {

    public static AnimatedView createView(Model model, AssetLoader assetLoader){

        ObstacleType type = (ObstacleType) model.getType();
        switch (type){
            case FIREBALL:
            case FIREBALL_ALTERNATING:
                return new FireballView(model, assetLoader);
            case MOCK:
                throw new IllegalArgumentException("Not created view for Mock");
            default:
                throw new IllegalArgumentException("Not created view for type yet: " + type);
        }
    }
}
