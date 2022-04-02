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


public class LeaderboardView implements Disposable {
    public Viewport viewport;
    public Stage stage;
    boolean backPressed;
    private int width, height;
    private OrthographicCamera cam;

    public LeaderboardView(SpriteBatch sb) {
        cam = new OrthographicCamera();
        viewport = new FitViewport(JumpJellyJump.V_WIDTH, JumpJellyJump.V_HEIGHT, cam);
        stage = new Stage(viewport, sb);
        width = 30;
        height = 30;

        Gdx.input.setInputProcessor(stage);

        Image back = new Image(new Texture("buttons/back.png"));
        back.setSize(width, height);
        back.addListener(new InputListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                backPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                backPressed = false;
            }
        });

        float horSpacer = (JumpJellyJump.V_WIDTH - width) / 2;
        float verSpacer = (JumpJellyJump.V_HEIGHT - height) / 2;

        Table table = new Table();
        table.left().bottom();

        table.add().height(verSpacer);
        table.row();

        table.add().width(horSpacer);
        table.add(back).size(width, height);
        table.add().width(horSpacer);
        table.row();

        table.add().height(verSpacer);
        table.row();

        stage.addActor(table);
    }

    public boolean isBackPressed() {
        return backPressed;
    }

    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

}
