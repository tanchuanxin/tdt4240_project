package com.tnig.game.model.models;

import com.badlogic.gdx.physics.box2d.Body;
import com.tnig.game.model.physics_engine.bodies.BodyFactory;
import com.tnig.game.model.physics_engine.Engine;

public abstract class AbstractModel implements ContactObject, Model, GameObject {

    private final float width, height;
    private final boolean isStatic, isSensor;
    private final Body body;
    private final ModelType type;
    private boolean disposable = false;


    protected AbstractModel(Engine engine, float x, float y, float width, float height,
                            boolean isStatic, boolean isSensor, ModelType type) {
        this.width = width;
        this.height = height;
        this.isStatic = isStatic;
        this.isSensor = isSensor;
        this.type = type;
        body = BodyFactory.getInstance().createBody(engine, x, y, this);
    }

    protected void dispose(){
        disposable = true;
    }

    public float getX(){
        return body.getPosition().x;
    }

    public float getY(){
        return body.getPosition().y;
    }

    public float getHeight() {
        return height;
    }

    public float getWidth() {
        return width;
    }

    @Override
    public ModelType getType() {
        return type;
    }

    public boolean isStatic() {
        return isStatic;
    }

    public boolean isSensor() {
        return isSensor;
    }

    public Body getBody() {
        return body;
    }

    /**
     * Checks if the instance should be disposed
     * @return True if disposable, false if not
     */
    public boolean isDisposable() {
        return disposable;
    }

    /**
     * Updates the model.
     * Override the method in concrete classes to implement
     * @param delta timestep
     */
    @Override
    public void update(float delta) {

    }
}
