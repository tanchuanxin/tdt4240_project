package com.tnig.game.controller.events.game_events;

import com.tnig.game.controller.events.Event;
import com.tnig.game.controller.events.EventName;

public class Jump extends Event {
    public Jump() {
        name = EventName.JUMP;
    }
}
