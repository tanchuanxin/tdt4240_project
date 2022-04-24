package com.tnig.game.controller.events.game_events;

import com.tnig.game.controller.events.Event;
import com.tnig.game.controller.events.EventName;

import java.util.HashMap;
import java.util.List;

public class UploadScore extends Event {
    public UploadScore(HashMap<String, Object> scores) {
        name = EventName.UPLOAD_SCORE;
        data = scores;
    }
}
