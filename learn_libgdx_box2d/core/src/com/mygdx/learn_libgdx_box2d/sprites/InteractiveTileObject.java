package com.mygdx.learn_libgdx_box2d.sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.learn_libgdx_box2d.LearnLibgdxBox2d;
import com.mygdx.learn_libgdx_box2d.screens.PlayScreen;

public abstract class InteractiveTileObject {
    protected World world;
    protected TiledMap map;
    protected TiledMapTile tile;
    protected Rectangle bounds;
    protected Body body;
    protected Fixture fixture;

    public InteractiveTileObject(PlayScreen playScreen, Rectangle bounds, short categoryBit) {
        this.world = playScreen.getWorld();
        this.map = playScreen.getMap();
        this.bounds = bounds;

        BodyDef bodyDef = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape polygonShape = new PolygonShape();

        // define body
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(LearnLibgdxBox2d.scale(bounds.getX() + bounds.getWidth()/2), LearnLibgdxBox2d.scale(bounds.getY() + bounds.getHeight()/2));

        // add body to world
        body = world.createBody(bodyDef);

        // define fixture
        polygonShape.setAsBox(LearnLibgdxBox2d.scale(bounds.getWidth()/2), LearnLibgdxBox2d.scale(bounds.getHeight()/2));
        fixtureDef.shape = polygonShape;
        fixtureDef.filter.categoryBits = categoryBit;
        fixture = body.createFixture(fixtureDef);
    }

    public abstract void onHit();


    public void setCategoryFilter(short filterBit) {
        Filter filter = new Filter();
        filter.categoryBits = filterBit;
        fixture.setFilterData(filter);
    }

    public TiledMapTileLayer.Cell getCell() {
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(1);

        // 16 is the tile size
        return layer.getCell((int)(LearnLibgdxBox2d.scale_reverse(body.getPosition().x))/16, (int)(LearnLibgdxBox2d.scale_reverse(body.getPosition().y))/16);
    }
}
