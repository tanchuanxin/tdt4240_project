package com.tnig.game.view.model_views.players;

import com.tnig.game.model.models.Model;
import com.tnig.game.model.models.players.PlayerType;
import com.tnig.game.utilities.AssetLoader;
import com.tnig.game.view.AnimatedView;

public class PlayerViewFactory {

    public static AnimatedView createView(Model model, AssetLoader assetLoader){

        PlayerType type = (PlayerType) model.getType();
        switch (type){
            case NORMALPLAYER:
                return new PlayerView(model, assetLoader);
            case MOCK:
                throw new IllegalArgumentException("Not created view for Mock");
            default:
                throw new IllegalArgumentException("Not created view for type yet: " + type);
        }
    }
}