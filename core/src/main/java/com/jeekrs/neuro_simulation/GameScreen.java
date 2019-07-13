package com.jeekrs.neuro_simulation;

import com.badlogic.gdx.Screen;
import com.jeekrs.neuro_simulation.entities.Ant;
import com.jeekrs.neuro_simulation.entities.Living;
import com.jeekrs.neuro_simulation.entities.Wall;
import com.jeekrs.neuro_simulation.system.SystemManager;
import com.jeekrs.neuro_simulation.utils.RandomUtil;

public class GameScreen implements Screen {

    public static SystemManager systemManager = new SystemManager();

    @Override
    public void show() {

        systemManager.init();

        for (int i = 0; i < 1; ++i) {
            Living p1 = new Ant();
            p1.getPos().set(RandomUtil.nextFloat(-400, 400), RandomUtil.nextFloat(-400, 400));
            systemManager.entitySystem.addEntity(p1);
        }
        placeWallRect(50, -1000, -1000, 40, 40);
        for (int i = 0; i < 20; ++i) {
            Wall w = new Wall(50, 50);
            w.getPos().set(RandomUtil.nextFloat(-800, 800), RandomUtil.nextFloat(-800, 800));
            systemManager.entitySystem.addEntity(w);
        }
    }

    public void placeWallRect(int size, int beginx, int beginy, int nx, int ny) {
        for (int i = 0; i < nx; ++i) {
            Wall w = new Wall(size, size);
            w.getPos().set(beginx + size * i, beginy);
            systemManager.entitySystem.addEntity(w);
        }

        for (int i = 0; i < nx; ++i) {
            Wall w = new Wall(size, size);
            w.getPos().set(beginx + size * i, beginy + size * ny);
            systemManager.entitySystem.addEntity(w);
        }
        for (int i = 0; i < ny; ++i) {
            Wall w = new Wall(size, size);
            w.getPos().set(beginx, beginy + size * i);
            systemManager.entitySystem.addEntity(w);
        }

        for (int i = 0; i < nx; ++i) {
            Wall w = new Wall(size, size);
            w.getPos().set(beginx + size * nx, beginy + size * i);
            systemManager.entitySystem.addEntity(w);
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