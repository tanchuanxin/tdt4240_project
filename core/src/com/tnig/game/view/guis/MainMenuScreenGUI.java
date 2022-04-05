package com.tnig.game.view.guis;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.tnig.game.controller.managers.ScreenManager;
import com.tnig.game.controller.screens.ScreenName;
import com.tnig.game.utilities.AssetLoader;
import com.tnig.game.utilities.events.Event;
import com.tnig.game.utilities.events.EventManager;
import com.tnig.game.utilities.events.NewGameEvent;
import com.tnig.game.utilities.events.QuitGameEvent;
import com.tnig.game.utilities.events.ViewLeaderboardsEvent;

public class MainMenuScreenGUI extends AbstractGUI {
    private final Table table;
    private final EventManager eventManager;

    public MainMenuScreenGUI(OrthographicCamera camera, AssetLoader assetLoader, final EventManager eventManager) {
        super(camera, assetLoader);
        this.eventManager = eventManager;

        table = new Table();

        // Create actors
        Label titleLabel = new Label("The Nearly Impossible Game", assetLoader.get(assetLoader.SKIN_PIXTHULHU_UI));
        titleLabel.setAlignment(Align.center);

        Label onePlayerBtnLabel = new Label("1 Player", assetLoader.get(assetLoader.SKIN_PIXTHULHU_UI));
        Button onePlayerBtn = new Button(onePlayerBtnLabel, assetLoader.get(assetLoader.SKIN_PIXTHULHU_UI));
        onePlayerBtn.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                // Start game as 1 player
                eventManager.pushEvent(new NewGameEvent(1));
            }
        });

        Label twoPlayerBtnLabel = new Label("2 Player", assetLoader.get(assetLoader.SKIN_PIXTHULHU_UI));
        Button twoPlayerBtn = new Button(twoPlayerBtnLabel, assetLoader.get(assetLoader.SKIN_PIXTHULHU_UI));
        twoPlayerBtn.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                // Start game as 2 players
                eventManager.pushEvent(new NewGameEvent(2));
            }
        });

        Label leaderboardsBtnLabel = new Label("Leaderboards", assetLoader.get(assetLoader.SKIN_PIXTHULHU_UI));
        Button leaderboardsBtn = new Button(leaderboardsBtnLabel, assetLoader.get(assetLoader.SKIN_PIXTHULHU_UI));
        leaderboardsBtn.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                // Go to leaderboards screen
                eventManager.pushEvent(new ViewLeaderboardsEvent());
            }
        });

        Label exitBtnLabel = new Label("Exit", assetLoader.get(assetLoader.SKIN_PIXTHULHU_UI));
        Button exitBtn = new Button(exitBtnLabel, assetLoader.get(assetLoader.SKIN_PIXTHULHU_UI));
        exitBtn.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                // Quit game event
                eventManager.pushEvent(new QuitGameEvent());
            }
        });

        // Add actors to table layout
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
    public void resize(int width, int height) {
        super.resize(width, height);
        table.setPosition(width / 2, height / 2, Align.center);
    }
}
