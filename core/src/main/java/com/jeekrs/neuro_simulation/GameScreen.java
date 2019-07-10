package com.jeekrs.neuro_simulation;

import com.badlogic.gdx.Screen;
import com.jeekrs.neuro_simulation.entities.Ant;
import com.jeekrs.neuro_simulation.entities.Living;
import com.jeekrs.neuro_simulation.entities.Wall;
import com.jeekrs.neuro_simulation.system.SystemManager;
import com.jeekrs.neuro_simulation.system.WorldSystem;
import com.jeekrs.neuro_simulation.utils.RandomUtil;

public class GameScreen implements Screen {
    public WorldSystem worldSystem = new WorldSystem();
    public SystemManager systemManager = new SystemManager();

    @Override
    public void show() {
        systemManager.setWorldSystem(worldSystem);
        systemManager.addSystem(worldSystem);
        systemManager.init();

        for (int i = 0; i < 10; ++i) {
            Living p1 = new Ant();
            p1.getPos().set(RandomUtil.nextFloat(-400, 400), RandomUtil.nextFloat(-400, 400));
            worldSystem.entities.add(p1);
        }
        placeWallRect(50, -1000, -1000, 40, 40);
        for (int i = 0; i < 20; ++i) {
            Wall w = new Wall(50, 50);
            w.getPos().set(RandomUtil.nextFloat(-800, 800), RandomUtil.nextFloat(-800, 800));
            worldSystem.entities.add(w);
        }
    }

    public void placeWallRect(int size, int beginx, int beginy, int nx, int ny) {
        for (int i = 0; i < nx; ++i) {
            Wall w = new Wall(size, size);
            w.getPos().set(beginx + size * i, beginy);
            worldSystem.entities.add(w);
        }

        for (int i = 0; i < nx; ++i) {
            Wall w = new Wall(size, size);
            w.getPos().set(beginx + size * i, beginy + size * ny);
            worldSystem.entities.add(w);
        }
        for (int i = 0; i < ny; ++i) {
            Wall w = new Wall(size, size);
            w.getPos().set(beginx, beginy + size * i);
            worldSystem.entities.add(w);
        }

        for (int i = 0; i < nx; ++i) {
            Wall w = new Wall(size, size);
            w.getPos().set(beginx + size * nx, beginy + size * i);
            worldSystem.entities.add(w);
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