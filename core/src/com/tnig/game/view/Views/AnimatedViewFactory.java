package com.tnig.game.view.Views;

import com.tnig.game.model.models.Model;
import com.tnig.game.view.AnimatedView;
import com.tnig.game.view.View;

public interface AnimatedViewFactory {
    AnimatedView createView(Model model);
}
