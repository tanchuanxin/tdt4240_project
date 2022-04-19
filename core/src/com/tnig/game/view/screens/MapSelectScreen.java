package com.tnig.game.view.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.tnig.game.controller.events.screen_events.MapSelectedEvent;
import com.tnig.game.controller.managers.EventManager;
import com.tnig.game.controller.managers.ScreenManager;
import com.tnig.game.utilities.AssetLoader;
import com.tnig.game.utilities.Constants;
import com.tnig.game.view.ui_components.ButtonFactory;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

public class MapSelectScreen extends AbstractScreen {
    private int numMaps = 0;

    public MapSelectScreen(final ScreenManager screenManager,
                           OrthographicCamera camera,
                           AssetLoader assetLoader,
                           final EventManager eventManager) {
        super(camera, assetLoader);

        // Check number of maps we have and load them
        FileHandle dirHandle = Gdx.files.internal(Constants.MAP_ASSET_LOCATION);
        FilenameFilter mapFileFilter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return(name.matches("map[0-9]+.tmx"));
            }
        };
        numMaps = dirHandle.list(mapFileFilter).length;

        Table table = new Table();

        // Create actors
        Label titleLabel = new Label("Select your map", assetLoader.get(AssetLoader.SKIN_PIXTHULHU_UI));
        titleLabel.setAlignment(Align.center);

        ButtonFactory buttonFactory = new ButtonFactory(eventManager, screenManager, assetLoader);

        List<Button> mapBtnList = new ArrayList<>();
        for (int map = 1; map <= numMaps; map++) {
            Button mapBtn = buttonFactory.createSwitchScreenEventButton(
                    ScreenName.GAME, new MapSelectedEvent(map), String.valueOf(map), true);

            mapBtnList.add(mapBtn);
        }

        Button backBtn = buttonFactory.createSwitchingScreenButton(
                ScreenName.MAIN_MENU, "Back", true);


        // Add actors to table layout
        table.pad(50f);
        table.setFillParent(true);
        table.row().colspan(6).spaceBottom(20f).expandX().fillX();
        table.add(titleLabel).center().fillX();

        for (int i = 0; i < mapBtnList.size(); i++) {
            if (i % 6 == 0) {
                table.row().left().spaceBottom(20f);
            }
            table.add(mapBtnList.get(i));
        }

        table.row().left().colspan(6).spaceBottom(20f).expandX().fillX();
        table.add(backBtn).center().fillX();

        // Add actors to stage
        stage.addActor(table);
    }
    @Override
    public void render(float delta) {
        super.render(delta);
    }


}
