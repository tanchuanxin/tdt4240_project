package com.tnig.game.model.physics_engine;



import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.tnig.game.model.models.ObjectProperties;
import com.tnig.game.model.models.interfaces.GameObject;
import com.tnig.game.model.physics_engine.Engine;

/**
 * A template class for creating different types of bodies in the box2D world
 */
public class BodyBuilder {

    // Template methods
     /**
     * Makes the necessary changes to the body definition to that specific body
     * @param bodyDef The body definition
     */
    //protected abstract void addToBodyDef(BodyDef bodyDef);

    /**
     * Makes the necessary changes to the fixture definition to that specific body
     * @param fixtureDef The fixture definition
     */
    //protected abstract void addToFixtureDef(FixtureDef fixtureDef);

    /**
     * Template method for creating a Box2D body
     * @param engine The physics engine which contains the world
     * @param x The x-coordinate where the body should appear
     * @param y The y-coordinate where the body should appear
     * @param object The object which will contain the Box2D body
     * @return a Box2D body
     */
    public static Body createBody(Engine engine, float x, float y, GameObject object) {
        World world = engine.getWorld();
        Shape shape = getShape(object);
        boolean isStatic = object.isStatic();
        ObjectProperties properties = object.getProperties();

        // Defines a Box2D body
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = isStatic ? BodyDef.BodyType.StaticBody : BodyDef.BodyType.DynamicBody;
        bodyDef.fixedRotation = true;

        // World units = meters, from world to screen -> Divide by Pixel Per Meter
        bodyDef.position.set(x, y);
        if (properties.get("rotation", Float.class) != null) {
            float rotation = properties.get("rotation", Float.class);
            bodyDef.angle = (float) Math.toRadians(rotation);

        }

        //addToBodyDef(bodyDef);
        //Puts the body in the Box2D world
        Body body = world.createBody(bodyDef);

        // A body is composed of fixtures => defines a fixture to put in the body
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.isSensor = object.isSensor();

        fixtureDef.density = 20;
        fixtureDef.friction = 0f;
        fixtureDef.restitution = 0f;
        //addToFixtureDef(fixtureDef);

        // Sets the model as userdata for the contactlistener
        body.createFixture(fixtureDef).setUserData(object);
        shape.dispose();

        return body;
    }


    /**
     * Gets the shape of the specific body
     * @param object The object to create the shape from
     * @return A Box2D shape
     */
    private static Shape getShape(GameObject object){
        Shape shape;
        switch (object.GetShapeType()){
            case BOX:
                shape = new PolygonShape();
                ((PolygonShape) shape).setAsBox(object.getWidth() / 2 , object.getHeight() / 2);
                break;
            case CIRCLE:
                shape = new CircleShape();
                shape.setRadius(object.getWidth() / 2 );
                break;
            case TRIANGLE:
                shape = new PolygonShape();
                Vector2[] vertices = new Vector2[3];
                    vertices[0] = new Vector2(-object.getWidth() / 2, -object.getHeight() / 2);
                    vertices[1] = new Vector2(object.getWidth() / 2, -object.getHeight() / 2);
                    vertices[2] = new Vector2(0, object.getHeight() / 2);
                ((PolygonShape) shape).set(vertices);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + object.GetShapeType());
        }
        return shape;
    }


}
