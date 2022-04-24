package com.tnig.game.model.models.obstacles;

import com.tnig.game.controller.events.game_events.PlayerDead;
import com.tnig.game.controller.managers.EventManager;
import com.tnig.game.model.models.AbstractModel;
import com.tnig.game.model.models.ObjectProperties;
import com.tnig.game.model.models.interfaces.ContactObject;
import com.tnig.game.model.models.interfaces.ModelType;
import com.tnig.game.model.models.ObjectShape;
import com.tnig.game.model.models.ObjectType;
import com.tnig.game.model.models.players.Player;
import com.tnig.game.model.physics_engine.Engine;

public class Spike extends AbstractModel {

    private static final boolean isStatic = true;
    private static final boolean isSensor = false;
    private static final ObjectShape shape = ObjectShape.TRIANGLE;

    private final EventManager eventManager;

    protected Spike(EventManager eventManager, Engine engine,
                    float x, float y, float width, float height,
                    ObjectProperties properties, ModelType type) {
        super(engine, x, y, width, height, properties, isStatic, isSensor, type);
        this.eventManager = eventManager;
    }

    @Override
    public void handleBeginContact(ContactObject object) {
        if (object.getType().getObjectType() == ObjectType.PLAYER){
            eventManager.pushEvent(new PlayerDead());
            object.dispose();
        }

    }


    @Override
    public ObjectShape GetShapeType() {
        return shape;
    }


}
