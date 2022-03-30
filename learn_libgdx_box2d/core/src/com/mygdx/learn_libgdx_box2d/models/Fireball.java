package com.mygdx.learn_libgdx_box2d.models;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.Array;
import com.mygdx.learn_libgdx_box2d.JumpJellyJump;
import com.mygdx.learn_libgdx_box2d.controllers.PlayController;

public class Fireball extends Enemy {
    private int size;
    private float stateTime;
    private Animation<TextureRegion> fireballAnimation;
    private Array<TextureRegion> frames;

    public Fireball(PlayController playController, float x, float y) {
        super(playController, x, y);
        size = 8;

        frames = new Array<TextureRegion>();

        for (int i=0;i<4; i++) {
            frames.add(new TextureRegion(playController.getAtlas().findRegion("fireball16x16"), i*16, 0, 16, 16));
        }

        fireballAnimation = new Animation(0.4f, frames);
        stateTime = 0;
        setBounds(getX(), getY(), JumpJellyJump.scale(16), JumpJellyJump.scale(16));
    }

    public void update(float delta) {
        stateTime += delta;
        b2body.setLinearVelocity(velocity);
        setPosition(b2body.getPosition().x - JumpJellyJump.scale(size), b2body.getPosition().y - JumpJellyJump.scale(size));
        setRegion(fireballAnimation.getKeyFrame(stateTime, true));
    }

    @Override
    protected void defineEnemy() {
        size = 8;

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(getX() + JumpJellyJump.scale(size), getY() + JumpJellyJump.scale(size));

        bodyDef.type = BodyDef.BodyType.KinematicBody;
        b2body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(JumpJellyJump.scale(size));

        fixtureDef.filter.categoryBits = JumpJellyJump.ENEMY_BIT;
        fixtureDef.filter.maskBits = JumpJellyJump.JELLY_BIT;

        fixtureDef.friction = 0f;

        fixtureDef.shape = circleShape;
        b2body.createFixture(fixtureDef).setUserData(this);


        //Create the hitbox here:
        PolygonShape hitbox = new PolygonShape();
        Vector2[] vertices = new Vector2[4];
        vertices[0] = new Vector2(-size, size).scl(JumpJellyJump.scale(1));
        vertices[1] = new Vector2(size, size).scl(JumpJellyJump.scale(1));
        vertices[2] = new Vector2(-size, -size).scl(JumpJellyJump.scale(1));
        vertices[3] = new Vector2(size, -size).scl(JumpJellyJump.scale(1));
        hitbox.set(vertices);

        fixtureDef.shape = hitbox;
        fixtureDef.filter.categoryBits = JumpJellyJump.ENEMY_BIT;
        b2body.createFixture(fixtureDef).setUserData(this);
    }

}

