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


public class GameOverView implements Disposable {
    public Viewport viewport;
    public Stage stage;
    boolean gameOverScreenMenuPressed, gameOverScreenRestartPressed;
    private int width, height;
    private OrthographicCamera cam;

    public GameOverView(SpriteBatch sb) {
        cam = new OrthographicCamera();
        viewport = new FitViewport(JumpJellyJump.V_WIDTH, JumpJellyJump.V_HEIGHT, cam);
        stage = new Stage(viewport, sb);
        width = 100;
        height = 20;


        Gdx.input.setInputProcessor(stage);

        Image gameOverScreenMenu = new Image(new Texture("buttons/gameOverScreenMenu.png"));
        gameOverScreenMenu.setSize(width, height);
        gameOverScreenMenu.addListener(new InputListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                gameOverScreenMenuPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                gameOverScreenMenuPressed = false;
            }
        });

        Image gameOverScreenRestart = new Image(new Texture("buttons/gameOverScreenRestart.png"));
        gameOverScreenRestart.setSize(width, height);
        gameOverScreenRestart.addListener(new InputListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                gameOverScreenRestartPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                gameOverScreenRestartPressed = false;
            }
        });

        float horSpacer = (JumpJellyJump.V_WIDTH - width) / 2;
        float verSpacer = (JumpJellyJump.V_HEIGHT - height*4) / 3;

        Table table = new Table();
        table.left().bottom();

        table.add().height(verSpacer);
        table.row();

        table.add().width(horSpacer);
        table.add(gameOverScreenMenu).size(width, height);
        table.add().width(horSpacer);
        table.row();

        table.add().width(horSpacer);
        table.add(gameOverScreenRestart).size(width, height);
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

    public boolean isGameOverScreenMenuPressed() {
        return gameOverScreenMenuPressed;
    }

    public boolean isGameOverScreenRestartPressed() {
        return gameOverScreenRestartPressed;
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

}
