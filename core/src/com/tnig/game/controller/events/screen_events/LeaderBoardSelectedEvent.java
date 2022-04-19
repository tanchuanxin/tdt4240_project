package com.tnig.game.controller.events.screen_events;

import com.tnig.game.controller.events.Event;
import com.tnig.game.controller.events.EventName;

import java.util.HashMap;

public class LeaderBoardSelectedEvent extends Event {

    public LeaderBoardSelectedEvent(int mapNum) {
        this.name = EventName.VIEW_LEADERBOARDS;
        this.data = new HashMap<>();
        this.data.put("mapNum", mapNum);
    }
}
