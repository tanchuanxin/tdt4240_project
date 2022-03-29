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
import com.mygdx.learn_libgdx_box2d.views.MenuView;

public class MenuController implements Screen {
    private JumpJellyJump game;
    private FitViewport viewport;
    private OrthographicCamera cam;
    private Stage stage;
    private Texture background;
    private MenuView menuButtons;
    private Music music;

    public MenuController(JumpJellyJump game) {
        background = new Texture("background.png");

        this.game = game;
        cam = new OrthographicCamera();
        viewport = new FitViewport(JumpJellyJump.V_WIDTH, JumpJellyJump.V_HEIGHT, cam);
        stage = new Stage(viewport, game.batch);

        menuButtons = new MenuView(game.batch);

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
        if (menuButtons.isMenuScreen1PlayerPressed()) {

        } else if (menuButtons.isMenuScreen2PlayerPressed()) {
            game.setScreen(new PlayController(game));
            dispose();
        } else if (menuButtons.isMenuScreenLeaderboardPressed()) {
            game.setScreen(new LeaderboardController(game));
            dispose();
        } else if (menuButtons.isMenuScreenSettingsPressed()) {
            game.setScreen(new SettingsController(game));
            dispose();
        }

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.setProjectionMatrix(cam.combined);

        stage.getBatch().begin();
        stage.getBatch().draw(background, 0, 0, JumpJellyJump.V_WIDTH, JumpJellyJump.V_HEIGHT);
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
