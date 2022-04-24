package com.tnig.game.controller.map;

import static com.tnig.game.utilities.Constants.PPM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.tnig.game.utilities.Constants;

public class GameMap {

    private final int mapWidthInPixels, mapHeightInPixels;
    private final int tileWidth, tileHeight;
    private final int mapWidthInTiles, mapHeightInTiles;
    private final float mapWidthInUnits, mapHeightInUnits;
    private final int mapNumber;

    private final TiledMap tiledMap;

    public GameMap(int mapNUmber) {
        this.mapNumber = mapNUmber;
        String mapLocation = getMapLocation(mapNUmber);

        tiledMap = new TmxMapLoader().load(mapLocation);

        MapProperties properties = tiledMap.getProperties();
        tileWidth         = properties.get("tilewidth", Integer.class);
        tileHeight        = properties.get("tileheight", Integer.class);
        mapWidthInTiles   = properties.get("width", Integer.class);
        mapHeightInTiles  = properties.get("height", Integer.class);
        mapWidthInPixels  = mapWidthInTiles  * tileWidth;
        mapHeightInPixels = mapHeightInTiles * tileHeight;
        mapWidthInUnits = mapWidthInPixels / PPM;
        mapHeightInUnits = mapHeightInPixels / PPM;

        Gdx.app.log("tileWidth: ", String.valueOf(tileWidth));
        Gdx.app.log("mapWidthInTiles: ", String.valueOf(mapWidthInTiles));
        Gdx.app.log("mapWidthInPixels: ", String.valueOf(mapWidthInPixels));
        Gdx.app.log("tileHeight: ", String.valueOf(tileHeight));
        Gdx.app.log("mapHeightInTiles: ", String.valueOf(mapHeightInTiles));
        Gdx.app.log("mapHeightInPixels: ", String.valueOf(mapHeightInPixels));
        Gdx.app.log("ppm: ", String.valueOf(Constants.PPM));
    }

    public int getMapNumber() {
        return mapNumber;
    }

    public int getMapHeightInPixels() {
        return mapHeightInPixels;
    }

    public int getMapWidthInPixels() {
        return mapWidthInPixels;
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


    private String getMapLocation(int mapNumber){
        return(Constants.MAP_ASSET_LOCATION + "map" + String.valueOf(mapNumber) + ".tmx");

    }
}
