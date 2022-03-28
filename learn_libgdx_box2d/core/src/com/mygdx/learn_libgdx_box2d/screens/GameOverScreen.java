package com.mygdx.learn_libgdx_box2d.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.learn_libgdx_box2d.LearnLibgdxBox2d;
import com.mygdx.learn_libgdx_box2d.scenes.GameOverButtons;
import com.mygdx.learn_libgdx_box2d.scenes.MenuButtons;


public class GameOverScreen implements Screen {
    private LearnLibgdxBox2d game;
    private FitViewport viewport;
    private OrthographicCamera cam;
    private Stage stage;
    private Texture background;
    private GameOverButtons gameOverButtons;
    private int score;

    public GameOverScreen(LearnLibgdxBox2d game, int score) {
        background = new Texture("gameoverscreen.png");

        this.game = game;
        cam = new OrthographicCamera();
        viewport = new FitViewport(LearnLibgdxBox2d.V_WIDTH, LearnLibgdxBox2d.V_HEIGHT, cam);
        stage = new Stage(viewport, game.batch);
        gameOverButtons = new GameOverButtons(game.batch);

        this.score = score;


        Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);

        Table table = new Table();
        table.bottom();
        table.setFillParent(true);

        Label menuLabel = new Label("Your score: " + String.valueOf(score), font);

        table.add(menuLabel).expandX().padTop(10f);
        table.row();
        table.add().height(LearnLibgdxBox2d.V_HEIGHT*5/8);
        table.row();

        stage.addActor(table);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if (gameOverButtons.isGameoverscreen_restart_pressed()) {
            game.setScreen(new PlayScreen(game));
            dispose();
        } else if (gameOverButtons.isGameoverscreen_menu_pressed()) {
            game.setScreen(new MenuScreen(game));
            dispose();
        }

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.setProjectionMatrix(cam.combined);

        stage.getBatch().begin();
        stage.getBatch().draw(background, 0, 0, LearnLibgdxBox2d.V_WIDTH, LearnLibgdxBox2d.V_HEIGHT);
        stage.getBatch().end();

        // set batch to draw what the camera sees
        game.batch.setProjectionMatrix(gameOverButtons.stage.getCamera().combined);
        gameOverButtons.stage.draw();

        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        gameOverButtons.resize(width, height);
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
