package com.tnig.game.model.physics_engine.BodyBuilder;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.tnig.game.model.models.GameObject;
import com.tnig.game.model.physics_engine.Engine;

public class ObstacleBody extends BodyBuilder{

    // Singleton pattern
    private static final ObstacleBody INSTANCE = new ObstacleBody();

    public static ObstacleBody getInstance() {
        return INSTANCE;
    }
    private ObstacleBody() {
    }

    @Override
    protected Shape getShape(GameObject object) {
        //
        //TODO: Implement
        throw new IllegalArgumentException("Method not implemented");
    }

    @Override
    protected void addToBodyDef(BodyDef bodyDef) {
        // TODO: set bodydef
    }

    @Override
    protected void addToFixtureDef(FixtureDef fixtureDef) {
        // TODO: Set filtering

    }

    public Body createBody(Engine engine, GameObject object){
        return createBody(engine.getWorld(), object);
    }
}
