package com.tnig.game.controller.events.screen_events;

import com.tnig.game.controller.events.Event;
import com.tnig.game.controller.events.EventName;
import com.tnig.game.model.GameState;

import java.util.HashMap;
import java.util.List;

public class GameOverEvent extends Event {
    public GameOverEvent(GameState gameState) {
        name = EventName.GAME_OVER;
        data.put("gamestate", gameState);

    }
}
