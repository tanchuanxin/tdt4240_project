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
import com.tnig.game.view.ui_components.ButtonFactory;

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

        ButtonFactory buttonFactory = new ButtonFactory(eventManager, screenManager, assetLoader);

        List<Button> mapBtnList = new ArrayList<>();
        for (int map = 1; map < 14; map++) {
            Button mapBtn = buttonFactory.createSwitchScreenEventButton(
                    ScreenName.GAME, new MapSelectedEvent(map), String.valueOf(map), true);

            mapBtnList.add(mapBtn);
        }

        Button backBtn = buttonFactory.createSwitchingScreenButton(
                ScreenName.MAIN_MENU, "Back", true);


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
