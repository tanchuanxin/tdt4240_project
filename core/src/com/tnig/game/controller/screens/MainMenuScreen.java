package com.tnig.game.controller.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tnig.game.controller.managers.ScreenManager;
import com.tnig.game.utilities.AssetLoader;
import com.tnig.game.view.guis.GUI;
import com.tnig.game.view.guis.MainMenuScreenGUI;

public class MainMenuScreen extends AbstractScreen {
    private GUI mainMenuScreenGUI;

    public MainMenuScreen(OrthographicCamera camera, final AssetLoader assetLoader, GUI mainMenuScreenGUI) {
        super(camera, assetLoader);
        this.mainMenuScreenGUI = mainMenuScreenGUI;
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        mainMenuScreenGUI.render(delta);
    }

    @Override
    public void resize(int width, int height) {
        mainMenuScreenGUI.resize(width, height);
    }

    @Override
    public void hide() {
        // Dispose this screen and its scenes
        dispose();
    }

    @Override
    public void dispose() {
        mainMenuScreenGUI.dispose();
    }
}
