package com.tnig.game.model.models.players;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.tnig.game.controller.events.Event;
import com.tnig.game.controller.events.EventListener;
import com.tnig.game.controller.events.EventName;
import com.tnig.game.controller.managers.EventManager;
import com.tnig.game.model.models.AbstractModel;
import com.tnig.game.model.models.ObjectProperties;
import com.tnig.game.model.models.enums.BodyType;
import com.tnig.game.model.models.interfaces.ContactObject;
import com.tnig.game.model.models.interfaces.ModelType;
import com.tnig.game.model.models.enums.ObjectShape;
import com.tnig.game.model.models.coins.Coin;
import com.tnig.game.model.physics_engine.Engine;
import com.tnig.game.utilities.Constants;

public class NormalPlayer extends AbstractModel implements EventListener, Player {

    private static final BodyType bodyType = BodyType.DYNAMIC;
    private static final boolean isSensor = false;
    private static final ObjectShape shape = ObjectShape.BOX;
    private int score = 100000;


    private final EventManager eventManager;
    private float speed = 2.2f;
    private float jumpingForce = 3.7f;
    private State STATE = State.RUNNING;

    public enum State {
        JUMPING, RUNNING, WIN, DIE
    }



    private float attackTimeout;

    public NormalPlayer(EventManager eventManager, Engine engine,
                        float x, float y, float width, float height,
                        ObjectProperties properties, ModelType type) {
        super(engine, x, y, width, height, properties, bodyType, isSensor, type);
        this.eventManager = eventManager;
        eventManager.subscribe(EventName.JUMP, this);
        eventManager.subscribe(EventName.MOVE_LEFT, this);
        eventManager.subscribe(EventName.MOVE_RIGHT, this);
        eventManager.subscribe(EventName.ATTACK, this);
        eventManager.subscribe(EventName.STOP_PLAYER, this);
        eventManager.subscribe(EventName.PLAYER_AT_GOAL, this);

        attackTimeoutReset();

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
                if (STATE == State.JUMPING) {
                    STATE = State.RUNNING;
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
    }
    
    @Override
    public int getScore() {
        return score;
    }

    public float getAttackTimeout() {
        return attackTimeout;
    }

    @Override
    public ObjectShape GetShapeType() {
        return shape;
    }

    @Override
    public void receiveEvent(Event event) {
        switch (event.name) {
            case MOVE_LEFT:
                setLinearVelocityX(-speed);
                break;
            case MOVE_RIGHT:
                setLinearVelocityX(speed);
                break;
            case JUMP:
                if (STATE == State.RUNNING) {
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
                        if (STATE == State.JUMPING) {
                            setLinearVelocityY(0);
                            applyImpulseToCenter(0, -jumpingForce);
                        }
                        break;
                    default:
                        setLinearVelocityX(0);
                }
                break;
            case PLAYER_AT_GOAL:
                Gdx.app.log("hi", "hi");
                break;
            case ATTACK:
                float randomImpulseX = (float) ((float) Math.signum(Math.random()-0.5) *  (0.5 + Math.random() * 0.5)) * 2;
                float randomImpulseY = (float) ((float) Math.signum(Math.random()-0.5) *  (1 + Math.random() * 1)) * 2;
                setLinearVelocityX(0);
                setLinearVelocityY(0);
                applyImpulseToCenter(randomImpulseX, randomImpulseY);
                attackTimeoutReset();
                break;
        }
    }

    private void jump() {
        if (STATE == State.RUNNING) {
            applyImpulseToCenter(0, jumpingForce);
            STATE = State.JUMPING;
        }
    }

}
