package com.tnig.game.model.models.interfaces;

public interface ContactObject {
    void handleBeginContact(ContactObject object);
    ModelType getEnum();
    void dispose();
}
