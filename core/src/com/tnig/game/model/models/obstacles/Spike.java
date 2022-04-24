package com.tnig.game.model.models.obstacles;

import com.badlogic.gdx.audio.Sound;
import com.tnig.game.controller.events.game_events.PlayerDead;
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

public class Spike extends AbstractModel {

    private static final BodyType bodyType = BodyType.STATIC;
    private static final boolean isSensor = false;
    private static final ObjectShape shape = ObjectShape.TRIANGLE;
    private Sound deathSound;

    private final EventManager eventManager;

    protected Spike(EventManager eventManager, Engine engine, AssetLoader assetLoader,
                    float x, float y, float width, float height,
                    ObjectProperties properties, ModelType type) {
        super(engine, x, y, width, height, properties, bodyType, isSensor, type);
        this.eventManager = eventManager;
        this.deathSound = assetLoader.get(AssetLoader.SOUND_DIE);
    }

    @Override
    public void handleBeginContact(ContactObject object) {
        if (object.getType().getObjectType() == ObjectType.PLAYER){
            deathSound.play();
            eventManager.pushEvent(new PlayerDead());
            object.dispose();
        }
    }


    @Override
    public ObjectShape GetShapeType() {
        return shape;
    }


}
