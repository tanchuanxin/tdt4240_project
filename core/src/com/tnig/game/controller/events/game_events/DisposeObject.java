package com.tnig.game.controller.events.game_events;

import com.tnig.game.controller.events.Event;
import com.tnig.game.controller.events.EventName;

import java.util.HashMap;

public class DisposeObject extends Event {

    public DisposeObject(Object object) {
        name = EventName.DISPOSE_OBJECT;
        data = new HashMap<>();
        data.put("object", object);

    }
}
