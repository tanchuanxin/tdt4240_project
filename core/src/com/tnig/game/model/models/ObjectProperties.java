package com.tnig.game.model.models;

import com.badlogic.gdx.maps.MapProperties;

/**
 * This class encapsulates the MapProperties class from LibGDX
 */
public class ObjectProperties {
    private final MapProperties properties;
    public ObjectProperties(MapProperties properties) {
        this.properties = properties;
    }

    /** Returns the object for the given key, casting it to clazz.
     * @param key the key of the object
     * @param clazz the class of the object
     * @return the object or null if the object is not in the map
     * @throws ClassCastException if the object with the given key is not of type clazz */
    public <T> T get (String key, Class<T> clazz) {
        return (T)get(key);
    }

    /** @param key property name
     * @return the value for that property if it exists, otherwise, null */
    private Object get (String key) {
        return properties.get(key);
    }

}
