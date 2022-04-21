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
import com.tnig.game.model.models.ObjectShape;
import com.tnig.game.model.models.coins.Coin;
import com.tnig.game.model.physics_engine.Engine;
import com.tnig.game.utilities.Constants;

public class NormalPlayer extends AbstractModel implements EventListener {

    private static final boolean isStatic = false;
    private static final boolean isSensor = false;
    private static final ObjectShape shape = ObjectShape.BOX;
    private int score = 1000000;

    private final EventManager eventManager;

    private int speed = 2;
    private int jumpingForce = 200;
    private State STATE = State.RUNNING;



    public enum State{
        JUMPING, RUNNING
    }

    public NormalPlayer(EventManager eventManager, Engine engine,
                        float x, float y, float width, float height,
                        ModelType type) {
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


        switch (object.getType().getObjectType()){
            case OBSTACLE:
                dispose();
                break;
            case COIN:
                Coin coin = (Coin) object;
                score += coin.getValue();
                break;
            case SENSOR:
                // TODO: FINISH
                dispose();
                break;
            case BLOCK:
                if (STATE == State.JUMPING){
                    STATE = State.RUNNING;
                }
                break;


        }



    }

    @Override

    public void update(float delta){

        score -= 1157 / Constants.FPS;
    }

    @Override
    public ObjectShape GetShape() {
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
                if (STATE == State.RUNNING){
                    jump();

                }
                break;
            case STOP_JUMP:
                if (STATE == State.JUMPING) {
                    applyImpulseToCenter(new Vector2(0, -jumpingForce));
                }
        }
    }

    private void jump(){
        if (STATE == State.RUNNING){
            applyForceToCenter(0, jumpingForce);
            STATE = State.JUMPING;
        }
    }

}
