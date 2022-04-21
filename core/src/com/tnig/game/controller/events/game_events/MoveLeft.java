package com.tnig.game.controller.events.game_events;

import com.tnig.game.controller.events.Event;
import com.tnig.game.controller.events.EventName;

public class MoveLeft extends Event {
    public MoveLeft() {
        this.name = EventName.MOVE_LEFT;
    }
}
