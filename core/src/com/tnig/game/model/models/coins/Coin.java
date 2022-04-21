package com.tnig.game.model.models.coins;

import com.badlogic.gdx.Gdx;
import com.tnig.game.controller.events.game_events.DisposeObject;
import com.tnig.game.controller.managers.EventManager;
import com.tnig.game.model.models.AbstractModel;
import com.tnig.game.model.models.ContactObject;
import com.tnig.game.model.models.ModelType;
import com.tnig.game.model.models.ObjectShape;
import com.tnig.game.model.models.ObjectType;
import com.tnig.game.model.models.blocks.BlockType;
import com.tnig.game.model.physics_engine.Engine;

public class Coin extends AbstractModel {
    private static final boolean isStatic = true;
    private static final boolean isSensor = true;
    private static final ModelType type = CoinType.NORMAL_COIN;
    private static final ObjectShape shape = ObjectShape.BOX;
    private int value;
    private EventManager eventManager;

    protected Coin(EventManager eventManager, Engine engine, String layer,
                   float x, float y, float width, float height, int value) {
        super(engine, layer, x, y, width, height, isStatic, isSensor, type);
        this.eventManager = eventManager;
        this.value = value;
    }

    @Override
    public void handleBeginContact(ContactObject object) {
        if (object.getType().getObjectType() == ObjectType.PLAYER){
            dispose();
            eventManager.pushEvent(new DisposeObject(this));
        }

        Gdx.app.log("Coint", "Contact with " + object.getType().getObjectType());
    }

    public int getValue() {
        return value;
    }

    @Override
    public ObjectShape GetShape() {
        return shape;
    }
}
