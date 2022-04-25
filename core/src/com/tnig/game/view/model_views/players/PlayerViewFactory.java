package com.tnig.game.view.model_views.players;

import com.tnig.game.model.models.interfaces.Model;
import com.tnig.game.model.models.players.PlayerType;
import com.tnig.game.utilities.AssetLoader;
import com.tnig.game.view.model_views.AnimatedView;

public class PlayerViewFactory {

    public static AnimatedView createView(Model model, AssetLoader assetLoader){

        PlayerType type = (PlayerType) model.getType();
        switch (type){
            case NORMAL_PLAYER:
                return new PlayerView(model, assetLoader);
            case MOCK_PLAYER:
                throw new IllegalArgumentException("Not created view for MOCK_OBSTACLE");
            default:
                throw new IllegalArgumentException("Not created view for type yet: " + type);
        }
    }
}
