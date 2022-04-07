package com.tnig.game.utilities.events;

import java.util.HashMap;

public class LeaderBoardSelectedEvent extends Event{

    public LeaderBoardSelectedEvent(int mapNum) {
        this.name = EventName.VIEW_LEADERBOARDS;
        this.data = new HashMap<>();
        this.data.put("mapNum", mapNum);
    }
}
