package com.tnig.game.controller.map;

import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class GameMap {

    private int mapWidthInPixels, mapHeightInPixels;

    private final TiledMap tiledMap;

    public GameMap(String mapLocation) {
        int tileWidth, tileHeight,
                mapWidthInTiles, mapHeightInTiles;

        tiledMap = new TmxMapLoader().load(mapLocation);

        MapProperties properties = tiledMap.getProperties();
        tileWidth         = properties.get("tilewidth", Integer.class);
        tileHeight        = properties.get("tileheight", Integer.class);
        mapWidthInTiles   = properties.get("width", Integer.class);
        mapHeightInTiles  = properties.get("height", Integer.class);
        mapWidthInPixels  = mapWidthInTiles  * tileWidth;
        mapHeightInPixels = mapHeightInTiles * tileHeight;
    }

    public int getMapHeightInPixels() {
        return mapHeightInPixels;
    }

    public int getMapWidthInPixels() {
        return mapWidthInPixels;
    }

    public TiledMap getTiledMap() {
        return tiledMap;
    }
}
