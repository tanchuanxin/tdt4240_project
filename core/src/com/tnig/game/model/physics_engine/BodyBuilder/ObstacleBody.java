package com.tnig.game.model.physics_engine.BodyBuilder;

import static com.tnig.game.utillities.Constants.PPM;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.tnig.game.model.models.Model;
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
    protected Shape getShape(Model model) {
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

    public void createBody(Engine engine, Model model){
        createBody(engine.getWorld(), model);
    }
}
