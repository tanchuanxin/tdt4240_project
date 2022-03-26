package com.tnig.game.view.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.tnig.game.utillities.AssetLoader;

public class MainMenuScreen extends AbstractScreen {
    public MainMenuScreen(OrthographicCamera camera, AssetLoader assetLoader) {
        super(camera, assetLoader);

        System.out.println("MAIN MENU");
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        System.out.println("MAIN MENU RENDER");
    }
}
