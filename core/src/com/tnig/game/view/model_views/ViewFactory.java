package com.tnig.game.view.model_views;

import com.tnig.game.model.models.interfaces.Model;
import com.tnig.game.model.models.enums.ObjectType;
import com.tnig.game.utilities.AssetLoader;
import com.tnig.game.view.model_views.obstacles.ObstacleViewFactory;
import com.tnig.game.view.model_views.players.PlayerViewFactory;

public class ViewFactory {

    public static View createView(Model model, AssetLoader assetLoader){
        ObjectType type = model.getType().getObjectType();
        switch (type){
            case PLAYER:
                return PlayerViewFactory.createView(model, assetLoader);
            case OBSTACLE:
                return ObstacleViewFactory.createView(model, assetLoader);
            default:
                throw new IllegalArgumentException("Not created model for type yet: " + type);
        }
    }
}
