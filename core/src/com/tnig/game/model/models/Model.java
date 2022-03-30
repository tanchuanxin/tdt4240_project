package com.tnig.game.model.models;

import com.badlogic.gdx.physics.box2d.Body;
import com.tnig.game.model.physics_engine.BodyBuilder.BodyFactory;
import com.tnig.game.model.physics_engine.Engine;

public abstract class Model implements ContactObject {

    private final float width, height;
    private final boolean isStatic, isSensor;
    private Body body;
    private final ModelType type;


    protected Model(Engine engine, float width, float height, boolean isStatic, boolean isSensor, ModelType type) {
        this.width = width;
        this.height = height;
        this.isStatic = isStatic;
        this.isSensor = isSensor;
        this.type = type;
        BodyFactory.getInstance().createBody(engine, this);
    }

    public float getX(){
        return body.getPosition().x;
    }

    public float getY(){
        return body.getPosition().y;
    }

    public boolean isStatic() {
        return isStatic;
    }

    public boolean isSensor() {
        return isSensor;
    }

    public float getHeight() {
        return height;
    }

    public float getWidth() {
        return width;
    }

    public ModelType getType() {
        return type;
    }

    public void setBody(final Body body){
        this.body = body;
    }
}
