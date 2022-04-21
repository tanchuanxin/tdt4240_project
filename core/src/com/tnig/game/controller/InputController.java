package com.tnig.game.controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.tnig.game.controller.events.game_events.Jump;
import com.tnig.game.controller.events.game_events.MoveLeft;
import com.tnig.game.controller.events.game_events.MoveRight;
import com.tnig.game.controller.events.game_events.StopJump;
import com.tnig.game.controller.events.game_events.StopMoveLeft;
import com.tnig.game.controller.events.game_events.StopMoveRight;
import com.tnig.game.controller.managers.EventManager;

public class InputController implements InputProcessor {

    private final EventManager eventManager;

    public InputController(EventManager eventManager) {
        this.eventManager = eventManager;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.LEFT:
                eventManager.pushEvent(new MoveLeft());
                break;
            case Input.Keys.RIGHT:
                eventManager.pushEvent(new MoveRight());
                break;
            case Input.Keys.UP:
                eventManager.pushEvent(new Jump());
                break;
            case Input.Keys.DOWN:
                eventManager.pushEvent(new StopJump());
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.LEFT:
                eventManager.pushEvent(new StopMoveLeft());
                break;
            case Input.Keys.RIGHT:
                eventManager.pushEvent(new StopMoveRight());
                break;
        }
        return false;
    }
    @Override
    public boolean keyTyped(char character) {
        return false;
    }
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }
    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

}