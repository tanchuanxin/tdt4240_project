package com.tnig.game.utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Extremely basic implementation of an EventManager that handles subscriptions to topics.
 * Pushes events to subscribers of a topic.
 * This does not support queues and retries.
 * @param topics a map of topic names to subscribers
 */
public class EventManager {
    private Map<String, List<EventListener>> topics;

    public EventManager() {
        topics = new HashMap<>();
    }

    /**
     * Subscribe to a topic. If the topic name does not exist, a new topic is created.
     * @param topic name of the topic to subscribe to
     * @param subscriber EventListener instance that will be notified on an event call belonging to the topic
     */
    public void subscribe(String topic, EventListener subscriber) {
        if (topics.containsKey(topic)) {
            // Topic found, add subscriber to the topic
            topics.get(topic).add(subscriber);
        }
        else {
            // No topic of the provided name found, create a new topic and add as first subscriber
            ArrayList<EventListener> eventListeners = new ArrayList<>();
            eventListeners.add(subscriber);
            topics.put(topic, eventListeners);
        }
    }

    /**
     * Unsubscribe to a topic. If the topic name does not exist or the EventListener is not a subscriber, nothing happens.
     * If a topic has its last subscriber removed, we remove the topic from EventManager as well.
     * @param topic name of the topic to unsubscribe to
     * @param subscriber an EventListener that is subscribed to the topic
     */
    public void unsubscribe(String topic, EventListener subscriber) {
        if (topics.containsKey(topic)) {
            topics.get(topic).remove(subscriber);

            // Check if that was the last subscriber, if so, we remove the topic
            if (topics.get(topic).size() <= 0) {
                topics.remove(topic);
            }
        }
    }

    /**
     * Pushes an event to all subscribers of the topic that the event belongs to.
     * @param event an Event with the topic name and any supporting data
     */
    public void pushEvent(Event event) {
        if (topics.containsKey(event.topic)) {
            for (EventListener subscriber : topics.get(event.topic)) {
                subscriber.receiveEvent(event);
            }
        }
    }
}
