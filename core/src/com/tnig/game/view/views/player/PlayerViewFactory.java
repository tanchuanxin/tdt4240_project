package com.tnig.game.view.views.player;

import com.tnig.game.model.models.Model;
import com.tnig.game.model.models.ObjectType;
import com.tnig.game.view.AnimatedView;
import com.tnig.game.view.views.AnimatedViewFactory;

public class PlayerViewFactory implements AnimatedViewFactory {

    private static final PlayerViewFactory INSTANCE = new PlayerViewFactory();

    private PlayerViewFactory() {
    }
    // Singleton intended
    public static PlayerViewFactory getInstance(){
        return INSTANCE;
    }

    // factory method
    @Override
    public AnimatedView createView(Model model) {
        if (model.getType() != ObjectType.PLAYER){
            throw new IllegalArgumentException("Model should be of type PLAYER but was: " + model.getType());
        }
        return new PlayerView(model);
    }
}
