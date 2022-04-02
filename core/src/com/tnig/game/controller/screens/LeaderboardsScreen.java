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
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tnig.game.controller.managers.ScreenManager;
import com.tnig.game.utilities.AssetLoader;
import com.tnig.game.view.guis.GUI;
import com.tnig.game.view.guis.LeaderboardsScreenGUI;

public class LeaderboardsScreen extends AbstractScreen {

    private GUI leaderboardsScreenGUI;

    public LeaderboardsScreen(OrthographicCamera camera, AssetLoader assetLoader, GUI leaderboardsScreenGUI) {
        super(camera, assetLoader);
        this.leaderboardsScreenGUI = leaderboardsScreenGUI;
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        leaderboardsScreenGUI.render(delta);
    }

    @Override
    public void resize(int width, int height) {
        leaderboardsScreenGUI.resize(width, height);
    }

    @Override
    public void hide() {
        // Dispose this screen and its scenes
        dispose();
    }

    @Override
    public void dispose() {
        leaderboardsScreenGUI.dispose();
    }
}
