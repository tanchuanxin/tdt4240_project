package com.tnig.game.controller.events.screen_events;

import com.tnig.game.controller.events.Event;
import com.tnig.game.controller.events.EventName;

public class ResumeEvent extends Event {
    public ResumeEvent() {
        name = EventName.RESUME;
    }
}
