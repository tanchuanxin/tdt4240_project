package com.tnig.game.view.Views;

import com.tnig.game.model.models.Model;
import com.tnig.game.view.View;

public interface ModelViewFactory {
    View createView(Model model);
}
