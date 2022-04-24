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
import com.tnig.game.controller.managers.EventManager;
import com.tnig.game.controller.managers.ScreenManager;
import com.tnig.game.model.GameState;
import com.tnig.game.model.networking.NetworkService;
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

    public GameOverScreen(ScreenManager screenManager, OrthographicCamera camera, AssetLoader assetLoader, final EventManager eventManager,
                          final List<GameState> gameStates, NetworkService networkService) {
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

        Label titleLabel = new Label("Upload scores", skin);
        titleLabel.setAlignment(Align.center);
        table.add(titleLabel).colspan(2).fillX().align(Align.center);
        table.row().padBottom(30f);

        Gdx.app.log("Gamestates: ", String.valueOf(gameStates.size()));
        Gdx.app.log("Stage width: ", String.valueOf(stage.getWidth()));
        Gdx.app.log("Stage height: ", String.valueOf(stage.getHeight()));

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

        Button saveScoreBtn = new Button(new Label("Submit", skin), skin);
        saveScoreBtn.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent inputEvent, float x, float y, int pointer, int button) {
                HashMap<String, Object> scores = new HashMap<>();

                for (int playerNum = 1; playerNum <= gameStates.size(); playerNum++) {
                    TextField textField = textFields.get(playerNum - 1);

                    Gdx.app.log("Player name: ", textField.getText());

                    if (textField.getText().isEmpty()) {
                        // TODO: Handle error of empty player name
                        Gdx.app.log("No player name input: ", String.valueOf(textField));
                        return;
                    }

                    scores.put(textField.getText(), gameStates.get(playerNum - 1));
                }

                eventManager.pushEvent(new UploadScore(scores));
            }
        });

        table.row().padBottom(20f);
        table.add(saveScoreBtn).colspan(2).fillX();

        stage.addActor(table);
    }
}
