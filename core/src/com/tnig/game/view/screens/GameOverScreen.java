package com.tnig.game.view.screens;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.tnig.game.model.GameState;
import com.tnig.game.model.networking.NetworkService;
import com.tnig.game.utilities.AssetLoader;

import java.util.List;

public class GameOverScreen extends AbstractScreen{

    private final List<GameState> gameStates;

    public GameOverScreen(OrthographicCamera camera, AssetLoader assetLoader, List<GameState> gameStates) {
        super(camera, assetLoader);
        this.gameStates = gameStates;
    }
}
