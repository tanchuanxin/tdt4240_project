package com.tnig.game.model.models.players;

import com.tnig.game.model.models.AbstractModel;
import com.tnig.game.model.models.ContactObject;
import com.tnig.game.model.models.ModelType;
import com.tnig.game.model.models.ObjectType;
import com.tnig.game.model.physics_engine.Engine;

public class NormalPlayer extends AbstractModel {

    private static final boolean isStatic = false;
    private static final boolean isSensor = false;
    private static final ModelType type = PlayerType.NORMALPLAYER;


    public NormalPlayer(Engine engine, float x, float y, float width, float height) {
        super(engine, x, y, width, height, isStatic, isSensor, type);
    }

    @Override
    public void handleBeginContact(ContactObject object) {

    }

    public ModelType getPlayerType() {
        return type;
    }
}
