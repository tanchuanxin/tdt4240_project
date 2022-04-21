package com.tnig.game.controller.events.game_events;

import com.tnig.game.controller.events.Event;
import com.tnig.game.controller.events.EventName;

public class StopMoveLeft extends Event {
    public StopMoveLeft() {
        this.name = EventName.STOP_MOVE_LEFT;
    }
}
