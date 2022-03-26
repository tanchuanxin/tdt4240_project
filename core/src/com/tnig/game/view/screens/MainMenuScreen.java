package com.tnig.game.view.screens;

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
import com.tnig.game.utillities.AssetLoader;

public class MainMenuScreen extends AbstractScreen {
    private final Stage stage;

    public MainMenuScreen(OrthographicCamera camera, AssetLoader assetLoader) {
        super(camera, assetLoader);

        // Initialize stage for UI drawing
        stage = new Stage(new ScreenViewport(camera));
        Table table = new Table();
        Gdx.input.setInputProcessor(stage);

        // Create actors
        Label titleLabel = new Label("The Nearly Impossible Game", assetLoader.getManager().get(assetLoader.SKIN_PIXTHULHU_UI));
        titleLabel.setAlignment(Align.center);

        Label playBtnLabel = new Label("Play", assetLoader.getManager().get(assetLoader.SKIN_PIXTHULHU_UI));
        final Button playBtn = new Button(playBtnLabel, assetLoader.getManager().get(assetLoader.SKIN_PIXTHULHU_UI));
        playBtn.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                // Change screen to game screen
                ScreenManager.getInstance().setScreen(Screen.GAME);
                return true;
            };
        });

        Label highScoresBtnLabel = new Label("High Scores", assetLoader.getManager().get(assetLoader.SKIN_PIXTHULHU_UI));
        final Button highScoresBtn = new Button(highScoresBtnLabel, assetLoader.getManager().get(assetLoader.SKIN_PIXTHULHU_UI));
        highScoresBtn.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                // Change screen to high scores screen
                ScreenManager.getInstance().setScreen(Screen.HIGH_SCORES);
                return true;
            };
        });

        // Add actors to table layout
        table.pad(50f);
        table.setFillParent(true);
        table.row().spaceBottom(20f);
        table.add(titleLabel).expandX().center().fillX();
        table.row().spaceBottom(20f);
        table.add(playBtn).expandX().center().fillX();
        table.row().spaceBottom(20f);
        table.add(highScoresBtn).expandX().center().fillX();

        // Add actors to stage
        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void hide() {
        // Dispose this screen and its scenes
        dispose();
    }

    @Override
    public void dispose() {
        Gdx.input.setInputProcessor(null);
        stage.dispose();
    }
}
