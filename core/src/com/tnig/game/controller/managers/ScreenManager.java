package com.tnig.game.controller.managers;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.tnig.game.controller.game_maps.GameMap;
import com.tnig.game.controller.game_maps.Level_1;
import com.tnig.game.controller.screens.LeaderboardSelectScreen;
import com.tnig.game.controller.screens.ScreenName;
import com.tnig.game.model.networking.Network;
import com.tnig.game.utilities.AssetLoader;
import com.tnig.game.controller.screens.GameScreen;
import com.tnig.game.controller.screens.LeaderboardsScreen;
import com.tnig.game.controller.screens.AppLoadingScreen;
import com.tnig.game.controller.screens.MainMenuScreen;
import com.tnig.game.controller.screens.MapSelectScreen;
import com.tnig.game.utilities.events.Event;
import com.tnig.game.utilities.events.EventListener;
import com.tnig.game.utilities.events.EventManager;
import com.tnig.game.utilities.events.EventName;
import com.tnig.game.view.guis.AppLoadingScreenGUI;
import com.tnig.game.view.guis.LeaderBoardSelectScreenGUI;
import com.tnig.game.view.guis.LeaderboardsScreenGUI;
import com.tnig.game.view.guis.MainMenuScreenGUI;
import com.tnig.game.view.guis.MapSelectScreenGUI;

public class ScreenManager implements EventListener {
    private Game game;
    private final OrthographicCamera camera;
    private final AssetLoader assetLoader;
    private final EventManager eventManager;
    private Network network;

    public ScreenManager(Game game, EventManager eventManager, OrthographicCamera camera, AssetLoader assetLoader, Network network) {
        this.game = game;
        this.eventManager = eventManager;
        this.camera = camera;
        this.assetLoader = assetLoader;
        this.network = network;

        // Subscribe to events
        eventManager.subscribe(EventName.INIT_APP, this);
        eventManager.subscribe(EventName.NEW_GAME, this);
        eventManager.subscribe(EventName.APP_LOADING_COMPLETE, this);
        eventManager.subscribe(EventName.VIEW_MAIN_MENU, this);
        eventManager.subscribe(EventName.VIEW_SETTINGS, this);
        eventManager.subscribe(EventName.VIEW_LEADERBOARDS, this);
        eventManager.subscribe(EventName.LEADERBOARD_SELECTED, this);
        eventManager.subscribe(EventName.QUIT_GAME, this);
    }

    /**
     * Receives events that it is subscribed to.
     * @param event an Event to act upon
     */
    public void receiveEvent(Event event) {
        switch (event.name) {
            case INIT_APP:
                setScreen(ScreenName.LOADING);
                break;
            case NEW_GAME:
                setScreen(ScreenName.MAP_SELECT);
                break;
            case APP_LOADING_COMPLETE:
            case VIEW_MAIN_MENU:
                setScreen(ScreenName.MAIN_MENU);
                break;
            case VIEW_SETTINGS:
                setScreen(ScreenName.SETTINGS);
                break;
            case VIEW_LEADERBOARDS:
                setScreen(ScreenName.LEADERBOARDS);
                break;
            case LEADERBOARD_SELECTED:
                setScreen(ScreenName.LEADERBOARDSELECTION);
                break;
            case QUIT_GAME:
                // TODO: Show quit game dialog for confirmation
                // For now we just quit
                quitGame();
                break;
            case QUIT_GAME_CONFIRMED:
                quitGame();
        }
    }

    // Switch screens
    private void setScreen(ScreenName screenName) {
        switch (screenName) {
            case LOADING:
                game.setScreen(new AppLoadingScreen(camera, assetLoader, new AppLoadingScreenGUI(camera, assetLoader), eventManager));
                break;
            case MAIN_MENU:
                game.setScreen(new MainMenuScreen(camera, assetLoader, new MainMenuScreenGUI(camera, assetLoader, eventManager)));
                break;
            case GAME:
                //TODO: need to fix so that any map can be selected
                GameMap map = new Level_1();
                game.setScreen(new GameScreen(camera, assetLoader, map));
                break;
            case MAP_SELECT:
                game.setScreen(new MapSelectScreen(camera, assetLoader, new MapSelectScreenGUI(camera, assetLoader, eventManager)));
                break;
            case LEADERBOARDSELECTION:
                game.setScreen(new LeaderboardSelectScreen(camera, assetLoader, new LeaderBoardSelectScreenGUI(camera, assetLoader, eventManager)));
                break;
            case LEADERBOARDS:
                game.setScreen(new LeaderboardsScreen(camera, assetLoader, new LeaderboardsScreenGUI(camera, assetLoader, eventManager, network, 1)));
                break;
            case GAME_OVER:
                throw new IllegalArgumentException("Not implemented yet");
        }
    }

    private void quitGame() {
        assetLoader.dispose();
        Gdx.app.exit();
    }
}
