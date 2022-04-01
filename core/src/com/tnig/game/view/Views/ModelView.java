package com.tnig.game.view.Views;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tnig.game.model.models.Model;
import com.tnig.game.utillities.Constants;
import com.tnig.game.view.AnimatedView;

public abstract class ModelView implements AnimatedView {

    protected final float FRAME_DURATION = 1f / Constants.FPS;
    private float time = 0;
    private final Model model;


    protected abstract void renderModel(SpriteBatch batch, float x, float y, float width, float height, float time);

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
        //renderModel(batch, x, y, width, height, time);

    }
}
