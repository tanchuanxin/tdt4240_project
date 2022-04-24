package com.tnig.game.controller.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.tnig.game.controller.events.Event;
import com.tnig.game.controller.events.EventListener;
import com.tnig.game.controller.events.EventName;
import com.tnig.game.controller.events.game_events.PlayerDead;
import com.tnig.game.controller.events.game_events.StopPlayer;
import com.tnig.game.controller.events.screen_events.GameOverEvent;
import com.tnig.game.controller.events.screen_events.NewGameEvent;
import com.tnig.game.controller.game_initializers.GameInitializer;
import com.tnig.game.controller.game_initializers.NormalGame;
import com.tnig.game.controller.game_objects.Controller;
import com.tnig.game.controller.game_objects.dynamic_objects.AnimatedController;
import com.tnig.game.controller.map.GameMap;
import com.tnig.game.model.GameState;
import com.tnig.game.model.models.interfaces.Model;
import com.tnig.game.model.models.players.Player;
import com.tnig.game.model.physics_engine.Engine;
import com.tnig.game.model.physics_engine.GameWorld;
import com.tnig.game.utilities.AssetLoader;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;

/**
 * Contains a list of all the controllers in the game, and contains the logic for updating and
 * removing controllers, while rendering the game.
 */
public class GameManager implements EventListener {

    private final EventManager eventManager;
    private Engine engine;
    private AssetLoader assetLoader;
    private int playersLeft;

    public GameMap getMap() {
        return map;
    }

    private GameMap map;
    private GameInitializer game;


    public GameManager(EventManager eventManager,
                       AssetLoader assetLoader,
                       GameMap map,
                       Viewport viewport,
                       int numberOfPlayers) {
        this.eventManager = eventManager;
        this.map = map;
        this.assetLoader = assetLoader;
        this.engine = new GameWorld(viewport);
        this.game = new NormalGame(eventManager, engine, assetLoader, map);
        Gdx.app.log("=================================", "=================================");
        this.playersLeft = numberOfPlayers - 1;
        Gdx.app.log("this.playersLeft: ", String.valueOf(this.playersLeft));
        Gdx.app.log("=================================", "=================================");


        eventManager.subscribe(EventName.PLAYER_DEAD, this);
        eventManager.subscribe(EventName.DISPOSE_SPRITE, this);
        eventManager.subscribe(EventName.PLAYER_AT_GOAL, this);
    }

    public void newGame(){
        Gdx.app.log("NEW GAME called!", "hey");
        if (playersLeft == 0){
            throw new IllegalStateException("Players left cant be 0");
        }

        engine.initNewWorld();

        map = new GameMap(map.getMapNumber());
        game = new NormalGame(eventManager, engine, assetLoader, map);
    }

    /**
     * Updates all controllers in the world and removes those that are disposable.
     * @param delta timestep
     */
    public void update(float delta){
        // Uses iterator2 instead of for loop so it is possible to remove elements from the list
        // While iterating
        engine.update(delta);

        Iterator<Controller> iterator = game.getControllers().iterator();
        while(iterator.hasNext()){
            Controller controller = iterator.next();
            if (controller.isDisposable()){
                iterator.remove();
                engine.disposeModel(controller.getModel());
            }
            else {
                controller.update(delta);
            }
        }

        // Update animated controllers
        Iterator<AnimatedController> iterator2 = game.getAnimatedControllers().iterator();
        while(iterator2.hasNext()){
            AnimatedController controller = iterator2.next();
            if (controller.isDisposable()){
                iterator2.remove();
                engine.disposeModel(controller.getModel());
            }
            else {
                controller.update(delta);
            }
        }

        triggerDeathAfterWin();
    }

    private GameState createGameState(){
        return new GameState(getScore(), map.getMapNumber());
    }

    public int getScore() {
        Model model = game.getPlayer().getModel();
        Player player = (Player) model;
        int score = (int) (player.getScore() * model.getX() / map.getMapWidthInUnits());
        return score;
    }

    public float getAttackTimeout() {
        Model model = game.getPlayer().getModel();
        Player player = (Player) model;
        return player.getAttackTimeout();
    }

    @Override
    public void receiveEvent(Event event) {
        switch (event.name){
            case PLAYER_AT_GOAL:
                eventManager.pushEvent(new StopPlayer(Input.Keys.RIGHT));
                eventManager.pushEvent(new StopPlayer(Input.Keys.LEFT));
                break;
            case PLAYER_DEAD:
                if (playersLeft > 0){
                    eventManager.pushEvent(new NewGameEvent(createGameState()));
                }
                else {
                    eventManager.pushEvent(new GameOverEvent(createGameState()));
                }
                playersLeft--;
                break;
            case DISPOSE_SPRITE:
                Model model = event.getData("object", Model.class);
                map.disposeGraphicOnTile(model.getX(), model.getY());
                break;
        }
    }

    public void dispose(){
        engine.dispose();
    }

    public List<AnimatedController> getAnimatedControllers() {
        return game.getAnimatedControllers();
    }

    public float getPlayerPosX() {
        return game.getPlayer().getModel().getX();
    }

    public float getPlayerPosY() {
        return game.getPlayer().getModel().getY();
    }

    public Engine getEngine() { return engine; }

    public List<Controller> getControllers() {
        return game.getControllers();
    }

    private void triggerDeathAfterWin() {
        Model model = game.getPlayer().getModel();
        Player player = (Player) model;
        if (player.getWinTimeout() <= 0) {
            Gdx.app.log("playersLeft", String.valueOf(playersLeft));
            eventManager.pushEvent(new PlayerDead());
//            if (playersLeft > 0){
//                eventManager.pushEvent(new NewGameEvent(createGameState()));
//            }
//            else {
//                eventManager.pushEvent(new GameOverEvent(createGameState()));
//            }
        }
    }
}
