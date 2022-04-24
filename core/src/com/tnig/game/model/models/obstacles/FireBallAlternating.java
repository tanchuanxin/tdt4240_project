package com.tnig.game.model.models.obstacles;

import static com.tnig.game.utilities.Constants.PPM;

import com.tnig.game.controller.managers.EventManager;
import com.tnig.game.model.models.ObjectProperties;
import com.tnig.game.model.models.interfaces.ModelType;
import com.tnig.game.model.physics_engine.Engine;

public class FireBallAlternating extends FireBall{

    private final float distanceToTravel;
    float startX, startY;

    protected FireBallAlternating(EventManager eventManager, Engine engine,
                                  float x, float y, float width, float height,
                                  ObjectProperties properties, ModelType type) {
        super(eventManager, engine, x, y, width, height, properties, type);
        startX = x;
        startY = y;
        int tileBounds = properties.get("tileBounds", int.class);
        float tileWidth = properties.get("width", float.class);
        distanceToTravel = tileBounds * tileWidth / PPM;
    }

    @Override
    public void update(float delta) {
        float xSquared = (float) Math.pow(getX()-startX, 2);
        float ySquared = (float) Math.pow(getY()-startY, 2);
        float distanceTravelled = (float) Math.sqrt(xSquared + ySquared);
        if (distanceTravelled > distanceToTravel){
            flipDirection();
            startX = getX();
            startY = getY();
        }
    }
}
