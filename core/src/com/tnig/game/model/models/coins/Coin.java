package com.tnig.game.model.models.coins;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.tnig.game.controller.events.game_events.DisposeSprite;
import com.tnig.game.controller.managers.EventManager;
import com.tnig.game.model.models.AbstractModel;
import com.tnig.game.model.models.ObjectProperties;
import com.tnig.game.model.models.enums.BodyType;
import com.tnig.game.model.models.interfaces.ContactObject;
import com.tnig.game.model.models.interfaces.ModelType;
import com.tnig.game.model.models.enums.ObjectShape;
import com.tnig.game.model.models.enums.ObjectType;
import com.tnig.game.model.physics_engine.Engine;
import com.tnig.game.utilities.AssetLoader;

public class Coin extends AbstractModel {
    private static final BodyType bodyType = BodyType.STATIC;
    private static final boolean isSensor = true;
    private static final ObjectShape shape = ObjectShape.BOX;
    private int value;
    private EventManager eventManager;
    private Sound coinSound;

    protected Coin(EventManager eventManager, Engine engine, AssetLoader assetLoader,
                   float x, float y, float width, float height,
                   ObjectProperties properties, ModelType type, int value) {
        super(engine, x, y, width, height, properties, bodyType, isSensor, type);
        this.eventManager = eventManager;
        this.coinSound = assetLoader.get(AssetLoader.SOUND_COIN);
        this.value = value;
    }

    @Override
    public void handleBeginContact(ContactObject object) {
        if (object.getType().getObjectType() == ObjectType.PLAYER){
            coinSound.play();
            dispose();
            eventManager.pushEvent(new DisposeSprite(this));
        }

        Gdx.app.log("Coin", "Contact with " + object.getType().getObjectType());
    }

    /**
     * coin can be initialized with different values
     */
    public int getValue() {
        return value;
    }

    @Override
    public ObjectShape GetShapeType() {
        return shape;
    }
}
