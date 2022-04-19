package com.tnig.game.controller.events.screen_events;

import com.tnig.game.controller.events.Event;
import com.tnig.game.controller.events.EventName;

import java.util.HashMap;

public class MapSelectedEvent extends Event {
    public MapSelectedEvent(int mapNum) {
        this.name = EventName.MAP_SELECTED;
        this.data = new HashMap<String, Object>();
        this.data.put("mapNum", mapNum);
    }
}
