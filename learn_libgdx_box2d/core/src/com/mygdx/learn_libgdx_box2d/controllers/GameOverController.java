package com.mygdx.learn_libgdx_box2d.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.learn_libgdx_box2d.JumpJellyJump;
import com.mygdx.learn_libgdx_box2d.views.GameOverView;


public class GameOverController implements Screen {
    private JumpJellyJump game;
    private FitViewport viewport;
    private OrthographicCamera cam;
    private Stage stage;
    private Texture background;
    private GameOverView gameOverButtons;
    private Music music;

    public GameOverController(JumpJellyJump game, int score, String message) {
        background = new Texture("background.png");

        this.game = game;
        cam = new OrthographicCamera();
        viewport = new FitViewport(JumpJellyJump.V_WIDTH, JumpJellyJump.V_HEIGHT, cam);
        stage = new Stage(viewport, game.batch);
        gameOverButtons = new GameOverView(game.batch);

        // audio
        music = JumpJellyJump.assetManager.get("audio/music/fun.mp3", Music.class);
        music.setLooping(true);
        music.setVolume(JumpJellyJump.getVolumeLevel());
        music.play();

        Label.LabelStyle headerFont = new Label.LabelStyle(new BitmapFont(), Color.MAROON);
        Label screenLabel = new Label(message, headerFont);

        Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.BLACK);
        Label scoreLabel = new Label("Your score: " + String.valueOf(score), font);


        int width = 100;
        int height = 20;
        float horSpacer = (JumpJellyJump.V_WIDTH - width) / 2;
        float verSpacer = (JumpJellyJump.V_HEIGHT - height*4) / 3;

        Table table = new Table();
        table.left().bottom();

        table.add().width(horSpacer);
        table.add(screenLabel);
        table.add().width(horSpacer);
        table.row();

        table.add().width(horSpacer);
        table.add(scoreLabel);
        table.add().width(horSpacer);
        table.row();

        table.add().width(horSpacer);
        table.add().size(width, height);
        table.add().width(horSpacer);
        table.row();

        table.add().width(horSpacer);
        table.add().size(width, height);
        table.add().width(horSpacer);
        table.row();

        table.add().height(verSpacer);
        table.row();
        table.add().height(verSpacer);
        table.row();

        stage.addActor(table);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if (gameOverButtons.isGameOverScreenRestartPressed()) {
            game.setScreen(new PlayController(game));
            dispose();
        } else if (gameOverButtons.isGameOverScreenMenuPressed()) {
            game.setScreen(new MenuController(game));
            dispose();
        }

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.setProjectionMatrix(cam.combined);

        stage.getBatch().begin();
        stage.getBatch().draw(background, 0, 0, JumpJellyJump.V_WIDTH, JumpJellyJump.V_HEIGHT);
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
