package com.tnig.game.view.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.tnig.game.controller.events.Event;
import com.tnig.game.controller.events.game_events.UploadScore;
import com.tnig.game.controller.events.screen_events.LeaderboardSelectedEvent;
import com.tnig.game.controller.managers.EventManager;
import com.tnig.game.controller.managers.ScreenManager;
import com.tnig.game.model.GameState;
import com.tnig.game.model.networking.NetworkService;
import com.tnig.game.model.networking.PlayerData;
import com.tnig.game.utilities.AssetLoader;
import com.tnig.game.view.ui_components.ButtonFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameOverScreen extends AbstractScreen{

    private final List<GameState> gameStates;
    private final NetworkService networkService;
    private final Skin skin;
    private final Table table;
    private final List<TextField> textFields;

    public GameOverScreen(OrthographicCamera camera, AssetLoader assetLoader, final EventManager eventManager,
                          final List<GameState> gameStates, final NetworkService networkService) {
        super(camera, assetLoader);
        this.gameStates = gameStates;
        this.networkService = networkService;
        this.textFields = new ArrayList<>();

        skin = assetLoader.get(AssetLoader.SKIN_PIXTHULHU_UI);

        // Add actors to table layout
        table = new Table();
        table.center().setFillParent(true);
        table.pad(30f);
        table.row().padBottom(30f);

        Label titleLabel = new Label("Game Over", skin, "title");
        titleLabel.setAlignment(Align.center);
        table.add(titleLabel).colspan(2).fillX().align(Align.center);
        table.row().padBottom(30f);

        for (int playerNum = 1; playerNum <= gameStates.size(); playerNum++) {
            GameState gameState = gameStates.get(playerNum - 1);

            Table subTable = new Table();
            subTable.pad(10f);

            if (playerNum != gameStates.size()) {
                subTable.padRight(20f);
            }

            subTable.row().padBottom(15f);

            Label scoreLabel = new Label("P" + String.valueOf(playerNum) + " Score: " + gameState.getScore(), skin);
            scoreLabel.setAlignment(Align.center);
            subTable.add(scoreLabel).colspan(2).fillX();

            Label nameFieldLabel = new Label("Name: ", skin);
            nameFieldLabel.setAlignment(Align.left);

            subTable.row();

            TextField textField = new TextField("", skin);
            textField.setAlignment(Align.left);
            textFields.add(textField);

            subTable.add(nameFieldLabel).padRight(10f).fillX();
            subTable.add(textField).fillX().setActorWidth(200f);

            table.add(subTable);
        }

        Button saveScoreBtn = new Button(new Label("Upload scores", skin), skin);
        saveScoreBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                List<PlayerData> players = new ArrayList<>();
                boolean allNamesFilled = true;

                for (int i = 0; i < textFields.size(); i++) {
                    String name = textFields.get(i).getText();
                    GameState gameState = gameStates.get(i);

                    PlayerData playerData = new PlayerData(gameState, name);
                    players.add(playerData);
                    if (name.isEmpty()) allNamesFilled = false;
                }

                if (allNamesFilled){
                    for (PlayerData player : players) {
                        networkService.pushHighscore(player);
                    }
                    // Sleep to let firebase get updated before pulling the data
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    eventManager.pushEvent(new LeaderboardSelectedEvent(gameStates.get(0).getMapNumber()));
                }
            }
        });

        table.row().padBottom(20f);
        table.add(saveScoreBtn).colspan(2).fillX();

        stage.addActor(table);
    }
}
