package com.tnig.game.model.models.obstacles;

import com.tnig.game.model.models.AbstractModel;
import com.tnig.game.model.models.ContactObject;
import com.tnig.game.model.models.ObjectType;
import com.tnig.game.model.physics_engine.Engine;

public class Static_Triangle extends AbstractModel implements Obstacle {

    private static final boolean isStatic = true;
    private static final boolean isSensor = false;
    private static final ObjectType type = ObjectType.OBSTACLE;
    private static final ObstacleType obstacleType = ObstacleType.STATIC_TRIANGLE;

    protected Static_Triangle(Engine engine, float x, float y, float width, float height) {
        super(engine, x, y, width, height, isStatic, isSensor, type);
    }

    @Override
    public void handleBeginContact(ContactObject object) {

    }

    @Override
    public ObstacleType getObstacleType() {
        return obstacleType;
    }
}
