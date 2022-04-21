package com.tnig.game.model.models.players;

import com.badlogic.gdx.math.Vector2;
import com.tnig.game.controller.events.Event;
import com.tnig.game.controller.events.EventListener;
import com.tnig.game.controller.events.EventName;
import com.tnig.game.controller.events.game_events.PlayerDead;
import com.tnig.game.controller.managers.EventManager;
import com.tnig.game.model.models.AbstractModel;
import com.tnig.game.model.models.ContactObject;
import com.tnig.game.model.models.ModelType;
import com.tnig.game.model.models.Movable;
import com.tnig.game.model.models.ObjectType;
import com.tnig.game.model.physics_engine.Engine;

public class NormalPlayer extends AbstractModel implements EventListener {

    private static final boolean isStatic = false;
    private static final boolean isSensor = false;
    private static final ModelType type = PlayerType.NORMALPLAYER;

    private final EventManager eventManager;
    private int speed = 4;
    private int jumpingForce = 5;
    private int maxJumpVelocity = 2;
    private State STATE = State.RUNNING;

    public enum State {
        JUMPING, RUNNING
    }

    public NormalPlayer(EventManager eventManager, Engine engine, float x, float y, float width, float height) {
        super(engine, x, y, width, height, isStatic, isSensor, type);
        this.eventManager = eventManager;
        eventManager.subscribe(EventName.JUMP, this);
        eventManager.subscribe(EventName.MOVE_LEFT, this);
        eventManager.subscribe(EventName.MOVE_RIGHT, this);
        eventManager.subscribe(EventName.STOP_MOVE_LEFT, this);
        eventManager.subscribe(EventName.STOP_MOVE_RIGHT, this);
        eventManager.subscribe(EventName.STOP_JUMP, this);
    }

    @Override
    public void handleBeginContact(ContactObject object) {
        if (object.getType().getObjectType() == ObjectType.OBSTACLE) {
            eventManager.pushEvent(new PlayerDead(this));
            //dispose();
        }

    }

    @Override
    public void update(float delta) {
        float velocityY = getLinearVelocity().y;
        if (velocityY == 0) {
            STATE = State.RUNNING;
        } else {
            STATE = State.JUMPING;
        }
    }

    @Override
    public void receiveEvent(Event event) {
        switch (event.name) {
            case MOVE_LEFT:
                setLinearVelocity(new Vector2(-speed, getLinearVelocity().y));
                break;
            case MOVE_RIGHT:
                setLinearVelocity(new Vector2(speed, getLinearVelocity().y));
                break;
            case STOP_MOVE_LEFT:
            case STOP_MOVE_RIGHT:
                setLinearVelocity(new Vector2(0, getLinearVelocity().y));
                break;
            case JUMP:
                if (STATE == State.RUNNING) {
                    applyImpulseToCenter(new Vector2(0, jumpingForce));
                }
                break;
            case STOP_JUMP:
                if (STATE == State.JUMPING) {
                    applyImpulseToCenter(new Vector2(0, -jumpingForce));
                }
        }
    }

}
