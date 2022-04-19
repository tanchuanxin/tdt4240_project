package com.tnig.game.view.screens;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.tnig.game.utilities.AssetLoader;
import com.tnig.game.utilities.events.EventManager;
import com.tnig.game.utilities.events.ViewMainMenuEvent;
import com.tnig.game.view.guis.AbstractGUI;

import java.util.ArrayList;
import java.util.List;

public class SettingsScreen extends AbstractGUI {
    private final List<Button> mapBtnList = new ArrayList<>();
    private final EventManager eventManager;

    public SettingsScreen(OrthographicCamera camera, AssetLoader assetLoader, final EventManager eventManager) {
        super(camera, assetLoader);
        this.eventManager = eventManager;

        Table table = new Table();

        // Create actors
        Label titleLabel = new Label("Settings", assetLoader.get(assetLoader.SKIN_PIXTHULHU_UI));
        titleLabel.setAlignment(Align.center);

        Label backBtnLabel = new Label("Back", assetLoader.get(assetLoader.SKIN_PIXTHULHU_UI));
        final Button backBtn = new Button(backBtnLabel, assetLoader.get(assetLoader.SKIN_PIXTHULHU_UI));
        backBtn.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            };

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                // Back to main menu
                eventManager.pushEvent(new ViewMainMenuEvent());
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
}
