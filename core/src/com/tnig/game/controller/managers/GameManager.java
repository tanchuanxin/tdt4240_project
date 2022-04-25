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
import com.tnig.game.model.models.players.PlayerState;
import com.tnig.game.model.physics_engine.Engine;
import com.tnig.game.model.physics_engine.GameWorld;
import com.tnig.game.utilities.AssetLoader;

import java.util.Iterator;
import java.util.List;

/**
 * Contains a list of all the controllers in the game, and contains the logic for updating and
 * removing controllers, while rendering the game.
 */
public class GameManager implements EventListener {
    private final EventManager eventManager;
    private Engine engine;
    private AssetLoader assetLoader;
    private GameMap map;
    private GameInitializer game;

    // used to control the next screen/game to be initialized
    private int playersLeft;

    // used to indicate that the game is over
    private boolean gameOver = false;

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
        this.playersLeft = numberOfPlayers;

        // subscribe to events that the gamemanager is watching that effect the whole game state
        eventManager.subscribe(EventName.PLAYER_DEAD, this);
        eventManager.subscribe(EventName.DISPOSE_SPRITE, this);
        eventManager.subscribe(EventName.PLAYER_AT_GOAL, this);
    }

    /**
     * sets up a new game if there are more than 0 players remaining
     */
    public void newGame(){
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

        // go through all static controllers to update them
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

        // go through all animated controllers to update them
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

        // after the player wins, we will trigger a death state to advance the game
        triggerDeathAfterWin();
    }

    private GameState createGameState(){
        return new GameState(getScore(), map.getMapNumber());
    }

    /**
     * returns the players' score
     */
    public int getScore() {
        Model model = game.getPlayer().getModel();
        Player player = (Player) model;
        int score = (int) (player.getScore() * model.getX() / map.getMapWidthInUnits());
        return score;
    }

    /**
     * timeout set on the attack event, to make the game balanced
     */
    public float getAttackTimeout() {
        Model model = game.getPlayer().getModel();
        Player player = (Player) model;
        return player.getAttackTimeout();
    }

    @Override
    public void receiveEvent(Event event) {
        switch (event.name){
            case PLAYER_AT_GOAL:
                // stop movement of player once at the goal
                eventManager.pushEvent(new StopPlayer(Input.Keys.RIGHT));
                eventManager.pushEvent(new StopPlayer(Input.Keys.LEFT));
                break;
            case PLAYER_DEAD:
                // advance the game by removing a player and pushing the right event based on players remaining
                playersLeft--;
                if (playersLeft > 0){
                    eventManager.pushEvent(new NewGameEvent(createGameState()));
                }
                else {
                    if (!gameOver) {
                        gameOver = true;
                        eventManager.pushEvent(new GameOverEvent(createGameState()));
                    }
                }
                break;
            case DISPOSE_SPRITE:
                // remove graphics from the game, based on relevant collision events
                Model model = event.getData("object", Model.class);
                map.disposeGraphicOnTile(model.getX(), model.getY());
                break;
        }
    }

    /**
     * triggers death to advance game
     */
    private void triggerDeathAfterWin() {
        Model model = game.getPlayer().getModel();
        Player player = (Player) model;
        if (player.getWinTimeout() <= 0 && player.getState() != PlayerState.DIE) {
            eventManager.pushEvent(new PlayerDead());
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

    public GameMap getMap() {
        return map;
    }


}
