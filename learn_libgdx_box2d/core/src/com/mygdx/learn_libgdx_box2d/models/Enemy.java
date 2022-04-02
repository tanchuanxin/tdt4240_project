package com.mygdx.learn_libgdx_box2d.models;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.learn_libgdx_box2d.controllers.PlayController;

public abstract class Enemy extends Sprite {
    protected World world;
    protected PlayController playController;
    public Body b2body;
    public Vector2 velocity;

    public Enemy(PlayController playController, float x, float y) {
        this.world = playController.getWorld();
        this.playController = playController;
        setPosition(x, y);
        defineEnemy();
        velocity = new Vector2(-1, 0);
        b2body.setActive(false);
    }

    protected abstract void defineEnemy();

    public abstract void update(float delta);
}
