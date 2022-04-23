package com.tnig.game.view.screens;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.tnig.game.controller.events.Event;
import com.tnig.game.controller.events.EventListener;
import com.tnig.game.controller.events.EventName;
import com.tnig.game.controller.events.screen_events.InitGameEvent;
import com.tnig.game.controller.events.screen_events.QuitGameEvent;
import com.tnig.game.controller.managers.EventManager;
import com.tnig.game.controller.managers.ScreenManager;
import com.tnig.game.utilities.AssetLoader;
import com.tnig.game.view.ui_components.ButtonFactory;

public class MainMenuScreen extends AbstractScreen implements EventListener {
    private final Table table;
    private final Music mainMenuMusic;

    public MainMenuScreen(final ScreenManager screenManager,
                          OrthographicCamera camera,
                          AssetLoader assetLoader,
                          final EventManager eventManager) {

        super(camera, assetLoader);

        // Subscribe to event
        eventManager.subscribe(EventName.MAP_SELECTED, this);

        Skin skin = assetLoader.get(AssetLoader.SKIN_PIXTHULHU_UI);

        // Create actors
        Label titleLabel = new Label("The Nearly Impossible Game", skin, "title");
        titleLabel.setAlignment(Align.center);
        titleLabel.setFontScale(0.5f);

        ButtonFactory buttonFactory = new ButtonFactory(eventManager, screenManager, assetLoader);

        Button onePlayerBtn = buttonFactory.createSwitchScreenEventButton(
                ScreenName.MAP_SELECT, new InitGameEvent(1), "1 Player", true);
        onePlayerBtn.setWidth(400f);

        Button twoPlayerBtn = buttonFactory.createSwitchScreenEventButton(
                ScreenName.MAP_SELECT, new InitGameEvent(2), "2 Player", true);
        twoPlayerBtn.setWidth(400f);

        Button leaderboardsBtn = buttonFactory
                .createSwitchingScreenButton(ScreenName.LEADERBOARD_SELECTION, "Leaderboards", true);
        leaderboardsBtn.setWidth(400f);

        Button exitBtn = buttonFactory
                .createEventButton(new QuitGameEvent(), "Exit", true);
        exitBtn.setWidth(400f);

        // Add actors to table layout
        table = new Table();
        table.pad(30f);
        table.setWidth(400f);
        table.setPosition(stage.getWidth() / 2, stage.getHeight() / 2, Align.center);
        table.row().spaceBottom(20f);
        table.add(titleLabel).center();
        table.row().spaceBottom(20f);
        table.add(onePlayerBtn).center().fillX();
        table.row().spaceBottom(20f);
        table.add(twoPlayerBtn).center().fillX();
        table.row().spaceBottom(20f);
        table.add(leaderboardsBtn).center().fillX();
        table.row().spaceBottom(20f);
        table.add(exitBtn).center().fillX();

        // Add actors to stage
        stage.addActor(table);

        // Play background music
        // Play background music
        this.mainMenuMusic = assetLoader.get(AssetLoader.MUSIC_A_BIT_OF_HOPE);
        mainMenuMusic.setLooping(true);
        mainMenuMusic.play();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        table.setPosition(width / 2f, height / 2f, Align.center);
    }

    public void receiveEvent(Event event) {
        switch (event.name) {
            case MAP_SELECTED:
                mainMenuMusic.stop();
                break;
        }
    }
}
