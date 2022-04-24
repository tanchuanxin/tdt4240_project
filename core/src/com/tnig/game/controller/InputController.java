package com.tnig.game.controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.tnig.game.controller.events.game_events.Attack;
import com.tnig.game.controller.events.game_events.Jump;
import com.tnig.game.controller.events.game_events.MoveLeft;
import com.tnig.game.controller.events.game_events.MoveRight;
import com.tnig.game.controller.events.game_events.StopPlayer;
import com.tnig.game.controller.events.screen_events.PauseEvent;
import com.tnig.game.controller.events.screen_events.ResumeEvent;
import com.tnig.game.controller.managers.EventManager;

public class InputController implements InputProcessor {

    private final EventManager eventManager;

    public InputController(EventManager eventManager) {
        this.eventManager = eventManager;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode){
            case Input.Keys.LEFT:
                eventManager.pushEvent(new MoveLeft());
                return true;
            case Input.Keys.RIGHT:
                eventManager.pushEvent(new MoveRight());
                return true;
            case Input.Keys.UP:
            case Input.Keys.SPACE:
                eventManager.pushEvent(new Jump());
                return true;
            case Input.Keys.DOWN:
                eventManager.pushEvent(new StopPlayer(keycode));
                return true;
            case Input.Keys.P:
                eventManager.pushEvent(new PauseEvent());
                break;
            case Input.Keys.R:
                eventManager.pushEvent(new ResumeEvent());
                break;
            case Input.Keys.A:
                eventManager.pushEvent(new Attack());
                break;
        }
        return false;
    }
    @Override
    public boolean keyUp(int keycode) {
        switch (keycode){
            case Input.Keys.LEFT:
            case Input.Keys.RIGHT:
                eventManager.pushEvent(new StopPlayer(keycode));
                return true;
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