package com.jeekrs.threatenbody.system;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class InputSystem extends SimpleSystem {
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

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
            Gdx.app.exit();


    }
}
