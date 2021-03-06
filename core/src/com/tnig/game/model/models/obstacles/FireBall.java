package com.tnig.game.model.models.obstacles;

import com.badlogic.gdx.audio.Sound;
import com.tnig.game.controller.events.game_events.PlayerDead;
import com.tnig.game.controller.managers.EventManager;
import com.tnig.game.model.models.AbstractModel;
import com.tnig.game.model.models.ObjectProperties;
import com.tnig.game.model.models.enums.BodyType;
import com.tnig.game.model.models.enums.Direction;
import com.tnig.game.model.models.enums.ObjectShape;
import com.tnig.game.model.models.enums.ObjectType;
import com.tnig.game.model.models.interfaces.ContactObject;
import com.tnig.game.model.models.interfaces.ModelType;
import com.tnig.game.model.physics_engine.Engine;
import com.tnig.game.utilities.AssetLoader;

public class FireBall extends AbstractModel {
    private static final BodyType bodyType = BodyType.KINEMATIC;
    private static final boolean isSensor = true;
    private static final ObjectShape shape = ObjectShape.CIRCLE;
    private final float speed;
    protected Sound fireballSound;
    protected Sound deathSound;

    private Direction direction;
    private EventManager eventManager;

    protected FireBall(EventManager eventManager, Engine engine, AssetLoader assetLoader,
                       float x, float y, float width, float height,
                       ObjectProperties properties, ModelType type) {
        super(engine, x, y, width, height, properties, bodyType, isSensor, type);
        this.eventManager = eventManager;
        this.fireballSound = assetLoader.get(AssetLoader.SOUND_FIRE_BURST);
        this.deathSound = assetLoader.get(AssetLoader.SOUND_DIE);
        speed = properties.get("speed", float.class);
        String direction = properties.get("direction", String.class);
        setDirection(direction);
    }

    @Override
    public void handleBeginContact(ContactObject object) {
        if (object.getType().getObjectType() == ObjectType.PLAYER){
            fireballSound.play();
            deathSound.play();
            eventManager.pushEvent(new PlayerDead());
            object.dispose();
        }

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

    protected void flipDirection(){
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
