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
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tnig.game.controller.managers.ScreenManager;
import com.tnig.game.utillities.AssetLoader;

public class HighScoresScreen extends AbstractScreen {
    private final Stage stage;

    public HighScoresScreen(OrthographicCamera camera, AssetLoader assetLoader) {
        super(camera, assetLoader);

        // Initialize stage for UI drawing
        stage = new Stage(new ScreenViewport(camera));
        Table table = new Table();
        Gdx.input.setInputProcessor(stage);

        Label backBtnLabel = new Label("Back", assetLoader.getManager().get(assetLoader.SKIN_PIXTHULHU_UI));
        final Button backBtn = new Button(backBtnLabel, assetLoader.getManager().get(assetLoader.SKIN_PIXTHULHU_UI));
        backBtn.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            };

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                // Change screen to map select screen
                ScreenManager.getInstance().setScreen(Screen.MAIN_MENU);
            };
        });

        // Add actors to table layout
        table.pad(50f);
        table.setFillParent(true);
        table.row().spaceBottom(20f);
        table.add(backBtn).expandX().center().fillX();

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
        stage.dispose();
    }
}
