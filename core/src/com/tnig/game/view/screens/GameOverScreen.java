package com.tnig.game.view.screens;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.tnig.game.controller.managers.ScreenManager;
import com.tnig.game.model.GameState;
import com.tnig.game.model.models.players.Player;
import com.tnig.game.model.networking.NetworkService;
import com.tnig.game.model.networking.PlayerData;
import com.tnig.game.utilities.AssetLoader;

import java.util.ArrayList;
import java.util.List;

public class GameOverScreen extends AbstractScreen{

    private final List<GameState> gameStates;
    private final NetworkService networkService;
    private final Skin skin;
    private final Table table;

    public GameOverScreen(final ScreenManager screenManager,
                          OrthographicCamera camera, AssetLoader assetLoader,
                          final List<GameState> gameStates, final NetworkService networkService) {
        super(camera, assetLoader);
        this.gameStates = gameStates;
        this.networkService = networkService;

        skin = assetLoader.get(AssetLoader.SKIN_PIXTHULHU_UI);

        // Add actors to table layout
        table = new Table();
        table.pad(50f);
        table.setWidth(600f);
        table.setPosition(stage.getWidth() / 2, stage.getHeight() / 2, Align.center);

        final List<TextField> textFields = new ArrayList<>();

        for (GameState gameState : gameStates) {
            Label scoreLabel = new Label("Score: " + gameState.getScore(), skin);
            scoreLabel.setAlignment(Align.center);

            Label nameFieldLabel = new Label("Name: ", skin);
            nameFieldLabel.setAlignment(Align.center);

            TextField textField = new TextField("", skin);
            textField.setAlignment(Align.center);
            textFields.add(textField);

            table.add(scoreLabel, nameFieldLabel, textField).center();
        }


        TextButton saveScoreBtn = new TextButton("Save Score", skin, "default");
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
                    if (name.length() == 0) allNamesFilled = false;
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

                    screenManager.setScreen(ScreenName.LEADERBOARDS);


                }
            }
        });
        table.add(saveScoreBtn);
        stage.addActor(table);

    }
}
