package com.tnig.game.view.views.player;

import com.tnig.game.model.models.Model;
import com.tnig.game.view.AnimatedView;
import com.tnig.game.view.views.AnimatedViewFactory;

public class PlayerViewFactory implements AnimatedViewFactory {

    private static final AnimatedViewFactory INSTANCE = new PlayerViewFactory();

    private PlayerViewFactory() {
    }

    protected static AnimatedViewFactory getInstance(){
        return INSTANCE;
    }
    @Override
    public AnimatedView createView(Model model) {
        //if (model instanceof PlayerModel) return new PlayerView(model) else throw;
        return new PlayerView(model);
    }
}
