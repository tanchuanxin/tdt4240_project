package com.tnig.game.model.models.players;

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
    private static final ModelType type = PlayerType.NORMALPLAYER;
    private static final ObjectShape shape = ObjectShape.BOX;
    private int score = 1000000;

    private final EventManager eventManager;
    private int speed = 4;
    private int jumpingForce = 100;
    private State STATE = State.RUNNING;



    public enum State{
        JUMPING, RUNNING
    }

    public NormalPlayer(EventManager eventManager, Engine engine, String layer,
                        float x, float y, float width, float height) {
        super(engine, layer, x, y, width, height, isStatic, isSensor, type);
        this.eventManager = eventManager;
        eventManager.subscribe(EventName.JUMP, this);
        eventManager.subscribe(EventName.MOVE_LEFT, this);
        eventManager.subscribe(EventName.MOVE_RIGHT, this);
    }

    @Override
    public void handleBeginContact(ContactObject object) {

        switch (object.getType().getObjectType()){
            case OBSTACLE:
                eventManager.pushEvent(new PlayerDead(this));
                dispose();
                break;
            case COIN:
                Coin coin = (Coin) object;
                score += coin.getValue();
        }



    }

    @Override
    public void update(float delta){
        float velocity_y = getLinearVelocity()[1];
        if (velocity_y == 0){
            STATE = State.RUNNING;
        }
        else {
            STATE = State.JUMPING;
        }

        score -= 1157 / Constants.FPS;
    }

    @Override
    public ObjectShape GetShape() {
        return shape;
    }

    @Override
    public void receiveEvent(Event event) {
        switch (event.name){
            case MOVE_LEFT:
                setLinearVelocityX(-speed);
                break;
            case MOVE_RIGHT:
                setLinearVelocityX(speed);
                break;
            case JUMP:
                if (STATE == State.RUNNING){
                    applyForceToCenter(0, jumpingForce);
                }
                break;
        }
    }

}
