package com.mygdx.learn_libgdx_box2d.Tools;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.mygdx.learn_libgdx_box2d.LearnLibgdxBox2d;
import com.mygdx.learn_libgdx_box2d.sprites.InteractiveTileObject;
import com.mygdx.learn_libgdx_box2d.sprites.Jelly;

public class WorldContactListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        int cDef = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;

        switch (cDef) {
            case LearnLibgdxBox2d.JELLY_BIT | LearnLibgdxBox2d.COIN_BIT:
                if(fixA.getFilterData().categoryBits == LearnLibgdxBox2d.JELLY_BIT)
                    ((InteractiveTileObject) fixB.getUserData()).onHit();
                else
                    ((InteractiveTileObject) fixA.getUserData()).onHit();
                break;
            case LearnLibgdxBox2d.ENEMY_BIT | LearnLibgdxBox2d.JELLY_BIT:
                if(fixA.getFilterData().categoryBits == LearnLibgdxBox2d.JELLY_BIT)
                    ((Jelly)fixA.getUserData()).die();
                else
                    ((Jelly)fixB.getUserData()).die();
                break;
        }



//        // hit enemy
//        if (fixA.getUserData() == "enemyBody" || fixB.getUserData() == "enemyBody") {
//            Gdx.app.log("Enemy", "Collision");
//
//            // set the enemyBody to the fixture that the enemyBody belongs to
//            Fixture enemyBody = fixA.getUserData() == "enemyBody" ? fixA : fixB;
//            // set the object to the other fixture
//            Fixture object = enemyBody == fixA ? fixB : fixA;
//
//            // check if we hit player
//            if (object.getFilterData().categoryBits == LearnLibgdxBox2d.JELLY_BIT) {
//                ((Jelly) object.getUserData()).hit();
//            }
//        }
//
//        if (fixA.getUserData() == "top" || fixB.getUserData() == "top") {
//            // set the top to the fixture that the top belongs to
//            Fixture top = fixA.getUserData() == "top" ? fixA : fixB;
//            // set the object to the other fixture
//            Fixture object = top == fixA ? fixB : fixA;
//
//            // implement the onTopHit() of the concrete class
//            if (object.getUserData() != null && InteractiveTileObject.class.isAssignableFrom(object.getUserData().getClass())) {
//                ((InteractiveTileObject) object.getUserData()).onTopHit();
//            }
//        }
//
//        if (fixA.getUserData() == "bot" || fixB.getUserData() == "bot") {
//            // set the bot to the fixture that the bot belongs to
//            Fixture bot = fixA.getUserData() == "bot" ? fixA : fixB;
//            // set the object to the other fixture
//            Fixture object = bot == fixA ? fixB : fixA;
//
//            // implement the onBotHit() of the concrete class
//            if (object.getUserData() != null && InteractiveTileObject.class.isAssignableFrom(object.getUserData().getClass())) {
//                ((InteractiveTileObject) object.getUserData()).onBotHit();
//            }
//        }
//
//        if (fixA.getUserData() == "left" || fixB.getUserData() == "left") {
//            // set the left to the fixture that the left belongs to
//            Fixture left = fixA.getUserData() == "left" ? fixA : fixB;
//            // set the object to the other fixture
//            Fixture object = left == fixA ? fixB : fixA;
//
//            // implement the onLeftHit() of the concrete class
//            if (object.getUserData() != null && InteractiveTileObject.class.isAssignableFrom(object.getUserData().getClass())) {
//                ((InteractiveTileObject) object.getUserData()).onLeftHit();
//            }
//        }
//
//        if (fixA.getUserData() == "right" || fixB.getUserData() == "right") {
//            // set the right to the fixture that the right belongs to
//            Fixture right = fixA.getUserData() == "right" ? fixA : fixB;
//            // set the object to the other fixture
//            Fixture object = right == fixA ? fixB : fixA;
//
//            // implement the onRightHit() of the concrete class
//            if (object.getUserData() != null && InteractiveTileObject.class.isAssignableFrom(object.getUserData().getClass())) {
//                ((InteractiveTileObject) object.getUserData()).onRightHit();
//            }
//        }
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
