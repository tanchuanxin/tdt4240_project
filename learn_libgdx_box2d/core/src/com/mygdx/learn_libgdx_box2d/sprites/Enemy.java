package com.mygdx.learn_libgdx_box2d.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.learn_libgdx_box2d.LearnLibgdxBox2d;
import com.mygdx.learn_libgdx_box2d.screens.PlayScreen;

public abstract class Enemy extends Sprite {
    protected World world;
    protected PlayScreen playScreen;
    public Body b2body;
    public Vector2 velocity;

    public Enemy(PlayScreen playScreen, float x, float y) {
        this.world = playScreen.getWorld();
        this.playScreen = playScreen;
        setPosition(x, y);
        defineEnemy();
        velocity = new Vector2(-1, 0);
        b2body.setActive(false);
    }

    protected abstract void defineEnemy();

    public abstract void update(float delta);
}
