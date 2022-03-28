package com.mygdx.learn_libgdx_box2d.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
import com.mygdx.learn_libgdx_box2d.LearnLibgdxBox2d;
import com.mygdx.learn_libgdx_box2d.screens.PlayScreen;



public class Jelly extends Sprite {
    private int size;
    public enum State {JUMPING, STANDING, DEAD};
    public State currentState;
    public State previousState;
    public World world;
    public Body b2body;
    private TextureRegion jellyStanding;
    private float stateTimer;
    private Animation<TextureRegion> jellyJump;
    private boolean jellyIsDead;


    public Jelly(PlayScreen playScreen) {
        super(playScreen.getAtlas().findRegion("jelly_28x28"));
        this.world = playScreen.getWorld();
        currentState = State.STANDING;
        previousState = State.STANDING;
        size = 8;
        stateTimer = 0;
        jellyIsDead = false;

        Array<TextureRegion> frames = new Array<TextureRegion>();
        for (int i=1; i<9; i++) {
            frames.add(new TextureRegion(getTexture(), i*28, 0, 28, 28));
        }

        jellyJump = new Animation<TextureRegion>(0.1f, frames);
        frames.clear();


        defineJelly();
        jellyStanding = new TextureRegion(getTexture(), 0,0,28,28);
        setBounds(0, 0, LearnLibgdxBox2d.scale(28), LearnLibgdxBox2d.scale(28));
        setRegion(jellyStanding);
    }

    public void update(float delta) {
        setPosition(b2body.getPosition().x - getWidth()/2, b2body.getPosition().y - getHeight()/3);
        setRegion(getFrame(delta));

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
            case STANDING:
            default:
                region = jellyStanding;
        }

        stateTimer = currentState == previousState ? stateTimer + delta : 0;
        previousState = currentState;

        return region;
    }

    public State getCurrentState() {
        if (jellyIsDead) {
            return State.DEAD;
        } else if (b2body.getLinearVelocity().y > 0) {
            return State.JUMPING;
        } else {
            return State.STANDING;
        }
    }


    public void defineJelly() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(LearnLibgdxBox2d.scale(256), LearnLibgdxBox2d.scale(128));
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.filter.categoryBits = LearnLibgdxBox2d.JELLY_BIT;
        fixtureDef.filter.maskBits = LearnLibgdxBox2d.FLAG_BIT | LearnLibgdxBox2d.BLOCK_BIT | LearnLibgdxBox2d.COIN_BIT | LearnLibgdxBox2d.ENEMY_BIT;

        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(LearnLibgdxBox2d.scale(size));

        fixtureDef.shape = circleShape;
        fixtureDef.friction = 0f;
        b2body.createFixture(fixtureDef).setUserData(this);

        // create the hitbox
        PolygonShape hitbox = new PolygonShape();
        Vector2[] vertices = new Vector2[4];
        vertices[0] = new Vector2(-size, size).scl(LearnLibgdxBox2d.scale(1));
        vertices[1] = new Vector2(size, size).scl(LearnLibgdxBox2d.scale(1));
        vertices[2] = new Vector2(-size, -size).scl(LearnLibgdxBox2d.scale(1));
        vertices[3] = new Vector2(size, -size).scl(LearnLibgdxBox2d.scale(1));
        hitbox.set(vertices);

        fixtureDef.shape = hitbox;
        fixtureDef.filter.categoryBits = LearnLibgdxBox2d.JELLY_BIT;
        b2body.createFixture(fixtureDef).setUserData(this);
    }

    public void die() {
        if (!isDead()) {
            LearnLibgdxBox2d.assetManager.get("audio/music/adventure.mp3", Music.class).stop();
            Sound sound = LearnLibgdxBox2d.assetManager.get("audio/sounds/die.mp3", Sound.class);
            sound.setVolume(sound.play(), 0.1f);

            jellyIsDead = true;
            Filter filter = new Filter();
            filter.maskBits = LearnLibgdxBox2d.NOTHING_BIT;

            for (Fixture fixture : b2body.getFixtureList()) {
                fixture.setFilterData(filter);
            }

            b2body.applyLinearImpulse(new Vector2(0, 5f), b2body.getWorldCenter(), true);
        }
    }

    public boolean isDead() {
        return jellyIsDead;
    }

    public float getStateTimer() {
        return stateTimer;
    }
}
