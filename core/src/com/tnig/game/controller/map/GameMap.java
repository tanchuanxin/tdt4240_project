package com.tnig.game.controller.map;

import static com.tnig.game.utilities.Constants.PPM;

import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.tnig.game.utilities.Constants;

public class GameMap {

    private final int tileWidth, tileHeight;
    private final int mapWidthInTiles, mapHeightInTiles;
    private final float mapWidthInUnits, mapHeightInUnits;
    private final int mapNumber;

    private final TiledMap tiledMap;

    public GameMap(int mapNumber) {
        this.mapNumber = mapNumber;
        String mapLocation = getMapLocation(mapNumber);

        tiledMap = new TmxMapLoader().load(mapLocation);

        // these map properties are needed for correct screen size settings
        MapProperties properties = tiledMap.getProperties();
        tileWidth         = properties.get("tilewidth", Integer.class);
        tileHeight        = properties.get("tileheight", Integer.class);
        mapWidthInTiles   = properties.get("width", Integer.class);
        mapHeightInTiles  = properties.get("height", Integer.class);
        mapWidthInUnits = mapWidthInTiles  * tileWidth / PPM;
        mapHeightInUnits = mapHeightInTiles * tileHeight / PPM;
    }

    public int getMapNumber() {
        return mapNumber;
    }

    public float getMapWidthInUnits(){
        return mapWidthInUnits;
    }

    public float getMapHeightInUnits(){
        return mapHeightInUnits;
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

    /**
     * Removes the graphic from the given tile
     * @param x the x-position given in units
     * @param y the y-position given in units
     */
    public void disposeGraphicOnTile(float x, float y){
        x *= PPM;
        y *= PPM;
        TiledMapTileLayer layer = (TiledMapTileLayer) tiledMap.getLayers().get(Constants.graphicsLayer);
        TiledMapTileLayer.Cell cell = layer.getCell((int) x / tileWidth, (int) y / tileHeight);
        if (cell != null) {
            cell.setTile(null);
        }
    }

    /**
     * used to locate the map file base on map number
     */
    private String getMapLocation(int mapNumber){
        return(Constants.MAP_ASSET_LOCATION + "map" + String.valueOf(mapNumber) + ".tmx");

    }
}
