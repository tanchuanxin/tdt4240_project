package com.tnig.game.model.models.players;

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
    private int jumpingForce = 200;

    public NormalPlayer(EventManager eventManager, Engine engine, float x, float y, float width, float height) {
        super(engine, x, y, width, height, isStatic, isSensor, type);
        this.eventManager = eventManager;
        eventManager.subscribe(EventName.JUMP, this);
        eventManager.subscribe(EventName.MOVE_LEFT, this);
        eventManager.subscribe(EventName.MOVE_RIGHT, this);
    }

    @Override
    public void handleBeginContact(ContactObject object) {
        if (object.getType().getObjectType() == ObjectType.OBSTACLE){
            dispose();
            eventManager.pushEvent(new PlayerDead(this));
        }

    }



    @Override
    public void receiveEvent(Event event) {
        switch (event.name){
            case MOVE_LEFT:
                setLinearVelocity(-speed, 0);
                break;
            case MOVE_RIGHT:
                setLinearVelocity(speed, 0);
                break;
            case JUMP:
                applyForceToCenter(0, jumpingForce);
                break;
        }
    }

}
