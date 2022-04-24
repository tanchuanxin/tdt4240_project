package com.tnig.game.controller.managers;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.tnig.game.controller.game_initializers.NormalGame;
import com.tnig.game.model.GameState;
import com.tnig.game.model.networking.NetworkService;
import com.tnig.game.utilities.AssetLoader;
import com.tnig.game.controller.events.EventListener;
import com.tnig.game.controller.events.Event;
import com.tnig.game.controller.events.EventName;
import com.tnig.game.view.screens.AppLoadingScreen;
import com.tnig.game.view.screens.GameOverScreen;
import com.tnig.game.view.screens.GameScreen;
import com.tnig.game.view.screens.LeaderboardSelectScreen;
import com.tnig.game.view.screens.LeaderboardsScreen;
import com.tnig.game.view.screens.MainMenuScreen;
import com.tnig.game.view.screens.MapSelectScreen;
import com.tnig.game.view.screens.ScreenName;

import java.util.ArrayList;
import java.util.List;

public class ScreenManager implements EventListener {
    private final EventManager eventManager = new EventManager();
    private final Game game;
    private final OrthographicCamera camera;
    private final AssetLoader assetLoader;
    private final NetworkService networkService;
    private int mapNumber = -1;
    private int leaderboardMapNum = 1;
    private int numberOfPlayers = -1;
    private List<GameState> gameStates = new ArrayList<>();

    public ScreenManager(Game game, OrthographicCamera camera, AssetLoader assetLoader, NetworkService networkService) {
        this.game = game;
        this.camera = camera;
        this.assetLoader = assetLoader;
        this.networkService = networkService;

        // Subscribe to events

        eventManager.subscribe(EventName.MAP_SELECTED, this);
        eventManager.subscribe(EventName.VIEW_LEADERBOARDS, this);
        eventManager.subscribe(EventName.INIT_GAME, this);
        eventManager.subscribe(EventName.GAME_OVER, this);
        eventManager.subscribe(EventName.NEW_GAME, this);
        eventManager.subscribe(EventName.PAUSE, this);
        eventManager.subscribe(EventName.QUIT_GAME, this);
    }

    /**
     * Receives events that it is subscribed to.
     * @param event an Event to act upon
     */
    public void receiveEvent(Event event) {
        switch (event.name) {
            case MAP_SELECTED:
                mapNumber = event.getData("mapNum", int.class);
                break;
            case INIT_GAME:
                numberOfPlayers = event.getData("numOfPlayers", int.class);
                break;
            case NEW_GAME:
            case GAME_OVER:
                gameStates.add(event.getData("gamestate", GameState.class));
                break;
            case PAUSE:
                game.pause();
                break;
            case VIEW_LEADERBOARDS:
                leaderboardMapNum = event.getData("mapNum", int.class) != null ? event.getData("mapNum", int.class) : 1;
                setScreen(ScreenName.LEADERBOARDS);
                break;
            case QUIT_GAME:
                quitGame();
                break;
        }
    }

    // Switch screens
    public void setScreen(ScreenName screenName) {
        switch (screenName) {
            case LOADING:
                game.setScreen(new AppLoadingScreen(this, camera, assetLoader, eventManager));
                break;
            case MAIN_MENU:
                game.setScreen(new MainMenuScreen(this, camera, assetLoader, eventManager));
                break;
            case GAME:
                if (mapNumber == -1){
                    throw new IllegalStateException("Map hasnt been updated");
                }
                game.setScreen(new GameScreen(this, eventManager, camera, assetLoader, mapNumber, numberOfPlayers));
                break;
            case MAP_SELECT:
                game.setScreen(new MapSelectScreen(this, camera, assetLoader, eventManager));
                break;
            case LEADERBOARD_SELECTION:
                game.setScreen(new LeaderboardSelectScreen(this, camera, assetLoader, eventManager));
                break;
            case LEADERBOARDS:
                game.setScreen(new LeaderboardsScreen(this, camera, assetLoader, eventManager, networkService, leaderboardMapNum));
                break;
            case GAME_OVER:
                game.setScreen(new GameOverScreen(camera, assetLoader, gameStates, networkService));
        }
    }

    private void quitGame() {
        assetLoader.dispose();
        Gdx.app.exit();
    }
}
