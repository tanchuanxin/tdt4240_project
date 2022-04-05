package com.tnig.game.utilities;

import java.util.Map;

/**
 * Defines how events passed between services should be structured.
 * @param topic a unique string defining the topic this event will be delivered to
 * @param data a map of key:values attaching data to the event for processing
 */
public class Event {
    public String topic;
    public Map<String, Object> data;

    public Event(String topic, Map<String, Object> data) {
        this.topic = topic;
        this.data = data;
    }
}
