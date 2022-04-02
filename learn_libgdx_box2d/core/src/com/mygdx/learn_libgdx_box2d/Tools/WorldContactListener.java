package com.mygdx.learn_libgdx_box2d.Tools;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.mygdx.learn_libgdx_box2d.JumpJellyJump;
import com.mygdx.learn_libgdx_box2d.models.InteractiveTileObject;
import com.mygdx.learn_libgdx_box2d.models.Jelly;

public class WorldContactListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        int cDef = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;

        switch (cDef) {
            case JumpJellyJump.JELLY_BIT | JumpJellyJump.COIN_BIT:
                if(fixA.getFilterData().categoryBits == JumpJellyJump.JELLY_BIT)
                    ((InteractiveTileObject) fixB.getUserData()).onHit();
                else
                    ((InteractiveTileObject) fixA.getUserData()).onHit();
                break;
            case JumpJellyJump.ENEMY_BIT | JumpJellyJump.JELLY_BIT:
                if(fixA.getFilterData().categoryBits == JumpJellyJump.JELLY_BIT)
                    ((Jelly)fixA.getUserData()).die();
                else
                    ((Jelly)fixB.getUserData()).die();
                break;
            case JumpJellyJump.JELLY_BIT | JumpJellyJump.FLAG_BIT:
                if(fixA.getFilterData().categoryBits == JumpJellyJump.JELLY_BIT)
                    ((Jelly)fixA.getUserData()).win();
                else
                    ((Jelly)fixB.getUserData()).win();
                break;
        }
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
