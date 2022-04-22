package com.tnig.game.controller.events.game_events;

import com.tnig.game.controller.events.Event;
import com.tnig.game.controller.events.EventName;

public class StopPlayer extends Event {
    public StopPlayer() {
        name = EventName.STOP_PLAYER;
    }
}
