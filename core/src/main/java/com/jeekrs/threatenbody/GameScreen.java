package com.jeekrs.threatenbody;

import com.badlogic.gdx.Screen;
import com.jeekrs.threatenbody.entity.Planet;
import com.jeekrs.threatenbody.system.*;
import com.jeekrs.threatenbody.system.World;

public class GameScreen implements Screen {
    public World world = new World();
    public SystemManager systemManager = new SystemManager();

    @Override
    public void show() {
        systemManager.setWorld(world);
        systemManager.addSystem(world);

        systemManager.init();

        Planet p1 = new Planet(10, 5000);
        p1.getPos().x = 200;


        Planet p2 = new Planet(10, 5000);
        p2.getPos().x = -200;


        Planet p3 = new Planet(10, 5000);
        p3.getPos().y = -200;



        world.entities.add(p1);
        world.entities.add(p2);
        world.entities.add(p3);
    }

    @Override
    public void render(float delta) {
        systemManager.update(delta);
    }

    @Override
    public void resize(int width, int height) {
        systemManager.renderSystem.setWindowSize(width, height);
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