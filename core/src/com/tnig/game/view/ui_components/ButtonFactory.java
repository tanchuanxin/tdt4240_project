package com.tnig.game.view.ui_components;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.tnig.game.controller.events.Event;
import com.tnig.game.controller.managers.EventManager;
import com.tnig.game.controller.managers.ScreenManager;
import com.tnig.game.utilities.AssetLoader;
import com.tnig.game.view.screens.ScreenName;

public class ButtonFactory {
    private final EventManager eventManager;
    private final ScreenManager screenManager;
    private final Skin skin;
    private Sound clickSound;

    public ButtonFactory(EventManager eventManager, ScreenManager screenManager, AssetLoader assetLoader) {
        this.eventManager = eventManager;
        this.screenManager = screenManager;
        this.clickSound = assetLoader.get(AssetLoader.SOUND_CLICK);
        this.skin = assetLoader.get(AssetLoader.SKIN_PIXTHULHU_UI);
    }

    public Button createSwitchScreenEventButton(final ScreenName screenName,
                                                final Event event,
                                                final String labelString,
                                                final boolean touchDown){
        Label label = new Label(labelString, skin);
        Button button = new Button(label, skin);
        button.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                clickSound.play();
                return touchDown;
            }

            @Override
            public void touchUp(InputEvent inputEvent, float x, float y, int pointer, int button) {
                eventManager.pushEvent(event);
                screenManager.setScreen(screenName);
            }
        });
        return button;
    }

    public Button createSwitchingScreenButton(final ScreenName screenName,
                                              final String labelString,
                                              final boolean touchDown){
        Label label = new Label(labelString, skin);
        Button button = new Button(label, skin);
        button.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                clickSound.play();
                return touchDown;
            }

            @Override
            public void touchUp(InputEvent inputEvent, float x, float y, int pointer, int button) {
                screenManager.setScreen(screenName);
            }
        });
        return button;
    }

    public Button createEventButton(final Event event,
                                    final String labelString,
                                    final boolean touchDown){
        Label label = new Label(labelString, skin);
        Button button = new Button(label, skin);
        button.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                clickSound.play();
                return touchDown;
            }

            @Override
            public void touchUp(InputEvent inputEvent, float x, float y, int pointer, int button) {
                eventManager.pushEvent(event);
            }
        });
        return button;
    }

    public Button createCustomEventButton(final Event event, Button button, final boolean touchDown) {
        if (touchDown) {
            button.addListener(new ClickListener() {
               @Override
               public boolean touchDown(InputEvent inputEvent, float x, float y, int pointer, int button) {
                   clickSound.play();
                   eventManager.pushEvent(event);
                   return true;
               };
            });
        } else {
            button.addListener(new ClickListener() {
                @Override
                public void touchUp(InputEvent inputEvent, float x, float y, int pointer, int button) {
                    eventManager.pushEvent(event);
                }
            });
        }

        return button;
    }
}
