package com.tnig.game.view.ui_components;

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

    public ButtonFactory(EventManager eventManager, ScreenManager screenManager, AssetLoader assetLoader) {
        this.eventManager = eventManager;
        this.screenManager = screenManager;
        skin = assetLoader.get(AssetLoader.SKIN_PIXTHULHU_UI);
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
                return touchDown;
            }

            @Override
            public void touchUp(InputEvent inputEvent, float x, float y, int pointer, int button) {
                // Start game as 2 players
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
                return touchDown;
            }

            @Override
            public void touchUp(InputEvent inputEvent, float x, float y, int pointer, int button) {
                // Start game as 2 players
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
                return touchDown;
            }

            @Override
            public void touchUp(InputEvent inputEvent, float x, float y, int pointer, int button) {
                // Start game as 2 players
                eventManager.pushEvent(event);
            }
        });
        return button;
    }


}
