package com.tnig.game.model.physics_engine;

import com.tnig.game.model.models.ContactObject;

import java.awt.geom.Point2D;

public interface GameObject {
    Point2D getPosition();
    ContactObject getContactObject();
}
