package com.tnig.game.controller.managers;

import com.tnig.game.controller.events.Event;
import com.tnig.game.controller.events.EventListener;
import com.tnig.game.controller.events.EventName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Extremely basic implementation of an EventManager that handles subscriptions to events.
 * Pushes events to subscribers. This does not support queues and retries.
 */
public class EventManager {
    private final Map<EventName, List<EventListener>> events;

    public EventManager() {
        events = new HashMap<>();
    }

    /**
     * Subscribe to an event. If the event name does not exist, a new event subscription is created.
     * @param eventName name of the event to subscribe to
     * @param subscriber EventListener instance that will be notified on an event call belonging to the topic
     */
    public void subscribe(EventName eventName, EventListener subscriber) {
        if (events.containsKey(eventName)) {
            // Topic found, add subscriber to the topic
            events.get(eventName).add(subscriber);
        }
        else {
            // No topic of the provided name found, create a new topic and add as first subscriber
            ArrayList<EventListener> eventListeners = new ArrayList<>();
            eventListeners.add(subscriber);
            events.put(eventName, eventListeners);
        }
    }

    /**
     * Unsubscribe to an event. If the event name does not exist or the EventListener is not a subscriber, nothing happens.
     * If an event has its last subscriber removed, we remove the event subscription from EventManager as well.
     * @param eventName name of the event to unsubscribe to
     * @param subscriber an EventListener that is subscribed to the topic
     */
    public void unsubscribe(EventName eventName, EventListener subscriber) {
        if (events.containsKey(eventName)) {
            events.get(eventName).remove(subscriber);

            // Check if that was the last subscriber, if so, we remove the topic
            if (events.get(eventName).size() <= 0) {
                events.remove(eventName);
            }
        }
    }

    /**
     * Pushes an event to all subscribers of the event subscription.
     * @param event an Event with the event name and any supporting data
     */
    public void pushEvent(Event event) {
        if (events.containsKey(event.name)) {

            for (EventListener subscriber : events.get(event.name)) {
                subscriber.receiveEvent(event);
            }
            System.out.println("Push Event: " + event.name);
        }
    }

    /**
     * Debugging function to retrieve all event names (aka all events with at least one subscriber).
     * @return events a Set of topic names
     */
    public Set<EventName> getEvents() {
        return events.keySet();
    }

    /**
     * Debugging function to retrieve all subscribers of an event.
     * Returns an empty List if there are no subscribers to the event, or if the event cannot be found.
     * @param eventName name of the event to retrieve subscribers for
     * @return subscribers a List of subscribers of the event
     */
    public List<EventListener> getSubscribers(EventName eventName) {
        if (events.containsKey(eventName)) {
            return events.get(eventName);
        }
        else {
            return new ArrayList<EventListener>();
        }
    }

    /**
     * Debugging function to retrieve all events and their corresponding subscribers.
     * @return events a Map of event name : subscribers
     */
    public Map<EventName, List<EventListener>> getEventsAndSubscribers() {
        return events;
    }
}
