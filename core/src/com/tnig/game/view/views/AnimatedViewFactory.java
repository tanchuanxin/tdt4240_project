package com.tnig.game.view.views;

import com.tnig.game.model.models.Model;
import com.tnig.game.view.AnimatedView;

public interface AnimatedViewFactory {
    AnimatedView createView(Model model);
}
