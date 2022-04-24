package com.tnig.game.view.screens;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Align;
import com.tnig.game.model.GameState;
import com.tnig.game.model.networking.NetworkService;
import com.tnig.game.utilities.AssetLoader;

import java.util.List;

public class GameOverScreen extends AbstractScreen{

    private final List<GameState> gameStates;
    private final NetworkService networkService;
    private final Skin skin;
    private final Table table;

    public GameOverScreen(OrthographicCamera camera, AssetLoader assetLoader,
                          List<GameState> gameStates, NetworkService networkService) {
        super(camera, assetLoader);
        this.gameStates = gameStates;
        this.networkService = networkService;

        skin = assetLoader.get(AssetLoader.SKIN_PIXTHULHU_UI);

        // Add actors to table layout
        table = new Table();
        table.pad(50f);
        table.setWidth(600f);
        table.setPosition(stage.getWidth() / 2, stage.getHeight() / 2, Align.center);

        for (GameState gameState : gameStates) {
            Label scoreLabel = new Label("Score: " + gameState.getScore(), skin);
            scoreLabel.setAlignment(Align.center);

            Label nameFieldLabel = new Label("Name: ", skin);
            nameFieldLabel.setAlignment(Align.center);

            TextField textField = new TextField("", skin);
            textField.setAlignment(Align.center);

            table.add(scoreLabel, nameFieldLabel, textField).center();

        }

        TextButton saveScoreBtn = new TextButton("Save Score", skin, "default");

        stage.addActor(table);

    }
}
