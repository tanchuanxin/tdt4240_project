package com.tnig.game.controller.screens;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.tnig.game.utilities.AssetLoader;
import com.tnig.game.view.guis.MapSelectScreenGUI;

public class MapSelectScreen extends AbstractScreen {
    private MapSelectScreenGUI mapSelectScreenGUI;

    public MapSelectScreen(OrthographicCamera camera, AssetLoader assetLoader, MapSelectScreenGUI mapSelectScreenGUI) {
        super(camera, assetLoader);
        this.mapSelectScreenGUI = mapSelectScreenGUI;
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        mapSelectScreenGUI.render(delta);
    }

    @Override
    public void resize(int width, int height) {
        mapSelectScreenGUI.resize(width, height);
    }

    @Override
    public void hide() {
        // Dispose this screen and its scenes
        dispose();
    }

    @Override
    public void dispose() {
        mapSelectScreenGUI.dispose();
    }
}
