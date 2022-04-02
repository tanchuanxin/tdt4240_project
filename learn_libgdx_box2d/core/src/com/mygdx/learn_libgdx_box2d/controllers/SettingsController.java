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
import com.mygdx.learn_libgdx_box2d.views.SettingsView;

public class SettingsController implements Screen {
    private JumpJellyJump game;
    private FitViewport viewport;
    private OrthographicCamera cam;
    private Stage stage;
    private Texture background;
    private SettingsView settingsView;
    private Music music;

    public SettingsController(JumpJellyJump game) {
        background = new Texture("background.png");

        this.game = game;
        cam = new OrthographicCamera();
        viewport = new FitViewport(JumpJellyJump.V_WIDTH, JumpJellyJump.V_HEIGHT, cam);
        stage = new Stage(viewport, game.batch);

        settingsView = new SettingsView(game.batch);

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
        update(delta);

        if (settingsView.isVolumeDownPressed()) {
            JumpJellyJump.setVolumeLevel(JumpJellyJump.getVolumeLevel() - 0.01f);
            music.setVolume(JumpJellyJump.getVolumeLevel());
            dispose();
        } else if (settingsView.isVolumeUpPressed()) {
            JumpJellyJump.setVolumeLevel(JumpJellyJump.getVolumeLevel() + 0.01f);
            music.setVolume(JumpJellyJump.getVolumeLevel());dispose();
            dispose();
        } else if (settingsView.isVolumeMutePressed()) {
            JumpJellyJump.setVolumeLevel(0f);
            music.setVolume(JumpJellyJump.getVolumeLevel());
            dispose();
        } else if (settingsView.isBackPressed()) {
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
        game.batch.setProjectionMatrix(settingsView.stage.getCamera().combined);
        settingsView.stage.draw();
    }

    public void update(float delta) {
        settingsView.update(delta);
    }


    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        settingsView.resize(width, height);
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
