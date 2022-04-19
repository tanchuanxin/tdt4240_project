package com.tnig.game.view.screens;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.tnig.game.controller.events.screen_events.MapSelectedEvent;
import com.tnig.game.controller.managers.EventManager;
import com.tnig.game.controller.managers.ScreenManager;
import com.tnig.game.utilities.AssetLoader;

import java.util.ArrayList;
import java.util.List;

public class MapSelectScreen extends AbstractScreen {

    public MapSelectScreen(final ScreenManager screenManager,
                           OrthographicCamera camera,
                           AssetLoader assetLoader,
                           final EventManager eventManager) {
        super(camera, assetLoader);

        Table table = new Table();

        // Create actors
        Label titleLabel = new Label("Select your map", assetLoader.get(AssetLoader.SKIN_PIXTHULHU_UI));
        titleLabel.setAlignment(Align.center);

        List<Button> mapBtnList = new ArrayList<>();
        for (Integer map = 1; map < 14; map++) {
            Button mapBtn = new Button(new Label(map.toString(), assetLoader.get(AssetLoader.SKIN_PIXTHULHU_UI)), assetLoader.get(assetLoader.SKIN_PIXTHULHU_UI));
            final Integer mapNum = map;
            mapBtn.addListener(new ClickListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                };

                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    // Change screen to game screen
                    System.out.println("Map selected: " + mapNum);
                    // TODO: Implement as event with map selected
                    eventManager.pushEvent(new MapSelectedEvent(mapNum));
                    screenManager.setScreen(ScreenName.GAME);
                };
            });
            mapBtnList.add(mapBtn);
        }

        Label backBtnLabel = new Label("Back", assetLoader.get(AssetLoader.SKIN_PIXTHULHU_UI));
        final Button backBtn = new Button(backBtnLabel, assetLoader.get(AssetLoader.SKIN_PIXTHULHU_UI));
        backBtn.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            };

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                // Change screen to main menu screen
                screenManager.setScreen(ScreenName.MAIN_MENU);
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
        super.render(delta);
    }


}
