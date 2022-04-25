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


public class PlayView implements Disposable {
    public Viewport viewport;
    public Stage stage;
    boolean leftPressed, rightPressed, jumpPressed, attackPressed;
    private boolean disabled;
    private float attackTimeoutCount;
    private int attackTimeoutTimer;
    private int width, height;
    private Table table;
    private Image attackButton;
    private Image noAttack;
    private OrthographicCamera cam;

    public PlayView(SpriteBatch sb) {
        attackTimeoutCount = 0;
        attackTimeoutTimer = 0;
        noAttack = new Image(new Texture("buttons/noAttack.png"));
        noAttack.setSize(width, height);
        disabled = false;


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
//                    case Input.Keys.SPACE:
//                        attackPressed = true;
//                        break;
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
//                    case Input.Keys.SPACE:
//                        attackPressed = false;
//                        break;
                }
                return true;
            }
        });

        width = 28;
        height = 28;


        Gdx.input.setInputProcessor(stage);

        table = new Table();
        table.left().bottom();

        Image leftButton = new Image(new Texture("buttons/left.png"));
        leftButton.setSize(width, height);
        leftButton.setColor(leftButton.getColor().r, leftButton.getColor().g, leftButton.getColor().b, 0.5f);
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
        rightButton.setSize(width, height);
        rightButton.setColor(rightButton.getColor().r, rightButton.getColor().g, rightButton.getColor().b, 0.5f);
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
        jumpButton.setSize(width, height);
        jumpButton.setColor(jumpButton.getColor().r, jumpButton.getColor().g, jumpButton.getColor().b, 0.5f);
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

        attackButton = new Image(new Texture("buttons/attack.png"));
        attackButton.setSize(width, height);
        attackButton.addListener(new InputListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                attackPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                attackPressed = false;
            }
        });

        int numCol = (int) Math.floor(JumpJellyJump.V_WIDTH / width);
        int numRow = (int) Math.floor(JumpJellyJump.V_HEIGHT / height);

        for (int i = 0; i<numRow; i++) {
            for (int j = 0; j<numCol; j++) {
                if (i==numRow-2 && j==1) {
                    table.add(leftButton);
                }
                else if (i==numRow-2 && j==3) {
                    table.add(rightButton);
                } else if (i==numRow-2 && j==numCol-1) {
                    table.add(jumpButton);
                } else if (i==1 && j==numCol-1) {
                    table.add(attackButton);
                } else {
                    table.add().width(width);
                }
            }
            table.row().height(height);
        }

        stage.addActor(table);

    }

    public void draw() {
        stage.draw();
    }

    public void update(float delta) {
        attackTimeoutCount += delta;
        if (attackTimeoutCount >= 1) {
            if (attackTimeoutTimer > 0) {
                attackTimeoutTimer--;
            } else {
                if (table.getCell(noAttack) != null) {
                    table.getCell(noAttack).setActor(attackButton);
                }
            }
            attackTimeoutCount = 0;
        }
    }

    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    public boolean isLeftPressed() {
        if (disabled) { return false; }
        return leftPressed;
    }

    public boolean isRightPressed() {
        if (disabled) { return false; }
        return rightPressed;
    }

    public boolean isJumpPressed() {
        if (disabled) { return false; }
        return jumpPressed;
    }

    public boolean isAttackPressed() {
        if (disabled) { return false; }

        if (attackTimeoutTimer == 0) {
            return attackPressed;
        } else {
            return false;
        }
    }

    public void setAttackPressed(boolean attackPressed) {
        this.attackPressed = attackPressed;

        this.attackTimeoutTimer = 5;
        this.table.getCell(attackButton).setActor(noAttack);
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
