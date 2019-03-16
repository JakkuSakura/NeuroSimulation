package com.jeekrs.threatenbody;

import com.badlogic.gdx.Screen;
import com.jeekrs.threatenbody.entity.Planet;
import com.jeekrs.threatenbody.system.*;
import com.jeekrs.threatenbody.system.WorldSystem;

public class GameScreen implements Screen {
    public WorldSystem worldSystem = new WorldSystem();
    public SystemManager systemManager = new SystemManager();

    @Override
    public void show() {
        systemManager.setWorldSystem(worldSystem);
        systemManager.addSystem(worldSystem);
        systemManager.init();

        for (int i = 0; i < 100; ++i) {
            Planet p1 = new Planet(10, 300);
            p1.getPos().x = (Math.random() - 0.5) * 500;
            p1.getPos().y = (Math.random() - 0.5) * 500;
            p1.getVel().x = (Math.random() - 0.5) * 5;
            p1.getVel().y = (Math.random() - 0.5) * 5;
            worldSystem.entities.add(p1);

        }
    }

    @Override
    public void render(float delta) {
        systemManager.update(delta);
    }

    @Override
    public void resize(int width, int height) {
        systemManager.resize(width, height);
    }

    @Override
    public void pause() {
        // Invoked when your application is paused.
    }

    @Override
    public void resume() {
        // Invoked when your application is resumed after pause.
    }

    @Override
    public void hide() {
        // This method is called when another screen replaces this one.
    }

    @Override
    public void dispose() {
        systemManager.dispose();
    }
}