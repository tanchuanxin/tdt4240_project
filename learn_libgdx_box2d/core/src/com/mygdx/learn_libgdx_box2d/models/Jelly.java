package com.mygdx.learn_libgdx_box2d.models;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.learn_libgdx_box2d.JumpJellyJump;
import com.mygdx.learn_libgdx_box2d.controllers.PlayController;



public class Jelly extends Sprite {
    private int size;
    private Rectangle bounds;
    public enum State {JUMPING, STANDING, DEAD, WIN};
    public State currentState;
    public State previousState;
    public World world;
    public Body b2body;
    private TextureRegion jellyStanding;
    private float stateTimer;
    private boolean runningRight;
    private Animation<TextureRegion> jellyJump;
    private Animation<TextureRegion> jellyWin;
    private boolean jellyIsDead;
    private boolean jellyIsWin;


    public Jelly(PlayController playController, Rectangle bounds) {
        this.world = playController.getWorld();
        this.bounds = bounds;
        currentState = State.STANDING;
        previousState = State.STANDING;
        size = 8;
        stateTimer = 0;
        runningRight = true;
        jellyIsDead = false;
        jellyIsWin = false;


        // animations
        Array<TextureRegion> frames = new Array<TextureRegion>();

        // jump animation
        for (int i=0; i<9; i++) {
            frames.add(new TextureRegion(playController.getAtlas().findRegion("jellyJump28x28"), i*28, 0, 28, 28));
        }
        jellyJump = new Animation<TextureRegion>(0.1f, frames);
        frames.clear();

        // win animation
        for (int i=0; i<9; i++) {
            frames.add(new TextureRegion(playController.getAtlas().findRegion("jellyWin28x28"), i*28, 0, 28, 28));
        }
        jellyWin = new Animation<TextureRegion>(0.1f, frames);
        frames.clear();

        // standing frame
        jellyStanding = new TextureRegion(playController.getAtlas().findRegion("jellyJump28x28"), 0,0,28,28);
        setBounds(0, 0, JumpJellyJump.scale(28), JumpJellyJump.scale(28));

        defineJelly();
        setRegion(jellyStanding);
    }

    public void update(float delta) {
        setPosition(b2body.getPosition().x - getWidth()/2, b2body.getPosition().y - getHeight()/3);
        setRegion(getFrame(delta));

        if (jellyIsDead || jellyIsWin ) {
            b2body.setLinearVelocity(0,b2body.getLinearVelocity().y);
        }

        if (b2body.getPosition().y <= 0) {
            die();
        }


    }

    public TextureRegion getFrame(float delta) {
        currentState = getCurrentState();

        TextureRegion region;
        switch (currentState) {
            case JUMPING:
            case DEAD:
                region = jellyJump.getKeyFrame(stateTimer);
                break;
            case WIN:
                region = jellyWin.getKeyFrame(stateTimer);
                break;
            case STANDING:
            default:
                region = jellyStanding;
        }

        //if jelly is running left and the texture isnt facing left... flip it.
        if((b2body.getLinearVelocity().x < 0 || !runningRight) && !region.isFlipX()){
            region.flip(true, false);
            runningRight = false;
        }

        //if jelly is running right and the texture isnt facing right... flip it.
        else if((b2body.getLinearVelocity().x > 0 || runningRight) && region.isFlipX()){
            region.flip(true, false);
            runningRight = true;
        }

        stateTimer = currentState == previousState ? stateTimer + delta : 0;
        previousState = currentState;

        return region;
    }

    public State getCurrentState() {
        if (jellyIsDead) {
            return State.DEAD;
        } else if (jellyIsWin) {
            return State.WIN;
        } else if (b2body.getLinearVelocity().y > 0) {
            return State.JUMPING;
        } else {
            return State.STANDING;
        }
    }


    public void defineJelly() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(JumpJellyJump.scale(bounds.getX() + bounds.getWidth()/2), JumpJellyJump.scale(bounds.getY() + bounds.getHeight()/2));
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.filter.categoryBits = JumpJellyJump.JELLY_BIT;
        fixtureDef.filter.maskBits = JumpJellyJump.FLAG_BIT | JumpJellyJump.BLOCK_BIT | JumpJellyJump.COIN_BIT | JumpJellyJump.ENEMY_BIT;

        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(JumpJellyJump.scale(size));

        fixtureDef.shape = circleShape;
        fixtureDef.friction = 0f;
        b2body.createFixture(fixtureDef).setUserData(this);

        // create the hitbox
        PolygonShape hitbox = new PolygonShape();
        Vector2[] vertices = new Vector2[4];
        vertices[0] = new Vector2(-size, size).scl(JumpJellyJump.scale(1));
        vertices[1] = new Vector2(size, size).scl(JumpJellyJump.scale(1));
        vertices[2] = new Vector2(-size, -size).scl(JumpJellyJump.scale(1));
        vertices[3] = new Vector2(size, -size).scl(JumpJellyJump.scale(1));
        hitbox.set(vertices);

        fixtureDef.shape = hitbox;
        fixtureDef.filter.categoryBits = JumpJellyJump.JELLY_BIT;
        b2body.createFixture(fixtureDef).setUserData(this);
    }

    public void die() {
        if (!isDead()) {
            JumpJellyJump.assetManager.get("audio/music/adventure.mp3", Music.class).stop();
            Sound sound = JumpJellyJump.assetManager.get("audio/sounds/die.mp3", Sound.class);
            sound.setVolume(sound.play(), JumpJellyJump.getVolumeLevel());

            jellyIsDead = true;
            Filter filter = new Filter();
            filter.maskBits = JumpJellyJump.NOTHING_BIT;

            for (Fixture fixture : b2body.getFixtureList()) {
                fixture.setFilterData(filter);
            }

            b2body.setLinearVelocity(new Vector2(0,0));
            b2body.applyLinearImpulse(new Vector2(0, 4f), b2body.getWorldCenter(), true);
        }
    }

    public void win() {
        if (!isDead() && !isWin()) {
            JumpJellyJump.assetManager.get("audio/music/adventure.mp3", Music.class).stop();
            Sound sound = JumpJellyJump.assetManager.get("audio/sounds/win.mp3", Sound.class);
            sound.setVolume(sound.play(), JumpJellyJump.getVolumeLevel());

            jellyIsWin = true;

            b2body.setLinearVelocity(new Vector2(0,0));
            b2body.applyLinearImpulse(new Vector2(0, 4f), b2body.getWorldCenter(), true);
        }
    }

    public boolean isDead() {
        return jellyIsDead;
    }

    public boolean isWin() {
        return jellyIsWin;
    }

    public float getStateTimer() {
        return stateTimer;
    }
}
