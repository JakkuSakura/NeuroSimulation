package com.jeekrs.neuro_simulation.inputs;

import com.badlogic.gdx.InputProcessor;

import java.util.ArrayList;
import java.util.LinkedList;

public class InputStack implements InputProcessor {
    public LinkedList<InputProcessor> stack = new LinkedList<>();
    public ArrayList<InputProcessor> toDel = new ArrayList<>();
    public void print() {
        for (InputProcessor inputProcessor : stack) {
            System.out.println(inputProcessor);
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        stack.removeAll(toDel);
        toDel.clear();
        for (InputProcessor e : stack) {
            if (e.keyDown(keycode))
                return true;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        stack.removeAll(toDel);
        toDel.clear();
        for (InputProcessor e : stack) {
            if (e.keyUp(keycode))
                return true;
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        stack.removeAll(toDel);
        toDel.clear();
        for (InputProcessor e : stack) {
            if (e.keyTyped(character))
                return true;
        }
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        stack.removeAll(toDel);
        toDel.clear();
        for (InputProcessor e : stack) {
            if (e.touchDown(screenX, screenY, pointer, button))
                return true;
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        stack.removeAll(toDel);
        toDel.clear();
        for (InputProcessor e : stack) {
            if (e.touchUp(screenX, screenY, pointer, button))
                return true;
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        stack.removeAll(toDel);
        toDel.clear();
        for (InputProcessor e : stack) {
            if (e.touchDragged(screenX, screenY, pointer))
                return true;
        }
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        stack.removeAll(toDel);
        toDel.clear();
        for (InputProcessor e : stack) {
            if (e.mouseMoved(screenX, screenY))
                return true;
        }
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        stack.removeAll(toDel);
        toDel.clear();
        for (InputProcessor e : stack) {
            if (e.scrolled(amount))
                return true;
        }
        return false;
    }
}
