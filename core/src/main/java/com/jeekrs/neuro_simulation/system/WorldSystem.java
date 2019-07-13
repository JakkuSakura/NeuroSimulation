package com.jeekrs.neuro_simulation.system;

import com.jeekrs.neuro_simulation.entities.BaseCamp;
import com.jeekrs.neuro_simulation.entities.Wall;
import com.jeekrs.neuro_simulation.utils.RandomUtil;

import static com.jeekrs.neuro_simulation.GameScreen.systemManager;

public class WorldSystem extends SimpleSystem {


    public WorldSystem() {

    }

    public void createWrold() {

        systemManager.agendaSystem.agendas.forEach((a, s) -> {
            BaseCamp baseCamp = new BaseCamp();
            baseCamp.getPos().set(RandomUtil.nextFloat(-800, 800), RandomUtil.nextFloat(-800, 800));
            baseCamp.setAgenda(a);
            baseCamp.setSpecies(s);
            systemManager.entitySystem.addEntity(baseCamp);
        });

        placeWallRect(50, -1000, -1000, 40, 40);
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

}
