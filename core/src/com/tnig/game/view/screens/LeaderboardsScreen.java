package com.tnig.game.view.screens;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.tnig.game.controller.events.Event;
import com.tnig.game.controller.events.EventListener;
import com.tnig.game.controller.managers.EventManager;
import com.tnig.game.controller.managers.ScreenManager;
import com.tnig.game.model.networking.NetworkService;
import com.tnig.game.utilities.AssetLoader;
import com.tnig.game.controller.events.EventName;
import com.tnig.game.view.ui_components.ButtonFactory;

import java.util.ArrayList;

public class LeaderboardsScreen extends AbstractScreen implements EventListener {

    private int mapNum = 1;

    public LeaderboardsScreen(final ScreenManager screenManager,
                              OrthographicCamera camera,
                              AssetLoader assetLoader,
                              EventManager eventManager, NetworkService networkService,
                              int mapNum) {
        super(camera, assetLoader);

        this.mapNum = mapNum;

        Table table = new Table();
        Skin skin = assetLoader.get(AssetLoader.SKIN_PIXTHULHU_UI);
        ButtonFactory buttonFactory = new ButtonFactory(eventManager, screenManager, assetLoader);

        // Add actors to table layout
        Label leaderboardTitle = new Label("Leaderboard (Map " + Integer.toString(mapNum) + ")", skin, "title");
        leaderboardTitle.setAlignment(Align.center);

        table.pad(50f);
        table.setFillParent(true);
        table.row().colspan(2).spaceBottom(20f).expandX().fillX();
        table.add(leaderboardTitle).align(Align.center).center().fillX();

        table.row().spaceBottom(20f);
        Label nameTitle = new Label("Name", skin, "subtitle");
        nameTitle.setAlignment(Align.center);
        Label scoreTitle = new Label("Score", skin, "subtitle");
        scoreTitle.setAlignment(Align.center);
        table.add(nameTitle);
        table.add(scoreTitle);

        int counter = 0;
        for (ArrayList<String> internalList : networkService.getHighScore(mapNum)) {
            counter++;
            table.row().spaceBottom(20f);
            for (String user : internalList) {
                table.add(new Label(user, skin));
            }
            if (counter >= 9) break;
        }

        table.row().colspan(2).spaceBottom(20f).expandX().fillX();
        Button backBtn = buttonFactory.createSwitchingScreenButton(ScreenName.LEADERBOARD_SELECTION, "Back", true);
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
        mapNum = event.getData("mapNum", int.class);
    }
}
