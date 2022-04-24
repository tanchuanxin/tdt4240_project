package com.tnig.game.model.models.obstacles;

import com.badlogic.gdx.Gdx;
import com.tnig.game.model.models.AbstractModel;
import com.tnig.game.model.models.ObjectProperties;
import com.tnig.game.model.models.enums.BodyType;
import com.tnig.game.model.models.enums.Direction;
import com.tnig.game.model.models.enums.ObjectShape;
import com.tnig.game.model.models.interfaces.ContactObject;
import com.tnig.game.model.models.interfaces.ModelType;
import com.tnig.game.model.physics_engine.Engine;

public class FireBall extends AbstractModel {

    private static final BodyType bodyType = BodyType.KINEMATIC;
    private static final boolean isSensor = true;
    private static final ObjectShape shape = ObjectShape.CIRCLE;
    private final float speed;

    private Direction direction;


    protected FireBall(Engine engine,
                       float x, float y, float width, float height,
                       ObjectProperties properties, ModelType type) {
        super(engine, x, y, width, height, properties, bodyType, isSensor, type);
        speed = properties.get("speed", float.class);
        String direction = properties.get("direction", String.class);
        setDirection(direction);
    }

    @Override
    public void handleBeginContact(ContactObject object) {
        flipDirection();

    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public ObjectShape GetShapeType() {
        return shape;
    }

    private void setDirection(String direction){
        switch (direction){
            case "left":
                setLinearVelocityX(-speed);
                this.direction = Direction.LEFT;
                break;
            case "right":
                setLinearVelocityX(speed);
                this.direction = Direction.RIGHT;
                break;
            case "up":
                setLinearVelocityY(speed);
                this.direction = Direction.UP;
                break;
            case "down":
                setLinearVelocityY(-speed);
                this.direction = Direction.DOWN;
                break;

        }
    }

    private void flipDirection(){
        switch (direction){
            case LEFT:
                setLinearVelocityX(speed);
                direction = Direction.RIGHT;
                break;
            case RIGHT:
                setLinearVelocityX(-speed);
                direction = Direction.LEFT;
                break;
            case UP:
                setLinearVelocityY(-speed);
                direction = Direction.DOWN;
                break;
            case DOWN:
                setLinearVelocityY(speed);
                direction = Direction.UP;
                break;

        }

    }

}
