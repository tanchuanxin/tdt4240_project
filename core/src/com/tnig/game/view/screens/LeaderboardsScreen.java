package com.tnig.game.view.screens;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.tnig.game.controller.events.Event;
import com.tnig.game.controller.events.EventListener;
import com.tnig.game.controller.managers.EventManager;
import com.tnig.game.controller.managers.ScreenManager;
import com.tnig.game.model.networking.NetworkService;
import com.tnig.game.model.networking.PlayerData;
import com.tnig.game.utilities.AssetLoader;
import com.tnig.game.view.ui_components.ButtonFactory;

import java.util.ArrayList;

public class LeaderboardsScreen extends AbstractScreen implements EventListener {
    private int mapNum;
    private int maxTopPlayersCount = 10; // Number of top scores to show in leaderboard

    private final EventManager eventManager;
    private final ScreenManager screenManager;
    private final NetworkService networkService;

    public LeaderboardsScreen(final ScreenManager screenManager,
                              OrthographicCamera camera,
                              AssetLoader assetLoader,
                              EventManager eventManager, NetworkService networkService,
                              int mapNum) {
        super(camera, assetLoader);

        this.mapNum = mapNum;
        this.eventManager = eventManager;
        this.screenManager = screenManager;
        this.networkService = networkService;
    }

    @Override
    public void show() {
        showHighscores();
    }

    private void showHighscores() {
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

        networkService.updateHighscore();
        ArrayList<PlayerData> highscores = networkService.getHighScores(mapNum);
        int numTopPlayersCount = 0;
        for (int i = highscores.size() - 1; i >= 0; i--) {
            if (numTopPlayersCount == maxTopPlayersCount) {
                break;
            }

            PlayerData playerData = highscores.get(i);

            table.row().spaceBottom(10f);
            table.add(new Label(playerData.getName(), skin));
            table.add(new Label(String.valueOf(playerData.getScore()), skin));
            numTopPlayersCount++;
        }

        table.row().colspan(2).spaceBottom(20f).expandX().fillX();
        Button backBtn = buttonFactory.createSwitchingScreenButton(ScreenName.MAIN_MENU, "Back", true);
        table.add(backBtn).expandX().center().fillX();

        // Add actors to stage
        stage.clear();
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
