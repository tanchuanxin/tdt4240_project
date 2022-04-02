package com.tnig.game.view.views.obstacles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tnig.game.model.models.Model;
import com.tnig.game.view.views.ModelView;

public class StaticTriangleView extends ModelView {

    public StaticTriangleView(Model model) {
        super(model);
    }

    @Override
    protected void renderModel(SpriteBatch batch, float x, float y, float width, float height, float time) {

    }
}
