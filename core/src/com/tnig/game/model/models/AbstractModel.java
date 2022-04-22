package com.tnig.game.model.models;


import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.tnig.game.model.models.interfaces.ContactObject;
import com.tnig.game.model.models.interfaces.GameObject;
import com.tnig.game.model.models.interfaces.Model;
import com.tnig.game.model.models.interfaces.ModelType;
import com.tnig.game.model.physics_engine.bodies.BodyFactory;
import com.tnig.game.model.physics_engine.Engine;

public abstract class AbstractModel implements ContactObject, Model, GameObject {

    private final float width, height;
    private final boolean isStatic, isSensor;
    private final Body body;
    private final ModelType type;
    private boolean disposable = false;


    protected AbstractModel(Engine engine,
                            float x, float y, float width, float height,
                            boolean isStatic, boolean isSensor, ModelType type) {
        this.width = width;
        this.height = height;
        this.isStatic = isStatic;
        this.isSensor = isSensor;
        this.type = type;
        body = BodyFactory.getInstance().createBody(engine, x, y, this);
    }

    @Override
    public void dispose(){
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
    public ModelType getEnum() {
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

    protected float[] getLinearVelocity(){
        float x = body.getLinearVelocity().x;
        float y = body.getLinearVelocity().y;
        return new float[]{x, y};
    }

    protected void setLinearVelocityX(int xDir){
        body.setLinearVelocity(new Vector2(xDir, body.getLinearVelocity().y));
    }

    protected void applyForceToCenter(float forceX, float forceY){
        body.applyForceToCenter(forceX, forceY, true);
    }


}
