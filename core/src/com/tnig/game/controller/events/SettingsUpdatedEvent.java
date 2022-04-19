package com.tnig.game.utilities.events;

import java.util.HashMap;

public class SettingsUpdatedEvent extends Event {
    public SettingsUpdatedEvent() {
        this.name = EventName.SETTINGS_UPDATED;
        // TODO: Update this with actual setting keys and values
        this.data = new HashMap<String, Object>();
    }
}
