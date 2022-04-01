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
import com.tnig.game.controller.Managers.ScreenManager;
import com.tnig.game.utillities.AssetLoader;

import java.util.ArrayList;
import java.util.List;

public class MapSelectScreen extends AbstractScreen {
    private final Stage stage;
    private final List<Button> mapBtnList = new ArrayList<>();

    public MapSelectScreen(OrthographicCamera camera, AssetLoader assetLoader) {
        super(camera, assetLoader);

        // Initialize stage for UI drawing
        stage = new Stage(new ScreenViewport(camera));
        Table table = new Table();
        Gdx.input.setInputProcessor(stage);

        // Create actors
        Label titleLabel = new Label("Select your map", assetLoader.getManager().get(assetLoader.SKIN_PIXTHULHU_UI));
        titleLabel.setAlignment(Align.center);

        for (Integer map = 1; map < 14; map++) {
            Button mapBtn = new Button(new Label(map.toString(), assetLoader.getManager().get(assetLoader.SKIN_PIXTHULHU_UI)), assetLoader.getManager().get(assetLoader.SKIN_PIXTHULHU_UI));
            final Integer finalMap = map;
            mapBtn.addListener(new ClickListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                };

                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    // Change screen to game screen
                    System.out.println("Map selected: " + finalMap.toString());
                    ScreenManager.getInstance().setScreen(Screen.GAME);
                };
            });
            mapBtnList.add(mapBtn);
        }

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
        table.row().colspan(5).spaceBottom(20f).expandX().fillX();
        table.add(titleLabel).center().fillX();

        for (int i = 0; i < mapBtnList.size(); i++) {
            if (i % 5 == 0) {
                table.row().spaceBottom(20f);
            }
            table.add(mapBtnList.get(i));
        }

        table.row().colspan(5).spaceBottom(20f).expandX().fillX();
        table.add(backBtn).center().fillX();

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
