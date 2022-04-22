package com.tnig.game.controller.events.screen_events;
import com.tnig.game.controller.events.Event;
import com.tnig.game.controller.events.EventName;

import java.util.HashMap;

public class InitGameEvent extends Event {
    public InitGameEvent(int numOfPlayers) {
        this.name = EventName.INIT_GAME;
        this.data = new HashMap<String, Object>();
        this.data.put("numOfPlayers", numOfPlayers);
    }
}
