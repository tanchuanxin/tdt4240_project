package com.tnig.game.view.Views;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tnig.game.model.models.Model;
import com.tnig.game.view.AnimatedView;

public abstract class ModelView implements AnimatedView {
    private final Model model;


    protected abstract void renderModel();

    public ModelView(Model model) {
        this.model = model;
    }




    @Override
    public void update(float deltaTime) {

    }

    // A template method could be used her
    @Override
    public void render(SpriteBatch batch) {
        float width = model.getWidth();
        float height = model.getHeight();
        float x = model.getX();
        float y = model.getY();
        renderModel(); //TODO: IMPLEMENT

    }
}
