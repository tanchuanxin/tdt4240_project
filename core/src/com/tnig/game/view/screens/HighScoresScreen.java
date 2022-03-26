package com.tnig.game.view.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.tnig.game.utillities.AssetLoader;

public class HighScoresScreen extends AbstractScreen {
    public HighScoresScreen(OrthographicCamera camera, AssetLoader assetLoader) {
        super(camera, assetLoader);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
}
