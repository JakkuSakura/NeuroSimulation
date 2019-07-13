package com.jeekrs.neuro_simulation;

import com.badlogic.gdx.Screen;
import com.jeekrs.neuro_simulation.entities.Wall;
import com.jeekrs.neuro_simulation.entities.functionals.Wood;
import com.jeekrs.neuro_simulation.entities.nest.AntFighterNest;
import com.jeekrs.neuro_simulation.system.SystemManager;

public class GameScreen implements Screen {

    public static SystemManager systemManager = new SystemManager();

    @Override
    public void show() {
        Agenda player = new Agenda() {{
            setNumber(1);
        }};
        Agenda NPC = new Agenda() {{
            setAI(true);
            setPlayer(false);
            setNumber(2);
            setRemote(false);
        }};

        systemManager.agendaSystem.agendas.add(player);
        systemManager.agendaSystem.agendas.add(NPC);

        AntFighterNest p1 = new AntFighterNest();
        p1.getPos().set(300, 0);
        p1.setAgenda(player);
        systemManager.entitySystem.addEntity(p1);

        AntFighterNest p2 = new AntFighterNest();
        p2.getPos().set(-300, 0);
        p2.setAgenda(NPC);
        systemManager.entitySystem.addEntity(p2);


        Wood wood = new Wood();
        wood.getPos().set(0, 666);
        systemManager.entitySystem.addEntity(wood);
        placeWallRect(50, -1000, -1000, 40, 40);

        systemManager.init();

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

        for (int i = 0; i <= nx; ++i) {
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