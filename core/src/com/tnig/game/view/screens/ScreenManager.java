package com.tnig.game.view.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.tnig.game.utillities.Constants;

public class ScreenManager {

    private Game game;
    private final OrthographicCamera camera = new OrthographicCamera();
    private static final ScreenManager INSTANCE = new ScreenManager();

    public static ScreenManager getInstance(){
        return INSTANCE;
    }

    private ScreenManager() {
        camera.setToOrtho(false, Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
    }

    //This is called by Game from inside the "create()" method.
    public void initialize(Game game) {
        this.game = game;
    }

    // EXAMPLE
    public void setLoadingScreen(){game.setScreen(new LoadingScreen(camera));}

    public void setMenuScreen(){ }

    public void setGameScreen(){}


}
