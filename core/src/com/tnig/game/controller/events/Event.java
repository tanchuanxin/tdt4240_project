package com.tnig.game.controller.events;

import java.util.HashMap;
import java.util.Map;

/**
 * Defines how events passed between services should be structured.
 * Note that either of these variables can be NULL, so checks should be done!
 */
public abstract class Event {
    public EventName name;
    protected Map<String, Object> data = new HashMap<>();

    public Event() {}


    /** Returns the object for the given key, casting it to clazz.
     * @param key the key of the object
     * @param clazz the class of the object
     * @return the object or null if the object is not in the map
     * @throws ClassCastException if the object with the given key is not of type clazz */
    public <T> T getData (String key, Class<T> clazz) {
        return (T)get(key);
    }

    private Object get(String key){
        return data.get(key);
    }
}
