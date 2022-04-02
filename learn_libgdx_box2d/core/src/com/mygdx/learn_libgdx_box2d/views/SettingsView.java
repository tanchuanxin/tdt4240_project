package com.mygdx.learn_libgdx_box2d.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.learn_libgdx_box2d.JumpJellyJump;


public class SettingsView implements Disposable {
    public Viewport viewport;
    public Stage stage;
    boolean volumeUpPressed, volumeDownPressed, volumeMutePressed, backPressed;
    private int width, height;
    private OrthographicCamera cam;
    private String volume;
    private Label.LabelStyle headerFont;
    private Label volumeLabel;

    public SettingsView(SpriteBatch sb) {
        cam = new OrthographicCamera();
        viewport = new FitViewport(JumpJellyJump.V_WIDTH, JumpJellyJump.V_HEIGHT, cam);
        stage = new Stage(viewport, sb);
        width = 30;
        height = 30;

        volume = Float.toString((float)Math.floor(JumpJellyJump.getVolumeLevel()*100));
        headerFont = new Label.LabelStyle(new BitmapFont(), Color.BLACK);
        volumeLabel = new Label(volume, headerFont);

        Gdx.input.setInputProcessor(stage);

        Image volumeUp = new Image(new Texture("buttons/volumeUp.png"));
        volumeUp.setSize(width, height);
        volumeUp.addListener(new InputListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                volumeUpPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                volumeUpPressed = false;
            }
        });

        Image volumeDown = new Image(new Texture("buttons/volumeDown.png"));
        volumeDown.setSize(width, height);
        volumeDown.addListener(new InputListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                volumeDownPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                volumeDownPressed = false;
            }
        });

        Image volumeMute = new Image(new Texture("buttons/volumeMute.png"));
        volumeMute.setSize(width, height);
        volumeMute.addListener(new InputListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                volumeMutePressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                volumeMutePressed = false;
            }
        });

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

        float horSpacer = (JumpJellyJump.V_WIDTH - width*3) / 2;
        float verSpacer = (JumpJellyJump.V_HEIGHT - height*2) / 2;

        Table table = new Table();
        table.left().bottom();

        table.add().height(verSpacer);
        table.add().height(verSpacer);
        table.add().height(verSpacer);
        table.add().height(verSpacer);
        table.add().height(verSpacer);
        table.row();

        table.add().height(verSpacer);
        table.add(back).size(width, height);
        table.add(volumeLabel).height(verSpacer);
        table.add().height(verSpacer);
        table.add().height(verSpacer);
        table.row();

        table.add().width(horSpacer);
        table.add(volumeUp).size(width, height);
        table.add(volumeDown).size(width, height);
        table.add(volumeMute).size(width, height);
        table.add().width(horSpacer);
        table.row();

        table.add().height(verSpacer);
        table.row();

        stage.addActor(table);
    }

    public void update(float delta) {
        volume = Integer.toString((int)Math.floor(JumpJellyJump.getVolumeLevel()*100));
        volumeLabel.setText(volume);
    }

    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    public boolean isVolumeUpPressed() {
        return volumeUpPressed;
    }

    public boolean isVolumeDownPressed() {
        return volumeDownPressed;
    }

    public boolean isVolumeMutePressed() {
        return volumeMutePressed;
    }

    public boolean isBackPressed() {
        return backPressed;
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

}
