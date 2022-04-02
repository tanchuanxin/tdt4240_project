package com.tnig.game.view.guis;

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

public class MainMenuScreenGUI extends AbstractGUI {
    private final Table table;

    public MainMenuScreenGUI(OrthographicCamera camera, final AssetLoader assetLoader) {
        super(camera, assetLoader);

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

            ;

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                // Change screen to map select screen
                ScreenManager.getInstance().setScreen(ScreenName.MAP_SELECT);
            }

            ;
        });

        Label twoPlayerBtnLabel = new Label("2 Player", assetLoader.get(assetLoader.SKIN_PIXTHULHU_UI));
        Button twoPlayerBtn = new Button(twoPlayerBtnLabel, assetLoader.get(assetLoader.SKIN_PIXTHULHU_UI));
        twoPlayerBtn.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            ;

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                // Change screen to map select screen
                ScreenManager.getInstance().setScreen(ScreenName.MAP_SELECT);
            }

            ;
        });

        Label leaderboardsBtnLabel = new Label("Leaderboards", assetLoader.get(assetLoader.SKIN_PIXTHULHU_UI));
        Button leaderboardsBtn = new Button(leaderboardsBtnLabel, assetLoader.get(assetLoader.SKIN_PIXTHULHU_UI));
        leaderboardsBtn.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            ;

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                // Change screen to high scores screen
                ScreenManager.getInstance().setScreen(ScreenName.LEADERBOARDS);
            }

            ;
        });

        Label exitBtnLabel = new Label("Exit", assetLoader.get(assetLoader.SKIN_PIXTHULHU_UI));
        Button exitBtn = new Button(exitBtnLabel, assetLoader.get(assetLoader.SKIN_PIXTHULHU_UI));
        exitBtn.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            ;

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                // Dispose all assets and exit game
                // TODO: Shift this responsibility to a controller class
                assetLoader.dispose();
            }

            ;
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
