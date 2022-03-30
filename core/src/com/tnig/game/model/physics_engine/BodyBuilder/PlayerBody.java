package com.tnig.game.model.physics_engine.BodyBuilder;

import static com.tnig.game.utillities.Constants.PPM;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.tnig.game.model.models.Model;
import com.tnig.game.model.physics_engine.Engine;

public class PlayerBody extends BodyBuilder{

    // Singleton pattern
    private static final PlayerBody INSTANCE = new PlayerBody();

    public static PlayerBody getInstance() {
        return INSTANCE;
    }
    private PlayerBody() {
    }

    @Override
    protected Shape getShape(Model model) {
        final PolygonShape shape = new PolygonShape();
        shape.setAsBox(model.getWidth() / 2 / PPM, model.getHeight() / 2 / PPM);
        return shape;
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
