package com.jeekrs.threatenbody.system;

import com.badlogic.gdx.InputProcessor;

import java.util.LinkedList;

public class InputStack implements InputProcessor {
    public LinkedList<InputProcessor> stack = new LinkedList<>();

    @Override
    public boolean keyDown(int keycode) {
        for (InputProcessor e : stack) {
            if (e.keyDown(keycode))
                return true;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        for (InputProcessor e : stack) {
            if (e.keyUp(keycode))
                return true;
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        for (InputProcessor e : stack) {
            if (e.keyTyped(character))
                return true;
        }
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        for (InputProcessor e : stack) {
            if (e.touchDown(screenX, screenY, pointer, button))
                return true;
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        for (InputProcessor e : stack) {
            if (e.touchUp(screenX, screenY, pointer, button))
                return true;
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        for (InputProcessor e : stack) {
            if (e.touchDragged(screenX, screenY, pointer))
                return true;
        }
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        for (InputProcessor e : stack) {
            if (e.mouseMoved(screenX, screenY))
                return true;
        }
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        for (InputProcessor e : stack) {
            if (e.scrolled(amount))
                return true;
        }
        return false;
    }
}
