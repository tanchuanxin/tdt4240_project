package com.mygdx.learn_libgdx_box2d.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
import com.mygdx.learn_libgdx_box2d.LearnLibgdxBox2d;


public class MenuButtons implements Disposable {
    public Viewport viewport;
    public Stage stage;
    boolean menuscreen_1player_pressed, menuscreen_2player_pressed, menuscreen_leaderboard_pressed, menuscreen_settings_pressed;
    private int width, height;
    private OrthographicCamera cam;

    public MenuButtons(SpriteBatch sb) {
        cam = new OrthographicCamera();
        viewport = new FitViewport(LearnLibgdxBox2d.V_WIDTH, LearnLibgdxBox2d.V_HEIGHT, cam);
        stage = new Stage(viewport, sb);
        width = 100;
        height = 20;


        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.left().bottom();

        Image menuscreen_1player = new Image(new Texture("menuscreen_1player.png"));
        menuscreen_1player.setSize(width, height);
        menuscreen_1player.addListener(new InputListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                menuscreen_1player_pressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                menuscreen_1player_pressed = false;
            }
        });

        Image menuscreen_2player = new Image(new Texture("menuscreen_2player.png"));
        menuscreen_2player.setSize(width, height);
        menuscreen_2player.addListener(new InputListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                menuscreen_1player_pressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                menuscreen_1player_pressed = false;
            }
        });

        Image menuscreen_leaderboard = new Image(new Texture("menuscreen_leaderboard.png"));
        menuscreen_leaderboard.setSize(width, height);
        menuscreen_leaderboard.addListener(new InputListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                menuscreen_leaderboard_pressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                menuscreen_leaderboard_pressed = false;
            }
        });

        Image menuscreen_settings = new Image(new Texture("menuscreen_settings.png"));
        menuscreen_settings.setSize(width, height);
        menuscreen_settings.addListener(new InputListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                menuscreen_settings_pressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                menuscreen_settings_pressed = false;
            }
        });

        float horSpacer = (LearnLibgdxBox2d.V_WIDTH - width) / 2;
        float verSpacer = (LearnLibgdxBox2d.V_HEIGHT - height*4) / 3;

        table.add().height(verSpacer);
        table.row();

        table.add().width(horSpacer);
        table.add(menuscreen_1player).size(width, height);
        table.add().width(horSpacer);
        table.row();

        table.add().width(horSpacer);
        table.add(menuscreen_2player).size(width, height);
        table.add().width(horSpacer);
        table.row();

        table.add().width(horSpacer);
        table.add(menuscreen_leaderboard).size(width, height);
        table.add().width(horSpacer);
        table.row();

        table.add().width(horSpacer);
        table.add(menuscreen_settings).size(width, height);
        table.add().width(horSpacer);
        table.row();

        table.add().height(verSpacer);
        table.row();
        table.add().height(verSpacer);
        table.row();

        stage.addActor(table);
    }

    public void draw() {
        stage.draw();
    }

    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    public boolean isMenuscreen_1player_pressed() {
        return menuscreen_1player_pressed;
    }

    public boolean isMenuscreen_2player_pressed() {
        return menuscreen_2player_pressed;
    }

    public boolean isMenuscreen_leaderboard_pressed() {
        return menuscreen_leaderboard_pressed;
    }

    public boolean isMenuscreen_settings_pressed() {
        return menuscreen_settings_pressed;
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

}
