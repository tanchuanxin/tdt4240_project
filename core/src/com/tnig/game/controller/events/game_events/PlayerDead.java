package com.tnig.game.controller.events.game_events;

import com.tnig.game.controller.events.Event;
import com.tnig.game.controller.events.EventName;

public class PlayerDead extends Event {
    public PlayerDead() {
        name = EventName.PLAYER_DEAD;
    }
}
