package com.mygdx.learn_libgdx_box2d.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.learn_libgdx_box2d.JumpJellyJump;
import com.mygdx.learn_libgdx_box2d.views.LevelView;

public class LevelController implements Screen {
    private JumpJellyJump game;
    private FitViewport viewport;
    private OrthographicCamera cam;
    private Stage stage;
    private Texture background;
    private LevelView levelView;
    private Music music;

    public LevelController(JumpJellyJump game) {
        background = new Texture("background.png");

        this.game = game;
        cam = new OrthographicCamera();
        viewport = new FitViewport(JumpJellyJump.V_WIDTH, JumpJellyJump.V_HEIGHT, cam);
        stage = new Stage(viewport, game.batch);

        levelView = new LevelView(game.batch);

        // audio
        music = JumpJellyJump.assetManager.get("audio/music/fun.mp3", Music.class);
        music.setLooping(true);
        music.setVolume(JumpJellyJump.getVolumeLevel());
        music.play();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if (levelView.isBackPressed()) {
            game.setScreen(new MenuController(game));
            dispose();
        }

        if (levelView.isLevelPressed()) {
            game.setScreen(new PlayController(game, levelView.getLevelSelected()));
        }

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.setProjectionMatrix(cam.combined);

        stage.getBatch().begin();
        stage.getBatch().draw(background, 0, 0, JumpJellyJump.V_WIDTH, JumpJellyJump.V_HEIGHT);
        stage.getBatch().end();

        // set batch to draw what the camera sees
        game.batch.setProjectionMatrix(levelView.stage.getCamera().combined);
        levelView.stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        levelView.resize(width, height);
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
