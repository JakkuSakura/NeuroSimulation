package com.jeekrs.neuro_simulation.system;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.collision.Ray;
import com.jeekrs.neuro_simulation.entities.Entity;
import com.jeekrs.neuro_simulation.entities.Living;
import com.jeekrs.neuro_simulation.utils.Vec2d;

public class InputSystem extends SimpleSystem implements InputProcessor {
    public InputStack inputStack = new InputStack();

    @Override
    public void init() {
        inputStack.stack.addFirst(this);
        Gdx.input.setInputProcessor(inputStack);
    }

    public Entity pickUp(Vec2d pos) {
        for (Entity e : systemManager.worldSystem.entities) {
            if (e instanceof Living) {
                if (pos.dist(e.getPhy().pos) < 10)
                    return e;
                // todo
            }
        }
        return null;
    }

    @Override
    public void update(float delta) {

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
            Gdx.app.exit();


    }

    public Entity selected;

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {

        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        System.out.println("touched screen " + screenX + " " + screenY);
        Ray pickRay = systemManager.renderSystem.camera.getPickRay(screenX, screenY);
        Vec2d pos = new Vec2d(pickRay.origin.x, pickRay.origin.y);
        System.out.println("touched stage" + pos);
        selected = pickUp(pos);
        System.out.println(selected);
        return selected != null;
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
    public boolean scrolled(int amount) {
        return false;
    }
}
