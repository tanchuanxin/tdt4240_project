package com.mygdx.learn_libgdx_box2d.Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.learn_libgdx_box2d.LearnLibgdxBox2d;
import com.mygdx.learn_libgdx_box2d.screens.PlayScreen;
import com.mygdx.learn_libgdx_box2d.sprites.Block;
import com.mygdx.learn_libgdx_box2d.sprites.Coin;
import com.mygdx.learn_libgdx_box2d.sprites.Fireball;
import com.mygdx.learn_libgdx_box2d.sprites.Flag;

public class B2WorldCreator {
    private Array<Fireball> fireballs;

    public B2WorldCreator(PlayScreen playScreen) {
        World world = playScreen.getWorld();
        TiledMap map = playScreen.getMap();

        BodyDef bodyDef = new BodyDef();
        PolygonShape polygonShape = new PolygonShape();
        FixtureDef fixtureDef = new FixtureDef();
        Body body;

        // create block layer - layer 2
        for (MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
            // grab rectangles corresponding to object
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            // create the tile
            new Block(playScreen, rect);
        }

        // create coin layer - layer 3
        for (MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {
            // grab rectangles corresponding to object
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            // create the tile
            new Coin(playScreen, rect);
        }

        // create block layer - layer 4
        for (MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)) {
            // grab rectangles corresponding to object
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            // create the tile
            new Flag(playScreen, rect);
        }

        // create fireball layer - layer 5
        fireballs = new Array<Fireball>();
        for (MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)) {
            // grab rectangles corresponding to object
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            // create the tile
            fireballs.add(new Fireball(playScreen, LearnLibgdxBox2d.scale(rect.getX()), LearnLibgdxBox2d.scale(rect.getY())));
        }
    }

    public Array<Fireball> getFireballs() {
        return fireballs;
    }

}
