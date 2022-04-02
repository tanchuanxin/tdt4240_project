package com.mygdx.learn_libgdx_box2d.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.learn_libgdx_box2d.JumpJellyJump;


public class MenuView implements Disposable {
    public Viewport viewport;
    public Stage stage;
    boolean menuScreen1PlayerPressed, menuScreen2PlayerPressed, menuScreenLeaderboardPressed, menuScreenSettingsPressed;
    private int width, height;
    private OrthographicCamera cam;

    public MenuView(SpriteBatch sb) {
        cam = new OrthographicCamera();
        viewport = new FitViewport(JumpJellyJump.V_WIDTH, JumpJellyJump.V_HEIGHT, cam);
        stage = new Stage(viewport, sb);
        width = 100;
        height = 20;


        Gdx.input.setInputProcessor(stage);

        Image menuScreen1Player = new Image(new Texture("buttons/menuScreen1Player.png"));
        menuScreen1Player.setSize(width, height);
        menuScreen1Player.addListener(new InputListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                menuScreen1PlayerPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                menuScreen1PlayerPressed = false;
            }
        });

        Image menuScreen2Player = new Image(new Texture("buttons/menuScreen2Player.png"));
        menuScreen2Player.setSize(width, height);
        menuScreen2Player.addListener(new InputListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                menuScreen2PlayerPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                menuScreen2PlayerPressed = false;
            }
        });

        Image menuScreenLeaderboard = new Image(new Texture("buttons/menuScreenLeaderboard.png"));
        menuScreenLeaderboard.setSize(width, height);
        menuScreenLeaderboard.addListener(new InputListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                menuScreenLeaderboardPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                menuScreenLeaderboardPressed = false;
            }
        });

        Image menuScreenSettings = new Image(new Texture("buttons/menuScreenSettings.png"));
        menuScreenSettings.setSize(width, height);
        menuScreenSettings.addListener(new InputListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                menuScreenSettingsPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                menuScreenSettingsPressed = false;
            }
        });

        float horSpacer = (JumpJellyJump.V_WIDTH - width) / 2;
        float verSpacer = (JumpJellyJump.V_HEIGHT - height*4) / 3;

        Table table = new Table();
        table.left().bottom();

        table.add().height(verSpacer);
        table.row();

        table.add().width(horSpacer);
        table.add(menuScreen1Player).size(width, height);
        table.add().width(horSpacer);
        table.row();

        table.add().width(horSpacer);
        table.add(menuScreen2Player).size(width, height);
        table.add().width(horSpacer);
        table.row();

        table.add().width(horSpacer);
        table.add(menuScreenLeaderboard).size(width, height);
        table.add().width(horSpacer);
        table.row();

        table.add().width(horSpacer);
        table.add(menuScreenSettings).size(width, height);
        table.add().width(horSpacer);
        table.row();

        table.add().height(verSpacer);
        table.row();
        table.add().height(verSpacer);
        table.row();

        stage.addActor(table);
    }

    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    public boolean isMenuScreen1PlayerPressed() {
        return menuScreen1PlayerPressed;
    }

    public boolean isMenuScreen2PlayerPressed() {
        return menuScreen2PlayerPressed;
    }

    public boolean isMenuScreenLeaderboardPressed() {
        return menuScreenLeaderboardPressed;
    }

    public boolean isMenuScreenSettingsPressed() {
        return menuScreenSettingsPressed;
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

}
