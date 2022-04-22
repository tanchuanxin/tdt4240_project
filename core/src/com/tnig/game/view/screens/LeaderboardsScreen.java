package com.tnig.game.view.screens;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.tnig.game.controller.events.Event;
import com.tnig.game.controller.events.EventListener;
import com.tnig.game.controller.managers.EventManager;
import com.tnig.game.controller.managers.ScreenManager;
import com.tnig.game.model.networking.NetworkService;
import com.tnig.game.utilities.AssetLoader;
import com.tnig.game.controller.events.EventName;

import java.util.ArrayList;

public class LeaderboardsScreen extends AbstractScreen implements EventListener {

    private int mapNum = 1;

    public LeaderboardsScreen(final ScreenManager screenManager,
                              OrthographicCamera camera,
                              AssetLoader assetLoader,
                              final EventManager eventManager,
                              NetworkService networkService) {
        super(camera, assetLoader);
        eventManager.subscribe(EventName.VIEW_LEADERBOARDS, this);
        Table table = new Table();

        Label selectMap = new Label("Leaderboard" + mapNum, assetLoader.get(AssetLoader.SKIN_PIXTHULHU_UI));

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
        table.add(selectMap).center().fillX();

        int counter = 0;
        for (ArrayList<String> internalList : networkService.getHighScore(mapNum)) {
            counter++;
            table.row().spaceBottom(20f);
            for (String user : internalList) {
                table.add(new Label(user, assetLoader.get(AssetLoader.SKIN_PIXTHULHU_UI)));
            }
            if (counter >= 9) break;
        }

        table.row().colspan(5).spaceBottom(20f).expandX().fillX();
        table.add(backBtn).expandX().center().fillX();



        // Add actors to stage
        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }




    @Override
    public void receiveEvent(Event event) {
        mapNum = (int) event.data.get("mapNum");
        System.out.println("Receiving event ---------------------------------");
    }
}
