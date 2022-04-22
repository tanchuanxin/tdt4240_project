package com.tnig.game.controller.map;

import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.tnig.game.utilities.Constants;

public class GameMap {

    private final int mapWidthInPixels;
    private int mapHeightInPixels;
    private int tileWidth;
    private int tileHeight;

    private final TiledMap tiledMap;

    public GameMap(String mapLocation) {
        int mapWidthInTiles, mapHeightInTiles;

        tiledMap = new TmxMapLoader().load(mapLocation);

        MapProperties properties = tiledMap.getProperties();
        tileWidth         = properties.get("tilewidth", Integer.class);
        tileHeight        = properties.get("tileheight", Integer.class);
        mapWidthInTiles   = properties.get("width", Integer.class);
        mapHeightInTiles  = properties.get("height", Integer.class);
        mapWidthInPixels  = mapWidthInTiles  * tileWidth;
        mapHeightInPixels = mapHeightInTiles * tileHeight;
    }

    public int getMapHeight() {
        return mapHeightInPixels;
    }

    public int getMapWidth() {
        return mapWidthInPixels;
    }

    public int getTileWidth() {
        return tileWidth;
    }

    public int getTileHeight() {
        return tileHeight;
    }

    public TiledMap getTiledMap() {
        return tiledMap;
    }

    public void disposeTile(float x, float y){
        TiledMapTileLayer layer = (TiledMapTileLayer) tiledMap.getLayers().get(Constants.graphicsLayer);
        TiledMapTileLayer.Cell cell = layer.getCell((int) x / tileWidth, (int) y / tileHeight);
        cell.setTile(null);

    }
}
