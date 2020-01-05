package com.jeekrs.neuro_simulation.systems;

import com.badlogic.gdx.Input;

public class ViewController extends SimpleInputProcessor {
    private RenderSystem renderSystem;

    public ViewController(RenderSystem renderSystem) {
        this.renderSystem = renderSystem;
    }


    public boolean zoomin, zoomout;
    public float mvx, mvy;
    public void update(float delta)
    {
        if (zoomin)
            renderSystem.camera.zoom *= 1 - 0.5 * delta;

        if (zoomout)
            renderSystem.camera.zoom *= 1 + 0.5 * delta;

        renderSystem.camera.translate(mvx * delta * renderSystem.camera.zoom, mvy * delta * renderSystem.camera.zoom, 0);

    }
    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.J) {
            zoomin = true;
            return true;
        }
        if (keycode == Input.Keys.K) {
            zoomout = true;
            return true;
        }

        if (keycode == Input.Keys.W) {
            mvy += 300;
            return true;
        }

        if (keycode == Input.Keys.S) {
            mvy += -300;
            return true;
        }

        if (keycode == Input.Keys.A) {
            mvx += -300;
            return true;
        }

        if (keycode == Input.Keys.D) {
            mvx += 300;
            return true;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.J) {
            zoomin = false;
            return true;
        }

        if (keycode == Input.Keys.K) {
            zoomout = false;
            return true;
        }
        if (keycode == Input.Keys.W) {
            mvy -= 300;
            return true;
        }

        if (keycode == Input.Keys.S) {
            mvy -= -300;
            return true;
        }

        if (keycode == Input.Keys.A) {
            mvx -= -300;
            return true;
        }

        if (keycode == Input.Keys.D) {
            mvx -= 300;
            return true;
        }

        return false;
    }

}
