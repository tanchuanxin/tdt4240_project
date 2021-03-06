package com.tnig.game.controller.events;

/**
 * Defines an interface for an object that is able to receive events.
 */
public interface EventListener {
    /**
     * Implement this function to be able to receive an event push.
     * @param event an Event type
     */
    void receiveEvent(Event event);
}
