package com.tnig.game.model.models.sensors;

import com.tnig.game.controller.events.game_events.PlayerDead;
import com.tnig.game.controller.managers.EventManager;
import com.tnig.game.model.models.interfaces.ContactObject;
import com.tnig.game.model.models.interfaces.ModelType;
import com.tnig.game.model.models.ObjectType;
import com.tnig.game.model.physics_engine.Engine;

public class DeathSensor extends Sensor {


    private final EventManager eventManager;


    protected DeathSensor(EventManager eventManager, Engine engine,
                          float x, float y, float width, float height,
                          ModelType type) {
        super(engine, x, y, width, height, type);
        this.eventManager = eventManager;
    }

    @Override
    public void handleBeginContact(ContactObject object) {
        if (object.getEnum().getObjectType() == ObjectType.PLAYER){
            eventManager.pushEvent(new PlayerDead(object));
            object.dispose();
        }

    }

}
