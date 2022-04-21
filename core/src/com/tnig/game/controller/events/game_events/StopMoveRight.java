package com.tnig.game.controller.events.game_events;

import com.tnig.game.controller.events.Event;
import com.tnig.game.controller.events.EventName;

public class StopMoveRight extends Event {
    public StopMoveRight() {
        name = EventName.STOP_MOVE_RIGHT;
    }
}
