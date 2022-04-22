package com.tnig.game.view.screens;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.tnig.game.controller.managers.EventManager;
import com.tnig.game.controller.managers.ScreenManager;
import com.tnig.game.model.networking.NetworkService;
import com.tnig.game.utilities.AssetLoader;

import java.util.ArrayList;
import java.util.List;

public class LeaderboardSelectScreen extends AbstractScreen {

    public LeaderboardSelectScreen(final ScreenManager screenManager,
                                   OrthographicCamera camera,
                                   AssetLoader assetLoader,
                                   final EventManager eventManager,
                                   NetworkService networkService) {
        super(camera, assetLoader);
        ArrayList<Integer> levelNum = new ArrayList<>(networkService.getLevels());
        Table table = new Table();

        // Create actors
        Label titleLabel = new Label("Select map leaderboard", assetLoader.get(AssetLoader.SKIN_PIXTHULHU_UI));
        titleLabel.setAlignment(Align.center);

        List<Button> mapBtnList = new ArrayList<>();
        for (int map = 1; map <= levelNum.size(); map++) {
            Button mapBtn = new Button(new Label(Integer.toString(map), assetLoader.get(AssetLoader.SKIN_PIXTHULHU_UI)), assetLoader.get(AssetLoader.SKIN_PIXTHULHU_UI));
            final Integer mapNum = map;
            mapBtn.addListener(new ClickListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }

                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    // Change screen to game screen
                    System.out.println("Map selected: " + mapNum);
                    // TODO: finish
                }
            });
            mapBtnList.add(mapBtn);
        }

        Label backBtnLabel = new Label("Back", assetLoader.get(AssetLoader.SKIN_PIXTHULHU_UI));
        final Button backBtn = new Button(backBtnLabel, assetLoader.get(AssetLoader.SKIN_PIXTHULHU_UI));
        backBtn.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                // Change screen to map select screen
                screenManager.setScreen(ScreenName.MAP_SELECT);
            }
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
        super.render(delta);
    }


}