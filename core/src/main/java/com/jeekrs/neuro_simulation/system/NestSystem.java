package com.jeekrs.neuro_simulation.system;

import com.jeekrs.neuro_simulation.entities.Entity;
import com.jeekrs.neuro_simulation.entities.livings.Living;
import com.jeekrs.neuro_simulation.entities.nest.Nest;

import static com.jeekrs.neuro_simulation.GameScreen.systemManager;

public class NestSystem extends SimpleSystem {
    @Override
    public void update(float delta) {

        for (Entity entity : systemManager.entitySystem.entities) {
            if (entity instanceof Nest) {
                Nest n = (Nest) entity;
                if (System.currentTimeMillis() - n.getLastTime() >= n.getDelayMs()) {
                    n.setLastTime(System.currentTimeMillis());
                    Living breed = n.reproduce();
                    n.evolve();
                    systemManager.entitySystem.addEntity(breed);
                }
            }
        }
    }
}
