package com.tnig.game.view.guis;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.tnig.game.controller.managers.ScreenManager;
import com.tnig.game.controller.screens.ScreenName;
import com.tnig.game.utilities.AssetLoader;
import com.tnig.game.utilities.events.EventManager;
import com.tnig.game.utilities.events.ViewLeaderboardsEvent;
import com.tnig.game.utilities.events.ViewMainMenuEvent;

public class LeaderboardsScreenGUI extends AbstractGUI {
    private final EventManager eventManager;

    public LeaderboardsScreenGUI(OrthographicCamera camera, AssetLoader assetLoader, final EventManager eventManager) {
        super(camera, assetLoader);
        this.eventManager = eventManager;

        Table table = new Table();

        Label backBtnLabel = new Label("Back", assetLoader.get(assetLoader.SKIN_PIXTHULHU_UI));
        final Button backBtn = new Button(backBtnLabel, assetLoader.get(assetLoader.SKIN_PIXTHULHU_UI));
        backBtn.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            };

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                // Change screen to map select screen
                eventManager.pushEvent(new ViewMainMenuEvent());
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
}
