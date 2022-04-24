package com.tnig.game.model.models;


import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.tnig.game.model.models.enums.BodyType;
import com.tnig.game.model.models.interfaces.ContactObject;
import com.tnig.game.model.models.interfaces.GameObject;
import com.tnig.game.model.models.interfaces.Model;
import com.tnig.game.model.models.interfaces.ModelType;
import com.tnig.game.model.physics_engine.BodyBuilder;
import com.tnig.game.model.physics_engine.Engine;

public abstract class AbstractModel implements ContactObject, Model, GameObject {

    private final float width, height;
    private final boolean isSensor;
    private final Body body;
    private final ModelType type;
    private final BodyType bodyType;
    protected final ObjectProperties properties;
    private boolean disposable = false;


    protected AbstractModel(Engine engine,
                            float x, float y, float width, float height, ObjectProperties properties,
                            BodyType bodyType, boolean isSensor, ModelType type) {
        this.width = width;
        this.height = height;
        this.properties = properties;
        this.bodyType = bodyType;
        this.isSensor = isSensor;
        this.type = type;
        body = BodyBuilder.createBody(engine, x, y, this);
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
    public BodyType getBodyType() {
        return bodyType;
    }

    @Override
    public ObjectProperties getProperties() {
        return properties;
    }

    @Override
    public ModelType getType() {
        return type;
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

    public float[] getLinearVelocity(){
        Vector2 v = body.getLinearVelocity();
        return new float[]{v.x, v.y};
    }

    protected void setLinearVelocityX(float velocityX){
        body.setLinearVelocity(new Vector2(velocityX, body.getLinearVelocity().y));
    }

    protected void setLinearVelocityY(float velocityY){
        body.setLinearVelocity(new Vector2(body.getLinearVelocity().x, velocityY));
    }


    protected void applyImpulseToCenter(float forceX, float forceY){
        body.applyLinearImpulse(new Vector2(forceX, forceY), body.getWorldCenter(), true);
    }
}
