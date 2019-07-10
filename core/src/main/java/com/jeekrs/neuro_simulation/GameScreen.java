package com.jeekrs.neuro_simulation;

import com.badlogic.gdx.Screen;
import com.jeekrs.neuro_simulation.entities.Ant;
import com.jeekrs.neuro_simulation.entities.Living;
import com.jeekrs.neuro_simulation.entities.Wall;
import com.jeekrs.neuro_simulation.system.*;
import com.jeekrs.neuro_simulation.system.WorldSystem;

public class GameScreen implements Screen {
    public WorldSystem worldSystem = new WorldSystem();
    public SystemManager systemManager = new SystemManager();

    @Override
    public void show() {
        systemManager.setWorldSystem(worldSystem);
        systemManager.addSystem(worldSystem);
        systemManager.init();

        for (int i = 0; i < 30; ++i) {
            Living p1 = new Ant();
            worldSystem.entities.add(p1);
        }
        Wall w = new Wall(50, 300);
        w.getPhy().pos.set(300, 0);
        worldSystem.entities.add(w);
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