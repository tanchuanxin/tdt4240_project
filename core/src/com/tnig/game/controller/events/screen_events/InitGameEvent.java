package com.tnig.game.controller.events.screen_events;

import com.tnig.game.controller.events.Event;
import com.tnig.game.controller.events.EventName;

public class InitGameEvent extends Event {
    public InitGameEvent(int numOfPlayers) {
        this.name = EventName.INIT_GAME;
        this.data.put("numOfPlayers", numOfPlayers);
    }
}
