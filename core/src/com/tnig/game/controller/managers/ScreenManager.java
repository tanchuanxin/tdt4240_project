package com.tnig.game.controller.managers;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.tnig.game.controller.map.GameMap;
import com.tnig.game.model.networking.Network;
import com.tnig.game.utilities.AssetLoader;
import com.tnig.game.utilities.Constants;
import com.tnig.game.controller.events.EventListener;
import com.tnig.game.controller.events.Event;
import com.tnig.game.controller.events.EventName;
import com.tnig.game.view.screens.AppLoadingScreen;
import com.tnig.game.view.screens.GameScreen;
import com.tnig.game.view.screens.LeaderboardSelectScreen;
import com.tnig.game.view.screens.LeaderboardsScreen;
import com.tnig.game.view.screens.MainMenuScreen;
import com.tnig.game.view.screens.MapSelectScreen;
import com.tnig.game.view.screens.ScreenName;

public class ScreenManager implements EventListener {
    private final EventManager eventManager = new EventManager();
    private final Game game;
    private final OrthographicCamera camera;
    private final AssetLoader assetLoader;
    private final Network network;
    private int mapNumber = -1;
    private int numberOfPlayers = -1;

    public ScreenManager(Game game, OrthographicCamera camera, AssetLoader assetLoader, Network network) {
        this.game = game;
        this.camera = camera;
        this.assetLoader = assetLoader;
        this.network = network;

        // Subscribe to events

        eventManager.subscribe(EventName.MAP_SELECTED, this);
        eventManager.subscribe(EventName.NEW_GAME, this);
    }

    /**
     * Receives events that it is subscribed to.
     * @param event an Event to act upon
     */
    public void receiveEvent(Event event) {
        switch (event.name) {
            case MAP_SELECTED:
                mapNumber = (int) event.data.get("mapNum");
                break;
            case NEW_GAME:
                numberOfPlayers = (int) event.data.get("numOfPlayers");
                break;
            case QUIT_GAME:
                quitGame();
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
                String mapLocation = getMapLocation(mapNumber);
                GameMap map = new GameMap(mapLocation);
                Gdx.app.log("Map", mapLocation);
                Gdx.app.log("Number of Players", String.valueOf(numberOfPlayers));

                game.setScreen(new GameScreen(this, eventManager, camera, assetLoader, map, numberOfPlayers));
                break;
            case MAP_SELECT:
                game.setScreen(new MapSelectScreen(this, camera, assetLoader, eventManager));
                break;
            case LEADERBOARDSELECTION:
                game.setScreen(new LeaderboardSelectScreen(this, camera, assetLoader, eventManager, network));
                break;
            case LEADERBOARDS:
                game.setScreen(new LeaderboardsScreen(this, camera, assetLoader, eventManager, network));
                break;
            case GAME_OVER:
                throw new IllegalArgumentException("Not implemented yet");
        }
    }

    private void quitGame() {
        assetLoader.dispose();
        Gdx.app.exit();
    }

    private String getMapLocation(int mapNumber){
        return(Constants.MAP_ASSET_LOCATION + "map" + String.valueOf(mapNumber) + ".tmx");
//        switch (mapNumber){
//            case 1:
//                return Constants.map1;
//            case 2:
//                return Constants.map2;
//            case 3:
//                return Constants.map3;
//            default:
//                throw new IllegalArgumentException("Havent implemented map number:" + mapNumber);
//
//        }
    }
}
