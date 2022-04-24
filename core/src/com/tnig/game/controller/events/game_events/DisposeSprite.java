package com.tnig.game.controller.events.game_events;

import com.tnig.game.controller.events.Event;
import com.tnig.game.controller.events.EventName;
import com.tnig.game.model.models.interfaces.Model;

import java.util.HashMap;

public class DisposeSprite extends Event {

    public DisposeSprite(Model model) {
        name = EventName.DISPOSE_SPRITE;
        data.put("object", model);

    }
}
