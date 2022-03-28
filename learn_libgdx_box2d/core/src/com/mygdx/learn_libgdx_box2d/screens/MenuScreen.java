package com.mygdx.learn_libgdx_box2d.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.learn_libgdx_box2d.LearnLibgdxBox2d;
import com.mygdx.learn_libgdx_box2d.scenes.MenuButtons;

import org.graalvm.compiler.core.common.type.ArithmeticOpTable;

public class MenuScreen implements Screen {
    private LearnLibgdxBox2d game;
    private FitViewport viewport;
    private OrthographicCamera cam;
    private Stage stage;
    private Texture background;
    private MenuButtons menuButtons;

    public MenuScreen(LearnLibgdxBox2d game) {
        background = new Texture("menuscreen.png");

        this.game = game;
        cam = new OrthographicCamera();
        viewport = new FitViewport(LearnLibgdxBox2d.V_WIDTH, LearnLibgdxBox2d.V_HEIGHT, cam);
        stage = new Stage(viewport, game.batch);

        menuButtons = new MenuButtons(game.batch);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if (menuButtons.isMenuscreen_1player_pressed()) {
            game.setScreen(new PlayScreen(game));
            dispose();
        } else if (menuButtons.isMenuscreen_2player_pressed()) {
            game.setScreen(new PlayScreen(game));
            dispose();
        } else if (menuButtons.isMenuscreen_leaderboard_pressed()) {
            game.setScreen(new PlayScreen(game));
            dispose();
        } else if (menuButtons.isMenuscreen_settings_pressed()) {
            game.setScreen(new PlayScreen(game));
            dispose();
        }

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.setProjectionMatrix(cam.combined);

        stage.getBatch().begin();
        stage.getBatch().draw(background, 0, 0, LearnLibgdxBox2d.V_WIDTH, LearnLibgdxBox2d.V_HEIGHT);
        stage.getBatch().end();

        // set batch to draw what the camera sees
        game.batch.setProjectionMatrix(menuButtons.stage.getCamera().combined);
        menuButtons.stage.draw();

        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        menuButtons.resize(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
