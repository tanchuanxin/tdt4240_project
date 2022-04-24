package com.tnig.game.model.models.players;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.tnig.game.controller.events.Event;
import com.tnig.game.controller.events.EventListener;
import com.tnig.game.controller.events.EventName;
import com.tnig.game.controller.managers.EventManager;
import com.tnig.game.model.models.AbstractModel;
import com.tnig.game.model.models.ObjectProperties;
import com.tnig.game.model.models.interfaces.ContactObject;
import com.tnig.game.model.models.interfaces.ModelType;
import com.tnig.game.model.models.ObjectShape;
import com.tnig.game.model.models.coins.Coin;
import com.tnig.game.model.physics_engine.Engine;
import com.tnig.game.utilities.Constants;


public class NormalPlayer extends AbstractModel implements EventListener, Player {

    private static final boolean isStatic = false;
    private static final boolean isSensor = false;
    private static final ObjectShape shape = ObjectShape.BOX;
    private int score = 100000;


    private final EventManager eventManager;
    private float speed = 2.2f;
    private float jumpingForce = 3.7f;

    private PlayerState playerState;

    public PlayerDirection getDirection() {
        return playerDirection;
    }

    private PlayerDirection playerDirection = PlayerDirection.RIGHT;


    private float attackTimeout;
    private float winTimeout;

    public NormalPlayer(EventManager eventManager, Engine engine,
                        float x, float y, float width, float height,
                        ObjectProperties properties, ModelType type) {
        super(engine, x, y, width, height, properties, isStatic, isSensor, type);
        this.eventManager = eventManager;

        setPlayerState(PlayerState.RUNNING);
        eventManager.subscribe(EventName.JUMP, this);
        eventManager.subscribe(EventName.MOVE_LEFT, this);
        eventManager.subscribe(EventName.MOVE_RIGHT, this);
        eventManager.subscribe(EventName.ATTACK, this);
        eventManager.subscribe(EventName.STOP_PLAYER, this);
        eventManager.subscribe(EventName.PLAYER_AT_GOAL, this);

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

    private void attackTimeoutReset() {
        attackTimeout = 5;
    }

    @Override
    public void update(float delta) {
        score -= 1157 / Constants.FPS;
        attackTimeout -= delta;

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
                    playerDirection = PlayerDirection.LEFT;
                }
                break;
            case MOVE_RIGHT:
                if (playerState != PlayerState.WIN && playerState != PlayerState.DIE) {
                    setLinearVelocityX(speed);
                    playerDirection = PlayerDirection.RIGHT;
                }
                break;
            case JUMP:
                if (playerState == PlayerState.RUNNING) {
                    jump();
                }
                break;
            case STOP_PLAYER:
                switch (event.getData("key", int.class)) {
                    case Input.Keys.LEFT:
                        if (getLinearVelocity()[0] < 0) {
                            setLinearVelocityX(0);
                        }
                        break;
                    case Input.Keys.RIGHT:
                        if (getLinearVelocity()[0] > 0) {
                            setLinearVelocityX(0);
                        }
                        break;
                    case Input.Keys.DOWN:
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
                        playerDirection = PlayerDirection.RIGHT;
                    } else {
                        playerDirection = PlayerDirection.LEFT;
                    }
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

}
