package com.tnig.game.view.guis;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.tnig.game.model.networking.Network;
import com.tnig.game.utilities.AssetLoader;
import com.tnig.game.utilities.events.Event;
import com.tnig.game.utilities.events.EventListener;
import com.tnig.game.utilities.events.EventManager;
import com.tnig.game.utilities.events.EventName;
import com.tnig.game.utilities.events.LeaderBoardSelectedEvent;
import com.tnig.game.utilities.events.MapSelectedEvent;
import com.tnig.game.utilities.events.ViewLeaderboardsEvent;
import com.tnig.game.utilities.events.ViewMainMenuEvent;

import java.util.ArrayList;
import java.util.List;

public class LeaderboardsScreenGUI extends AbstractGUI implements EventListener {
    private final EventManager eventManager;
    private Network network;
    private final List<Button> mapBtnList = new ArrayList<>();
    private int mapNum = 1;

    public LeaderboardsScreenGUI(OrthographicCamera camera, AssetLoader assetLoader, final EventManager eventManager, Network network) {
        super(camera, assetLoader);
        this.eventManager = eventManager;
        this.network = network;
        eventManager.subscribe(EventName.VIEW_LEADERBOARDS, this);
        Table table = new Table();

        Label selectMap = new Label("Leaderboard" + mapNum, assetLoader.get(assetLoader.SKIN_PIXTHULHU_UI));

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
                eventManager.pushEvent(new ViewLeaderboardsEvent());
            };
        });

        // Add actors to table layout
        table.pad(50f);
        table.setFillParent(true);
        table.row().colspan(5).spaceBottom(20f).expandX().fillX();
        table.add(selectMap).center().fillX();

        int counter = 0;
        for (ArrayList<String> internalList : network.getHighScore(mapNum)) {
            counter++;
            table.row().spaceBottom(20f);
            for (String user : internalList) {
                table.add(new Label(user, assetLoader.get(assetLoader.SKIN_PIXTHULHU_UI)));
            }
            if (counter >= 9) break;
        }

        table.row().colspan(5).spaceBottom(20f).expandX().fillX();
        table.add(backBtn).expandX().center().fillX();



        // Add actors to stage
        stage.addActor(table);
    }

    @Override
    public void receiveEvent(Event event) {
        mapNum = (int) event.data.get("mapNum");
    }
}
