package com.tnig.game.view.model_views;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tnig.game.model.models.interfaces.Model;

public abstract class AbstractView implements AnimatedView {
    private float time = 0;
    private final Model model;

    protected abstract void renderModel(SpriteBatch batch, float x, float y, float width, float height, float time);

    public AbstractView(Model model) {
        this.model = model;
    }

    @Override
    public void update(float deltaTime) {
        time = deltaTime;
    }

    // A template method could be used here
    @Override
    public void render(SpriteBatch batch) {
        float width = model.getWidth();
        float height = model.getHeight();
        float x = model.getX();
        float y = model.getY();

        renderModel(batch, x, y, width, height, time);
    }
}
