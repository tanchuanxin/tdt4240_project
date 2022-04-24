package com.tnig.game.controller.events.screen_events;

import com.tnig.game.controller.events.Event;
import com.tnig.game.controller.events.EventName;

import java.util.HashMap;

public class LeaderboardSelectedEvent extends Event {

    public LeaderboardSelectedEvent(int mapNum) {
        this.name = EventName.VIEW_LEADERBOARDS;
        this.data.put("mapNum", mapNum);
    }
}
