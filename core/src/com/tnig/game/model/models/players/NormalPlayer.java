package com.tnig.game.model.models.players;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.tnig.game.controller.events.Event;
import com.tnig.game.controller.events.EventListener;
import com.tnig.game.controller.events.EventName;
import com.tnig.game.controller.managers.EventManager;
import com.tnig.game.model.models.AbstractModel;
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
    private State STATE = State.RUNNING;

    public enum State {
        JUMPING, RUNNING
    }

    public NormalPlayer(EventManager eventManager, Engine engine,
                        float x, float y, float width, float height, float rotation,
                        ModelType type) {
        super(engine, x, y, width, height, rotation, isStatic, isSensor, type);
        this.eventManager = eventManager;
        eventManager.subscribe(EventName.JUMP, this);
        eventManager.subscribe(EventName.MOVE_LEFT, this);
        eventManager.subscribe(EventName.MOVE_RIGHT, this);
        eventManager.subscribe(EventName.STOP_PLAYER, this);
        eventManager.subscribe(EventName.PLAYER_AT_GOAL, this);

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


    @Override
    public void update(float delta) {
        score -= 1157 / Constants.FPS;
    }
    
    @Override
    public int getScore() {
        return score;
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
        }
    }

    private void jump() {
        if (STATE == State.RUNNING) {
            applyImpulseToCenter(0, jumpingForce);
            STATE = State.JUMPING;
        }
    }

}
