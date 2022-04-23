package com.tnig.game.controller.events.game_events;

import com.tnig.game.controller.events.Event;
import com.tnig.game.controller.events.EventName;

public class MoveRight extends Event {
    public MoveRight() {
        name = EventName.MOVE_RIGHT;
    }
}
