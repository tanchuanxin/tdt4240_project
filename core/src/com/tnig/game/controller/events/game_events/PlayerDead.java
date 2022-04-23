package com.tnig.game.controller.events.game_events;

import com.tnig.game.controller.events.Event;
import com.tnig.game.controller.events.EventName;

import java.util.HashMap;

public class PlayerDead extends Event {
    public PlayerDead(Object player) {
        name = EventName.PLAYER_DEAD;
        data = new HashMap<String, Object>();
        data.put("player", player);
    }
}
