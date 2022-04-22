package com.tnig.game.view.screens;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.tnig.game.controller.events.screen_events.InitGameEvent;
import com.tnig.game.controller.events.screen_events.QuitGameEvent;
import com.tnig.game.controller.managers.EventManager;
import com.tnig.game.controller.managers.ScreenManager;
import com.tnig.game.utilities.AssetLoader;
import com.tnig.game.view.ui_components.ButtonFactory;

public class MainMenuScreen extends AbstractScreen {
    private final Table table;

    public MainMenuScreen(final ScreenManager screenManager,
                          OrthographicCamera camera,
                          AssetLoader assetLoader,
                          final EventManager eventManager) {

        super(camera, assetLoader);

        Skin skin = assetLoader.get(AssetLoader.SKIN_PIXTHULHU_UI);



        // Create actors
        Label titleLabel = new Label("The Nearly Impossible Game", skin);
        titleLabel.setAlignment(Align.center);

        ButtonFactory buttonFactory = new ButtonFactory(eventManager, screenManager, assetLoader);

        Button onePlayerBtn = buttonFactory.createSwitchScreenEventButton(
                ScreenName.MAP_SELECT, new InitGameEvent(1), "1 Player", true);

        Button twoPlayerBtn = buttonFactory.createSwitchScreenEventButton(
                ScreenName.MAP_SELECT, new InitGameEvent(2), "2 Player", true);



        Button leaderboardsBtn = buttonFactory
                .createSwitchingScreenButton(ScreenName.LEADERBOARDS, "Leaderboards", true);

        Button exitBtn = buttonFactory
                .createEventButton(new QuitGameEvent(), "Exit", true);


        // Add actors to table layout
        table = new Table();
        table.pad(50f);
        table.setWidth(600f);
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

}
