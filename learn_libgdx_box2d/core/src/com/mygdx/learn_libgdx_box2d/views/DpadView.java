package com.mygdx.learn_libgdx_box2d.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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


public class DpadView implements Disposable {
    public Viewport viewport;
    public Stage stage;
    boolean leftPressed, rightPressed, jumpPressed;
    private int size;
    private OrthographicCamera cam;

    public DpadView(SpriteBatch sb) {
        cam = new OrthographicCamera();
        viewport = new FitViewport(JumpJellyJump.V_WIDTH, JumpJellyJump.V_HEIGHT, cam);
        stage = new Stage(viewport, sb);
        stage.addListener(new InputListener() {

            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                switch (keycode) {
                    case Input.Keys.UP:
                    case Input.Keys.W:
                        jumpPressed = true;
                        break;
                    case Input.Keys.LEFT:
                    case Input.Keys.A:
                        leftPressed = true;
                        break;
                    case Input.Keys.RIGHT:
                    case Input.Keys.D:
                        rightPressed = true;
                        break;
                }
                return true;
            }

            @Override
            public boolean keyUp(InputEvent event, int keycode) {
                switch (keycode) {
                    case Input.Keys.UP:
                    case Input.Keys.W:
                        jumpPressed = false;
                        break;
                    case Input.Keys.LEFT:
                    case Input.Keys.A:
                        leftPressed = false;
                        break;
                    case Input.Keys.RIGHT:
                    case Input.Keys.D:
                        rightPressed = false;
                        break;
                }
                return true;
            }
        });

        size = 28;


        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.left().bottom();

        Image leftButton = new Image(new Texture("buttons/left.png"));
        leftButton.setSize(size, size);
        leftButton.addListener(new InputListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                leftPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                leftPressed = false;
            }
        });

        Image rightButton = new Image(new Texture("buttons/right.png"));
        rightButton.setSize(size, size);
        rightButton.addListener(new InputListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                rightPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                rightPressed = false;
            }
        });

        Image jumpButton = new Image(new Texture("buttons/jump.png"));
        jumpButton.setSize(size, size);
        jumpButton.addListener(new InputListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                jumpPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                jumpPressed = false;
            }
        });


        float spacer = (JumpJellyJump.V_WIDTH - leftButton.getWidth() - rightButton.getWidth() - jumpButton.getWidth()) / 18;

        table.add().width(spacer);
        table.add(leftButton).size(leftButton.getWidth(), leftButton.getHeight());
        table.add().width(spacer);
        table.add(rightButton).size(rightButton.getWidth(), rightButton.getHeight());
        table.add().width(spacer*15);
        table.add(jumpButton).size(jumpButton.getWidth(), jumpButton.getHeight());
        table.add().width(spacer);
        table.row();

        table.add().height(leftButton.getHeight());
        table.row();

        stage.addActor(table);
    }

    public void draw() {
        stage.draw();
    }

    public void resize(int width, int height) {
        viewport.update(width, height);
    }


    public boolean isLeftPressed() {
        return leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    public boolean isJumpPressed() {
        return jumpPressed;
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

}
