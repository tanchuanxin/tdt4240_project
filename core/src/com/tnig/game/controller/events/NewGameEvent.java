package com.tnig.game.utilities.events;

import java.util.HashMap;

public class NewGameEvent extends Event {
    public NewGameEvent(int numOfPlayers) {
        this.name = EventName.NEW_GAME;
        this.data = new HashMap<String, Object>();
        this.data.put("numOfPlayers", numOfPlayers);
    }
}
