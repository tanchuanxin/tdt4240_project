package com.tnig.game.controller.screens;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.tnig.game.utilities.AssetLoader;
import com.tnig.game.view.guis.LeaderBoardSelectScreenGUI;
import com.tnig.game.view.guis.LeaderboardsScreenGUI;

public class LeaderboardSelectScreen extends AbstractScreen {

    private LeaderBoardSelectScreenGUI leaderboardsScreenGUI;

    public LeaderboardSelectScreen(OrthographicCamera camera, AssetLoader assetLoader, LeaderBoardSelectScreenGUI leaderboardSelectScreenGUI) {
        super(camera, assetLoader);
        this.leaderboardsScreenGUI = leaderboardSelectScreenGUI;
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