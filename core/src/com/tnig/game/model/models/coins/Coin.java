package com.tnig.game.model.models.coins;

import com.badlogic.gdx.Gdx;
import com.tnig.game.controller.events.game_events.DisposeSprite;
import com.tnig.game.controller.managers.EventManager;
import com.tnig.game.model.models.AbstractModel;
import com.tnig.game.model.models.ObjectProperties;
import com.tnig.game.model.models.interfaces.ContactObject;
import com.tnig.game.model.models.interfaces.ModelType;
import com.tnig.game.model.models.ObjectShape;
import com.tnig.game.model.models.ObjectType;
import com.tnig.game.model.physics_engine.Engine;

public class Coin extends AbstractModel {
    private static final boolean isStatic = true;
    private static final boolean isSensor = true;
    private static final ObjectShape shape = ObjectShape.BOX;
    private int value;
    private EventManager eventManager;

    protected Coin(EventManager eventManager, Engine engine,
                   float x, float y, float width, float height,
                   ObjectProperties properties, ModelType type, int value) {
        super(engine, x, y, width, height, properties, isStatic, isSensor, type);
        this.eventManager = eventManager;
        this.value = value;
    }

    @Override
    public void handleBeginContact(ContactObject object) {
        if (object.getType().getObjectType() == ObjectType.PLAYER){
            dispose();
            eventManager.pushEvent(new DisposeSprite(this));
        }

        Gdx.app.log("Coin", "Contact with " + object.getType().getObjectType());
    }

    public int getValue() {
        return value;
    }

    @Override
    public ObjectShape GetShapeType() {
        return shape;
    }
}
