package com.tnig.game.model.models.sensors;

import com.tnig.game.controller.events.game_events.PlayerDead;
import com.tnig.game.controller.managers.EventManager;
import com.tnig.game.model.models.ObjectProperties;
import com.tnig.game.model.models.enums.BodyType;
import com.tnig.game.model.models.interfaces.ContactObject;
import com.tnig.game.model.models.interfaces.ModelType;
import com.tnig.game.model.models.enums.ObjectType;
import com.tnig.game.model.physics_engine.Engine;
import com.tnig.game.utilities.AssetLoader;

public class DeathSensor extends Sensor {
    private static final BodyType bodyType = BodyType.STATIC;
    private final EventManager eventManager;

    protected DeathSensor(EventManager eventManager, Engine engine, AssetLoader assetLoader,
                          float x, float y, float width, float height,
                          ObjectProperties properties, ModelType type) {
        super(engine, bodyType, x, y, width, height, properties, type);
        this.eventManager = eventManager;
    }

    @Override
    public void handleBeginContact(ContactObject object) {
        if (object.getType().getObjectType() == ObjectType.PLAYER){
            eventManager.pushEvent(new PlayerDead());
            object.dispose();
        }

    }

}
