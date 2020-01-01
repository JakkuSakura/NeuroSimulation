package com.jeekrs.neuro_simulation;

import com.badlogic.gdx.Screen;
import com.jeekrs.neuro_simulation.entities.Food;
import com.jeekrs.neuro_simulation.entities.Swarm;
import com.jeekrs.neuro_simulation.system.GamePanel;
import com.jeekrs.neuro_simulation.system.SystemManager;

public class GameScreen implements Screen {

    public static SystemManager systemManager = new SystemManager();

    @Override
    public void show() {
//        systemManager.UISystem.addUIComponent(new GamePanel());
        systemManager.entitySystem.entities.add(new Food(0, 0));
        systemManager.entitySystem.entities.add(new Swarm(30, 0));
        systemManager.init();
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