package com.tnig.game.controller.events.game_events;

import com.tnig.game.controller.events.Event;
import com.tnig.game.controller.events.EventName;

public class StopJump extends Event {
    public StopJump() {
        name = EventName.STOP_JUMP;
    }
}
