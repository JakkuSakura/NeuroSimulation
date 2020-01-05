package com.jeekrs.neuro_simulation.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class EscExit extends SimpleInputProcessor {
    @Override
    public boolean keyDown(int keycode) {
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
            Gdx.app.exit();
        return true;
    }
}
