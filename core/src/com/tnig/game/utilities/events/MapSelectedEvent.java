package com.tnig.game.utilities.events;

import java.util.HashMap;

public class MapSelectedEvent extends Event {
    public MapSelectedEvent(int mapNum) {
        this.name = EventName.MAP_SELECTED;
        this.data = new HashMap<String, Object>();
        this.data.put("mapNum", mapNum);
    }
}
