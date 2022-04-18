package com.tnig.game.view.model_views.player;

import com.tnig.game.model.models.Model;
import com.tnig.game.model.models.obstacles.Spike;
import com.tnig.game.model.models.players.NormalPlayer;
import com.tnig.game.model.models.players.PlayerType;
import com.tnig.game.view.AnimatedView;
import com.tnig.game.view.views.ModelView;

public class PlayerViewFactory {

    public static AnimatedView createView(Model model){
        NormalPlayer player = (NormalPlayer) model;
        PlayerType type = player.getPlayerType();
        switch (type){
            case NORMALPLAYER:
                return new PlayerView(model);
            case MOCK:
                throw new IllegalArgumentException("Not created view for Mock");
            default:
                throw new IllegalArgumentException("Not created view for type yet: " + type);
        }
    }
}
