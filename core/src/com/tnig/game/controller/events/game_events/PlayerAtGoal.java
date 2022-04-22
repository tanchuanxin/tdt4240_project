package com.tnig.game.controller.events.game_events;

import com.tnig.game.controller.events.Event;
import com.tnig.game.controller.events.EventName;

public class PlayerAtGoal extends Event {
    public PlayerAtGoal() {
        name = EventName.PLAYER_AT_GOAL;
    }
}
