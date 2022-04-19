package com.tnig.game.view.model_views;

import com.tnig.game.model.models.Model;
import com.tnig.game.model.models.ObjectType;
import com.tnig.game.utilities.AssetLoader;
import com.tnig.game.view.AnimatedView;
import com.tnig.game.view.model_views.players.PlayerViewFactory;

public class ViewFactory {

    public static AnimatedView createView(Model model, AssetLoader assetLoader){
        ObjectType type = model.getType().getObjectType();
        switch (type){
            case PLAYER:
                return PlayerViewFactory.createView(model, assetLoader);
            case OBSTACLE:
                throw new IllegalArgumentException("Not created view for Obstacle");
            default:
                throw new IllegalArgumentException("Not created model for type yet: " + type);
        }
    }
}
