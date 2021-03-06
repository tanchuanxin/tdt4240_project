package com.tnig.game.model.models.players;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.tnig.game.controller.events.Event;
import com.tnig.game.controller.events.EventListener;
import com.tnig.game.controller.events.EventName;
import com.tnig.game.controller.events.game_events.StopPlayer;
import com.tnig.game.controller.managers.EventManager;
import com.tnig.game.model.models.AbstractModel;
import com.tnig.game.model.models.ObjectProperties;
import com.tnig.game.model.models.enums.BodyType;
import com.tnig.game.model.models.enums.Direction;
import com.tnig.game.model.models.interfaces.ContactObject;
import com.tnig.game.model.models.interfaces.ModelType;
import com.tnig.game.model.models.enums.ObjectShape;
import com.tnig.game.model.models.coins.Coin;
import com.tnig.game.model.physics_engine.Engine;
import com.tnig.game.utilities.AssetLoader;
import com.tnig.game.utilities.Constants;


public class NormalPlayer extends AbstractModel implements EventListener, Player {
    private final EventManager eventManager;

    private static final BodyType bodyType = BodyType.DYNAMIC;
    private static final boolean isSensor = false;
    private static final ObjectShape shape = ObjectShape.BOX;

    private PlayerState playerState;
    private Direction playerDirection;

    private Sound winSound;
    private Sound jumpSound;
    private Sound attackSound;

    private int score = 100000;
    private float speed = 2.2f;
    private float jumpingForce = 3.7f;
    private float attackTimeout;
    private float winTimeout;

    public NormalPlayer(EventManager eventManager, Engine engine, AssetLoader assetLoader,
                        float x, float y, float width, float height,
                        ObjectProperties properties, ModelType type) {
        super(engine, x, y, width, height, properties, bodyType, isSensor, type);
        this.eventManager = eventManager;

        // subscribe to player relevant events
        eventManager.subscribe(EventName.JUMP, this);
        eventManager.subscribe(EventName.MOVE_LEFT, this);
        eventManager.subscribe(EventName.MOVE_RIGHT, this);
        eventManager.subscribe(EventName.ATTACK, this);
        eventManager.subscribe(EventName.STOP_PLAYER, this);
        eventManager.subscribe(EventName.PLAYER_AT_GOAL, this);

        // set animation related variables
        setPlayerState(PlayerState.RUNNING);
        playerDirection = Direction.RIGHT;

        // load in sounds
        this.winSound = assetLoader.get(AssetLoader.SOUND_WIN);
        this.jumpSound = assetLoader.get(AssetLoader.SOUND_JUMP);
        this.attackSound = assetLoader.get(AssetLoader.SOUND_PUNCH);

        // time outs for the player
        attackTimeout = 5;
        winTimeout = 2;
    }

    @Override
    public void handleBeginContact(ContactObject object) {
        ModelType type = object.getType();

        switch (type.getObjectType()) {
            case COIN:
                Coin coin = (Coin) object;
                score += coin.getValue();
                break;
            case BLOCK:
                if (playerState == PlayerState.JUMPING) {
                    setPlayerState(PlayerState.RUNNING);
                }
                break;
        }
    }

    @Override
    public void update(float delta) {
        // reduce the score with passing time
        score -= 1157 / Constants.FPS;

        // reduce the attack timer to allow for attacks
        attackTimeout -= delta;

        // reduce the win timer to advance game state
        if (playerState == PlayerState.WIN) {
            winTimeout -= delta;
        }
    }

    @Override
    public int getScore() {
        return score;
    }

    public float getAttackTimeout() {
        return attackTimeout;
    }

    @Override
    public float getWinTimeout() {
        return winTimeout;
    }

    @Override
    public PlayerState getState() {
        return playerState;
    }

    @Override
    public ObjectShape GetShapeType() {
        return shape;
    }

    @Override
    public void receiveEvent(Event event) {
        switch (event.name) {
            case MOVE_LEFT:
                if (playerState != PlayerState.WIN && playerState != PlayerState.DIE) {
                    setLinearVelocityX(-speed);
                    playerDirection = Direction.LEFT;
                }
                break;
            case MOVE_RIGHT:
                if (playerState != PlayerState.WIN && playerState != PlayerState.DIE) {
                    setLinearVelocityX(speed);
                    playerDirection = Direction.RIGHT;
                }
                break;
            case JUMP:
                if (playerState == PlayerState.RUNNING) {
                    jumpSound.play();
                    jump();
                } else if (playerState == PlayerState.JUMPING) {
                    // perform stomp on jump button only on android
                    if (Gdx.app.getType() == Application.ApplicationType.Android) {
                        eventManager.pushEvent(new StopPlayer(Input.Keys.DOWN));
                    }
                }
                break;
            case STOP_PLAYER:
                switch (event.getData("key", int.class)) {
                    case Input.Keys.LEFT:
                    case Input.Keys.A:
                        if (getLinearVelocity()[0] < 0) {
                            setLinearVelocityX(0);
                        }
                        break;
                    case Input.Keys.RIGHT:
                    case Input.Keys.D:
                        if (getLinearVelocity()[0] > 0) {
                            setLinearVelocityX(0);
                        }
                        break;
                    case Input.Keys.DOWN:
                    case Input.Keys.S:
                        if (playerState == PlayerState.JUMPING) {
                            setLinearVelocityY(0);
                            applyImpulseToCenter(0, -jumpingForce);
                        }
                        break;
                    default:
                        setLinearVelocityX(0);
                }
                break;
            case PLAYER_AT_GOAL:
                winSound.play();
                setPlayerState(PlayerState.WIN);
                applyImpulseToCenter(0, jumpingForce);
                break;
            case ATTACK:
                if (playerState != PlayerState.WIN && playerState != PlayerState.DIE) {
                    float randomImpulseX = (float) ((float) Math.signum(Math.random() - 0.5) * (0.5 + Math.random() * 0.5)) * 2;
                    float randomImpulseY = (float) ((float) Math.signum(Math.random() - 0.5) * (1 + Math.random() * 1)) * 2;
                    setLinearVelocityX(0);
                    setLinearVelocityY(0);
                    applyImpulseToCenter(randomImpulseX, randomImpulseY);
                    attackTimeoutReset();

                    if (randomImpulseX > 0) {
                        playerDirection = Direction.RIGHT;
                    } else {
                        playerDirection = Direction.LEFT;
                    }
                    attackSound.play();
                }
                break;
        }
    }

    private void jump() {
        if (playerState == PlayerState.RUNNING) {
            applyImpulseToCenter(0, jumpingForce);
            setPlayerState(PlayerState.JUMPING);
        }
    }

    private void setPlayerState(PlayerState newState) {
        if (playerState != PlayerState.WIN && playerState != PlayerState.DIE) {
            playerState = newState;
        }
    }

    public Direction getDirection() {
        return playerDirection;
    }

    private void attackTimeoutReset() {
        attackTimeout = 5;
    }

}
