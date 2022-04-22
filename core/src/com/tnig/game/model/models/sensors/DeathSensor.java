package com.tnig.game.model.models.sensors;

import com.tnig.game.controller.events.game_events.PlayerDead;
import com.tnig.game.controller.managers.EventManager;
import com.tnig.game.model.models.AbstractModel;
import com.tnig.game.model.models.ContactObject;
import com.tnig.game.model.models.ModelType;
import com.tnig.game.model.models.ObjectShape;
import com.tnig.game.model.models.ObjectType;
import com.tnig.game.model.models.coins.Coin;
import com.tnig.game.model.models.coins.CoinType;
import com.tnig.game.model.physics_engine.Engine;

public class DeathSensor extends AbstractModel {

    private static final boolean isStatic = true;
    private static final boolean isSensor = true;
    private static final ObjectShape shape = ObjectShape.BOX;
    private EventManager eventManager;


    protected DeathSensor(EventManager eventManager, Engine engine,
                          float x, float y, float width, float height,
                          ModelType type) {
        super(engine, x, y, width, height, isStatic, isSensor, type);
        this.eventManager = eventManager;
    }

    @Override
    public void handleBeginContact(ContactObject object) {
        if (object.getType().getObjectType() == ObjectType.PLAYER){
            eventManager.pushEvent(new PlayerDead(object));
        }

    }

    @Override
    public ObjectShape GetShape() {
        return shape;
    }
}
