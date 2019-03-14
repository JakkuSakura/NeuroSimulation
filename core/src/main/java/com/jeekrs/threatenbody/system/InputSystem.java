package com.jeekrs.threatenbody.system;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.collision.Ray;

public class InputSystem extends SimpleSystem implements InputProcessor {
    @Override
    public void init() {
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void update(float delay) {
        float speed = 300;
        if (Gdx.input.isKeyPressed(Input.Keys.W))
            systemManager.renderSystem.camera.translate(0, speed * delay, 0);
        if (Gdx.input.isKeyPressed(Input.Keys.S))
            systemManager.renderSystem.camera.translate(0, -speed * delay, 0);
        if (Gdx.input.isKeyPressed(Input.Keys.A))
            systemManager.renderSystem.camera.translate(-speed * delay, 0, 0);
        if (Gdx.input.isKeyPressed(Input.Keys.D))
            systemManager.renderSystem.camera.translate(speed * delay, 0, 0);
//        if (Gdx.input.)

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
            Gdx.app.exit();


    }

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
        System.out.println("touched " + screenX + " " + screenY);
        Ray pickRay = systemManager.renderSystem.camera.getPickRay(screenX, screenY);
        System.out.println(pickRay.origin);
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
    public boolean scrolled(int amount) {
        return false;
    }
}
