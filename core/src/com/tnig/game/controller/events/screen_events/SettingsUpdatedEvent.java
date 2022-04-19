package com.tnig.game.controller.events.screen_events;
import com.tnig.game.controller.events.Event;
import com.tnig.game.controller.events.EventName;

import java.util.HashMap;

public class SettingsUpdatedEvent extends Event {
    public SettingsUpdatedEvent() {
        this.name = EventName.SETTINGS_UPDATED;
        // TODO: Update this with actual setting keys and values
        this.data = new HashMap<String, Object>();
    }
}
