package com.tnig.game.controller.events.screen_events;

import com.tnig.game.controller.events.Event;
import com.tnig.game.controller.events.EventName;

public class PauseEvent extends Event {
    public PauseEvent() {
        name = EventName.PAUSE;
    }
}
