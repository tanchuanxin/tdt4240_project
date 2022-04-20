package com.tnig.game.model.physics_engine;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.tnig.game.model.models.ContactObject;

public class WorldContactListener implements ContactListener {


    @Override
    public void beginContact(Contact contact) {

        ContactObject objectA = (ContactObject) contact.getFixtureA().getUserData();
        ContactObject objectB = (ContactObject) contact.getFixtureB().getUserData();

        objectA.handleBeginContact(objectB);
        objectB.handleBeginContact(objectA);

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }



}
