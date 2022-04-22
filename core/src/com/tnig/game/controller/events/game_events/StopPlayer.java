package com.tnig.game.controller.events.game_events;

import com.badlogic.gdx.Input;
import com.tnig.game.controller.events.Event;
import com.tnig.game.controller.events.EventName;

import java.util.HashMap;

public class StopPlayer extends Event {
    public StopPlayer(int key) {
        name = EventName.STOP_PLAYER;
        data = new HashMap<>();
        data.put("key", key);
    }
}
