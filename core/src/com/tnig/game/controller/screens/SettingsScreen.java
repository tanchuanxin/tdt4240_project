package com.tnig.game.controller.screens;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.tnig.game.utilities.AssetLoader;
import com.tnig.game.view.guis.GUI;
import com.tnig.game.view.guis.SettingsScreenGUI;

public class SettingsScreen extends AbstractScreen {
    private SettingsScreenGUI settingsScreenGUI;

    public SettingsScreen(OrthographicCamera camera, AssetLoader assetLoader, SettingsScreenGUI settingsScreenGUI) {
        super(camera, assetLoader);
        this.settingsScreenGUI = settingsScreenGUI;
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        settingsScreenGUI.render(delta);
    }

    @Override
    public void resize(int width, int height) {
        settingsScreenGUI.resize(width, height);
    }

    @Override
    public void hide() {
        // Dispose this screen and its scenes
        dispose();
    }

    @Override
    public void dispose() {
        settingsScreenGUI.dispose();
    }
}
