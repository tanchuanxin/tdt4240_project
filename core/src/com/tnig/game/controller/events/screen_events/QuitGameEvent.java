package com.tnig.game.controller.events.screen_events;

import com.tnig.game.controller.events.Event;
import com.tnig.game.controller.events.EventName;

public class QuitGameEvent extends Event {
    public QuitGameEvent() {
        this.name = EventName.QUIT_GAME;
    }
}
