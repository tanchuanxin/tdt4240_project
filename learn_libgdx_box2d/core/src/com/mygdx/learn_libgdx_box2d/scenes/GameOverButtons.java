package com.mygdx.learn_libgdx_box2d.scenes;

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
import com.mygdx.learn_libgdx_box2d.LearnLibgdxBox2d;


public class GameOverButtons implements Disposable {
    public Viewport viewport;
    public Stage stage;
    boolean gameoverscreen_menu_pressed, gameoverscreen_restart_pressed;
    private int width, height;
    private OrthographicCamera cam;

    public GameOverButtons(SpriteBatch sb) {
        cam = new OrthographicCamera();
        viewport = new FitViewport(LearnLibgdxBox2d.V_WIDTH, LearnLibgdxBox2d.V_HEIGHT, cam);
        stage = new Stage(viewport, sb);
        width = 100;
        height = 20;


        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.left().bottom();

        Image gameoverscreen_menu = new Image(new Texture("gameoverscreen_menu.png"));
        gameoverscreen_menu.setSize(width, height);
        gameoverscreen_menu.addListener(new InputListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                gameoverscreen_menu_pressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                gameoverscreen_menu_pressed = false;
            }
        });

        Image gameoverscreen_restart = new Image(new Texture("gameoverscreen_restart.png"));
        gameoverscreen_restart.setSize(width, height);
        gameoverscreen_restart.addListener(new InputListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                gameoverscreen_restart_pressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                gameoverscreen_restart_pressed = false;
            }
        });

        float horSpacer = (LearnLibgdxBox2d.V_WIDTH - width) / 2;
        float verSpacer = (LearnLibgdxBox2d.V_HEIGHT - height*4) / 3;

        table.add().height(verSpacer);
        table.row();

        table.add().width(horSpacer);
        table.add(gameoverscreen_menu).size(width, height);
        table.add().width(horSpacer);
        table.row();

        table.add().width(horSpacer);
        table.add(gameoverscreen_restart).size(width, height);
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

    public boolean isGameoverscreen_menu_pressed() {
        return gameoverscreen_menu_pressed;
    }

    public boolean isGameoverscreen_restart_pressed() {
        return gameoverscreen_restart_pressed;
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

}
