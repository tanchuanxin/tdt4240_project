package com.tnig.game.view.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.SnapshotArray;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tnig.game.utillities.AssetLoader;

public class MainMenuScreen extends AbstractScreen {
    private final Stage stage;
    private final Table table;

    public MainMenuScreen(OrthographicCamera camera, final AssetLoader assetLoader) {
        super(camera, assetLoader);

        // Initialize stage for UI drawing
        stage = new Stage(new ScreenViewport(camera));
        table = new Table();
        Gdx.input.setInputProcessor(stage);

        // Create actors
        Label titleLabel = new Label("The Nearly Impossible Game", assetLoader.getManager().get(assetLoader.SKIN_PIXTHULHU_UI));
        titleLabel.setAlignment(Align.center);

        Label onePlayerBtnLabel = new Label("1 Player", assetLoader.getManager().get(assetLoader.SKIN_PIXTHULHU_UI));
        Button onePlayerBtn = new Button(onePlayerBtnLabel, assetLoader.getManager().get(assetLoader.SKIN_PIXTHULHU_UI));
        onePlayerBtn.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            };

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                // Change screen to map select screen
                ScreenManager.getInstance().setScreen(Screen.MAP_SELECT);
            };
        });

        Label twoPlayerBtnLabel = new Label("2 Player", assetLoader.getManager().get(assetLoader.SKIN_PIXTHULHU_UI));
        Button twoPlayerBtn = new Button(twoPlayerBtnLabel, assetLoader.getManager().get(assetLoader.SKIN_PIXTHULHU_UI));
        twoPlayerBtn.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            };

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                // Change screen to map select screen
                ScreenManager.getInstance().setScreen(Screen.MAP_SELECT);
            };
        });

        Label leaderboardsBtnLabel = new Label("Leaderboards", assetLoader.getManager().get(assetLoader.SKIN_PIXTHULHU_UI));
        Button leaderboardsBtn = new Button(leaderboardsBtnLabel, assetLoader.getManager().get(assetLoader.SKIN_PIXTHULHU_UI));
        leaderboardsBtn.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            };

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                // Change screen to high scores screen
                ScreenManager.getInstance().setScreen(Screen.HIGH_SCORES);
            };
        });

        Label exitBtnLabel = new Label("Exit", assetLoader.getManager().get(assetLoader.SKIN_PIXTHULHU_UI));
        Button exitBtn = new Button(exitBtnLabel, assetLoader.getManager().get(assetLoader.SKIN_PIXTHULHU_UI));
        exitBtn.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            };

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                // Dispose all assets and exit game
                assetLoader.dispose();
                Gdx.app.exit();
            };
        });

        // Add actors to table layout
        table.pad(50f);
        table.setWidth(600f);
        table.setPosition(stage.getWidth()/2,stage.getHeight()/2, Align.center);
        table.row().spaceBottom(20f);
        table.add(titleLabel).center();
        table.row().spaceBottom(20f);
        table.add(onePlayerBtn).center().fillX();
        table.row().spaceBottom(20f);
        table.add(twoPlayerBtn).center().fillX();
        table.row().spaceBottom(20f);
        table.add(leaderboardsBtn).center().fillX();
        table.row().spaceBottom(20f);
        table.add(exitBtn).center().fillX();

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
        table.setPosition(width/2,height/2, Align.center);
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
