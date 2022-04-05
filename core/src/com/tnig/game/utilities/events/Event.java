package com.tnig.game.utilities.events;

import java.util.Map;

/**
 * Defines how events passed between services should be structured.
 * Note that either of these variables can be NULL, so checks should be done!
 */
public class Event {
    public EventName name;
    public Map<String, Object> data;

    public Event() {}
}
