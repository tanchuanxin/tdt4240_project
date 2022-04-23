package com.tnig.game.controller.events.screen_events;

import com.tnig.game.controller.events.Event;
import com.tnig.game.controller.events.EventName;
import com.tnig.game.model.GameState;

import java.util.HashMap;

public class NewGameEvent extends Event {
    public NewGameEvent(GameState gameState) {
        name = EventName.NEW_GAME;
        data = new HashMap<>();
        data.put("gamestate", gameState);

    }
}
